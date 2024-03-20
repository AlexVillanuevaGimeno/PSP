import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Child {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); // Para enviar mensajes al servidor
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); // Para recibir entrada del usuario desde la consola

            Scanner scanner = new Scanner(System.in);
            System.out.print("¿Cuántos niños van a llegar?: ");
            int numberOfChildren = scanner.nextInt();

            for (int i = 1; i <= numberOfChildren; i++) {
                System.out.println("Soy el niño " + i + ", voy al Tragachicos");
                salida.println("Soy el niño " + i + ", voy al Tragachicos");
                Thread.sleep(1000); // Simulando la llegada de un niño cada segundo
            }

            while (true) {
                String message = entrada.readLine();
                System.out.println(message);
                if (message.contains("bajo por el Tragachicos")) {
                    System.out.println("¿Quieres dar otra vuelta? (s/n)");
                    String response = scanner.next();

                    if (response.equalsIgnoreCase("n")) {
                        salida.println("Me voy ya a casa");
                        break;
                    } else {
                        salida.println("Vuelvo al Tragachicos");
                    }
                }
            }

            socket.close();
            scanner.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
