import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TragachicosServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Soy el Tragachicos, y ya estoy esperando zagales");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Se ha conectado un niño");
                Thread childThread = new Thread(new ChildHandler(socket));
                childThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
