//Es un servidor que genera ordenes de forma constante para cualquiera que establezca una conexión con él.
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Cesar extends Thread implements I_Acciones{
    //ATRIBUTOS
    // ATRIBUTOS
    static final int PORT = 5000;

    // CONSTRUCTOR
    public Cesar() {
        start();
    }

    // MÉTODOS
    @Override
    public void run() {
        try {
            // Creamos el socket-servidor.
            ServerSocket skServidor = new ServerSocket(PORT);
            System.out.println("CESAR: Estoy asignando Decuriones a mis Centuriones desde el puerto " + PORT + ".");
            // Generamos el bucle de atención a centuriones.
            while (true) {
                Socket centurion = skServidor.accept();
                // Obtenemos probabilidades aleatorias para cada tarea que mandará el Decurión.
                int p_traer = crearAleatorio(8);
                int p_decir = crearAleatorio(9 - p_traer);
                int p_vigilar = 10 - (p_traer + p_decir);
                Thread decurion = new Decurion(centurion, p_traer, p_decir, p_vigilar);
                decurion.start();
                // Notificamos la orden enviada y la ID del centurión.
                System.out.println("CESAR: He asignado un Decurión al Centurión " + centurion.toString());
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de la excepción de E/S
        }
    }


}
