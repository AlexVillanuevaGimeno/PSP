import java.io.*;
import java.net.Socket;

public class ChildHandler implements Runnable {
    private final Socket socket;

    public ChildHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); // Para enviar mensajes al servidor
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); // Para recibir entrada del usuario desde la consola

            while (true) {
                String message = entrada.readLine();
                System.out.println(message);

                if (message.contains("bajo por el Tragachicos")) {
                    salida.println("Soy el niño y he bajado por el Tragachicos");
                } else if (message.contains("Me voy ya a casa")) {
                    System.out.println("El niño se ha ido a casa");
                    break;
                } else if (message.contains("Vuelvo al Tragachicos")) {
                    salida.println("Soy el niño y he vuelto al Tragachicos");
                }
            }

            socket.close();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
