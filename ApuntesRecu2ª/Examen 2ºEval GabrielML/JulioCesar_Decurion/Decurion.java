import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Decurion extends Thread implements I_Acciones {
    // ATRIBUTOS
    private int PORT;
    private int p_traer;
    private int p_decir;
    private int p_vigilar;

    // CONSTRUCTORES
    public Decurion(Socket centurion, int p_traer, int p_decir, int p_vigilar) {
        // Obtener el puerto del centurión
        this.PORT = centurion.getPort();
        this.p_traer = p_traer;
        this.p_decir = p_decir;
        this.p_vigilar = p_vigilar;
    }

    // MÉTODOS
    @Override
    public void run() {
        try {
            // Creamos el servidor de sockets
            ServerSocket skServidor = new ServerSocket(PORT);
            System.out.println(toString() + ": voy a mandar ordenes a mis centuriones desde el puerto " + PORT + ".");
            System.out.println(toString() + ": TRAER=" + p_traer + " | DECIR=" + p_decir + " | VIGILAR=" + p_vigilar);
            // Generamos el bucle de atención a centuriones
            while (true) {
                Socket miCliente = skServidor.accept();
                Thread hiloCenturion = new Thread(() -> atenderCenturion(miCliente));
                hiloCenturion.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void atenderCenturion(Socket cliente) {
        try {
            // Generamos una orden
            String orden = generar_orden();

            // Lee la ID del centurión atendido
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            int idCenturion = flujoEntrada.readInt();
            System.out.println(toString() + ": el Centurión " + idCenturion + " me ha solicitado una orden.");

            // Enviamos la orden cifrada
            OutputStream aux = cliente.getOutputStream();
            DataOutputStream flujoSalida = new DataOutputStream(aux);
            flujoSalida.writeUTF(orden);

            // Notificamos la orden enviada y la ID del centurión
            System.out.println(toString() + ": Centurion " + idCenturion + " ha recibido la orden: " + descifrar(orden));

            // Cerramos la conexión
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generar_orden() {
        String orden = "";
        // Generamos aleatoriamente el tipo de orden que se va a mandar
        int n_orden = crearAleatorio(3);
        // Orden de traer algo
        if (n_orden <= p_traer) {
            orden += "TRAER|";
            int n_cosa = crearAleatorio(3);
            int n_cantidad = crearAleatorio(5); // CANTIDAD DE COSAS A TRAER
            switch (n_cosa) {
                case 1:
                    orden += "CERVEZA|" + String.valueOf(n_cantidad);
                    break;
                case 2:
                    orden += "PRISIONEROS|" + String.valueOf(n_cantidad);
                    break;
                case 3:
                    orden += "PERGAMINO|0";
                    break;
            }
        } else if (n_orden > p_traer && n_orden <= (p_traer + p_decir)) {
            orden += "DECIR|"; // Orden
            orden += "Pompeyo el Grande|"; // Destinatario
            orden += "Este es un mensaje cifrado, solo puede ser leido por el ejercito romano."; // Cuerpo del mensaje
        } else if (n_orden > (p_traer + p_decir)) {
            int n_tiempo = crearAleatorio(5) * 1000;
            orden += "VIGILAR|"; // Orden
            orden += "ENTRADA CAMPAMENTO|"; // Objeto a vigilar
            orden += String.valueOf(n_tiempo); // Tiempo de vigilancia
        }
        return cifrar(orden);
    }

    @Override
    public String toString() {
        return "D" + (PORT - 5000);
    }
}
