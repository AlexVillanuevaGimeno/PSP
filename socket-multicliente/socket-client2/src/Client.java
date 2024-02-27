import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    static final String HOST = "localhost";
    static final int puerto = 5000;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client() {
        try {
            Socket skCliente = new Socket(HOST, puerto);

            // Leer el mensaje del servidor
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujo = new DataInputStream(aux);
            String mensaje = flujo.readUTF();

            // Imprimir el mensaje junto con el ID del cliente
            System.out.println("Cliente ID: " + obtenerClienteId() + " - Mensaje del servidor: " + mensaje);

            // Cierra la conexión con el servidor
            skCliente.close();
        } catch (IOException e) {
            e.printStackTrace(); // o usa algún mecanismo de registro adecuado
        }
    }

    private int obtenerClienteId() {
        // Puedes implementar la lógica para obtener o generar un ID único para el cliente
        // En este ejemplo, simplemente retornamos un valor fijo para demostración.
        return 2;
    }
}