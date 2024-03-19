package Server;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    public void start() throws IOException {
        // Crear un servidor socket en el puerto 12345
        ServerSocket serverSocket = new ServerSocket(12345);
        
        // Crear un pool de hilos para manejar múltiples clientes MAX TAREAS = 10
        ExecutorService executor = Executors.newFixedThreadPool(10);

        System.out.println("Server listening on port 12345...");

        try {
            // Ciclo para esperar y manejar las conexiones de los clientes
            while (true) {
                // Aceptar una conexión de cliente entrante
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Iniciar un nuevo hilo para manejar la comunicación con el cliente
                executor.submit(new ClientHandler(clientSocket));
            }
        } finally {
            // Cerrar el servidor socket y detener el pool de hilos cuando el servidor
            // termina
            serverSocket.close();
            executor.shutdown();
        }
    }

    // Clase interna para manejar la comunicación con un cliente
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                // Crear flujos de entrada y salida para comunicarse con el cliente
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                // Leer mensajes del cliente y enviarlos de vuelta como respuesta
                while ((inputLine = in.readLine()) != null) {
                    out.println("Server: " + inputLine); // Echo back to client
                }

                // Cerrar la conexión con el cliente cuando termina la comunicación
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}