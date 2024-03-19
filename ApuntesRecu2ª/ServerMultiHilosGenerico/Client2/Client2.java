package Client2;

import java.io.*;
import java.net.*;

public class Client2 {
    private Socket socket;

    public Client2(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try {
            // Crear flujos de entrada y salida para comunicarse con el servidor
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String userInputLine;
            // Leer entrada del usuario y enviarla al servidor
            while ((userInputLine = userInput.readLine()) != null) {
                out.println(userInputLine);
                // Leer la respuesta del servidor y mostrarla en la consola
                System.out.println("Server response: " + in.readLine());
            }

            // Cerrar la conexión con el servidor
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}