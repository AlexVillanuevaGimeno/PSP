import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Centurion extends Thread implements I_Acciones {
    // ATRIBUTOS
    static final String CESAR_IP = "localhost";
    static final int CESAR_PORT = 5000;
    private String DECURION_IP = "localhost";
    private int DECURION_PORT = 0;
    private int identificador;
    private int PORT;
    private int max_intentos = 6;

    // CONSTRUCTORES
    public Centurion(int identificador) {
        this.identificador = identificador;
        PORT = 5000 + (identificador * 10);
        start();
    }

    // MÉTODOS
    @Override
    public void run() {
        try {
            // Dejamos abierto el servidor para que se puedan conectar los legionarios
            try (ServerSocket skServidor = new ServerSocket(PORT)) {
                // Generamos un bucle constante de obtención y asignación de órdenes
                while (true) {
                    esperar(2000); // Dejamos un pequeño tiempo de espera antes de volver a solicitar una orden
                    try {
                        // Si todavía no tenemos un decurión asignado, le solicitamos al César que nos
                        // asigne uno
                        if (DECURION_PORT == 0) {
                            Socket skCesar = new Socket(CESAR_IP, CESAR_PORT);
                            int puerto_decurion = establecerDecurion(skCesar);
                            skCesar.close();
                            setDecurion(puerto_decurion);
                            System.out.println(
                                    toString() + ": el César me ha asignado el Decurión " + DECURION_PORT + ".");
                            esperar(1500); // Damos tiempo para que se genere el centurión
                        }

                        // Conectamos con nuestro Decurión y le pedimos que nos mande una orden
                        Socket skDecurion = new Socket(DECURION_IP, DECURION_PORT);
                        System.out.println(toString() + ": solicitando orden a mi Decurión " + DECURION_PORT);
                        String orden = obtenerOrden(skDecurion);
                        skDecurion.close();
                        System.out.println("C" + identificador + ": mi Decurión me ha mandado: " + orden);

                        // Realizamos los intentos para asignar la orden a un legionario dentro del
                        // máximo de intentos
                        asignarOrden(orden, skServidor);

                    } catch (IOException e) {
                        System.err.println(toString() + ": Intentando reconectar con el César/Decurión.");
                        esperar(2000);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(toString() + ": Fallo al tratar de abrirme como servidor!!!");
        }
    }

    public String obtenerOrden(Socket skDecurion) {
        try {
            // Obtiene la orden y la descifra
            DataInputStream flujoEntrada = new DataInputStream(skDecurion.getInputStream());
            String orden = descifrar(flujoEntrada.readUTF());
            // Devolvemos la orden asignada
            return orden;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al solicitar la orden";
        }
    }

    public int establecerDecurion(Socket skCesar) {
        try {
            // Obtiene el puerto del Decurion al que tenemos que conectarnos
            DataInputStream flujoEntrada = new DataInputStream(skCesar.getInputStream());
            int puerto_decurion = flujoEntrada.readInt();
            // Devolvemos el puerto asignado y cerramos la conexión con el César
            return puerto_decurion;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void asignarOrden(String orden, ServerSocket skServidor) {
        // Destripamos los datos de la orden para saber a qué tipo de legionario debemos
        // enviarla
        String[] orden_parametros = orden.split("\\|");
        // Dejamos por defecto un "DENEGADO" en caso de que no se asigne la orden en los
        // intentos
        String orden_enviada = "DENEGADO";
        try {
            // Realizamos un máximo de 5 intentos para asignar la tarea a un legionario
            for (int i = 0; i < max_intentos; i++) {
                System.out.println(
                        "C" + identificador + ": intento " + (i + 1) + "/" + max_intentos + " de asignar la orden.");
                // Aceptamos al primer legionario que nos contacte y obtenemos su tipo
                Socket legionario = skServidor.accept();
                DataInputStream flujoEntrada = new DataInputStream(legionario.getInputStream());
                char tipo_legionario = flujoEntrada.readChar();
                esperar(300);
                // Comprobamos si el tipo de legionario coincide con la orden
                if (orden_parametros[0].equals("TRAER")) {
                    if (comprobarEspecialidad('L', tipo_legionario)) {
                        orden_enviada = cifrar(orden);
                        System.out.println(
                                toString() + ": he asignado la orden a mi legionario de tipo " + tipo_legionario + ".");
                        break;
                    }
                } else if (orden_parametros[0].equals("DECIR")) {
                    if (comprobarEspecialidad('E', tipo_legionario)) {
                        orden_enviada = cifrar(orden);
                        System.out.println(
                                toString() + ": he asignado la orden a mi legionario de tipo " + tipo_legionario + ".");
                        break;
                    }
                } else if (orden_parametros[0].equals("VIGILAR")) {
                    if (comprobarEspecialidad('S', tipo_legionario)) {
                        orden_enviada = cifrar(orden);
                        System.out.println(
                                toString() + ": he asignado la orden a mi legionario de tipo " + tipo_legionario + ".");
                        break;
                    }
                }
                // Enviamos la orden cifrada o el "DENEGADO" según si se coincide el tipo de
                // legionario
                OutputStream aux = legionario.getOutputStream();
                DataOutputStream flujoSalida = new DataOutputStream(aux);
                flujoSalida.writeUTF(orden_enviada);
                // Dejamos un pequeño tiempo de espera antes de volver a intentar asignarla
                esperar(500);
            }
            // Si no se ha logrado asignar la orden, lo notificamos
            if (orden_enviada.equals("DENEGADO")) {
                System.out.println(toString() + ": no he logrado asignar la orden");
            }

        } catch (IOException e) {
            System.out
                    .println(toString() + ": la orden no se ha podido asignar, voy a solicitar otra orden a mi César.");
            esperar(3000);
        }
    }

    // Comprobamos si la especialidad coincide con la orden
    private boolean comprobarEspecialidad(char especialidad, char tipo_legionario) {
        return especialidad == tipo_legionario;
    }

    private void esperar(int tiempo) {
        try {
            sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setDecurion(int DECURION_PORT) {
        this.DECURION_PORT = DECURION_PORT;
    }

    @Override
    public String toString() {
        return "C" + getIdentificador();
    }
}
