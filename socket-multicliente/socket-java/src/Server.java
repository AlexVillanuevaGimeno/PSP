import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int puerto = 5000;

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Escuchando en el puerto " + puerto);

            while (true) {
                // Aceptar la conexión del cliente
                Socket skCliente = serverSocket.accept();

                // Crear un nuevo hilo para atender al cliente y pasar el socket al constructor
                Thread clienteThread = new Thread(new ClienteHandler(skCliente, 1));
                clienteThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace(); // o usa algún mecanismo de registro adecuado
        }
    }
    class ClienteHandler implements Runnable {
        private final Socket skCliente;
        private final int clientId;

        public ClienteHandler(Socket skCliente, int clientId) {
            this.skCliente = skCliente;
            this.clientId = clientId;
        }

        @Override
        public void run() {
            try {
                System.out.println("Atendiendo al Cliente " + clientId + " desde " + skCliente.getInetAddress());


                OutputStream aux = skCliente.getOutputStream();
                DataOutput flujo = new DataOutputStream(aux);
                flujo.writeUTF("Hola cliente en " + skCliente.getInetAddress());

                // Cierra la conexión con el cliente después de enviar el mensaje
                skCliente.close();
            } catch (IOException e) {
                e.printStackTrace(); // o usa algún mecanismo de registro adecuado
            }
        }
    }

}
