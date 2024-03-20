import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static FilaDeNiños fila = new FilaDeNiños(); // Instancia de la fila de niños

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(12345); // Crear un servidor en el puerto 12345
        System.out.println("Servidor esperando conexiones...");

        while (true) {
            Socket cliente = servidor.accept(); // Esperar y aceptar conexiones de clientes
            System.out.println("Cliente conectado desde " + cliente.getInetAddress() + ":" + cliente.getPort());

            // Crear un hilo para manejar al cliente
            Thread clienteThread = new Thread(new ClienteHandler(cliente));
            clienteThread.start();

            // Mostrar la fila constantemente
            mostrarFilaConstantemente();
        }
    }

    // Clase interna para manejar cada cliente en un hilo separado
    static class ClienteHandler implements Runnable {
        private Socket cliente;
        private PrintWriter salida;
        private BufferedReader entrada;

        public ClienteHandler(Socket socketCliente) throws IOException {
            this.cliente = socketCliente;
            salida = new PrintWriter(cliente.getOutputStream(), true); // Salida para enviar mensajes al cliente
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream())); // Entrada para recibir mensajes del cliente
        }

        public void run() {
            try {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) { // Leer mensajes del cliente
                    System.out.println("Mensaje recibido: " + mensaje);
                    String[] partesMensaje = mensaje.split(":");
                    String accion = partesMensaje[0]; // Acción solicitada por el cliente
                    String nombreNiño = partesMensaje[1]; // Nombre del niño involucrado en la acción

                    switch (accion) {
                        case "subir":
                            if (verificarEnFila(nombreNiño)) {
                                // Permitir subir solo si el niño está en la fila
                                System.out.println(nombreNiño + " está en la fila y sube al Tragachicos.");
                            } else {
                                System.out.println(nombreNiño + " no está en la fila. Debe ponerse en la fila primero.");
                            }
                            break;
                        case "bajar":
                            Niño niñoABajar = fila.sacarNiño();
                            if (niñoABajar != null) {
                                System.out.println(nombreNiño + " ha bajado del Tragachicos.");
                            } else {
                                System.out.println("La fila está vacía, no hay niños para bajar.");
                            }
                            break;
                        case "darvuelta":
                            Niño niñoDarVuelta = encontrarNiñoEnFila(nombreNiño);
                            if (niñoDarVuelta != null) {
                                niñoDarVuelta.darVuelta();
                                System.out.println(nombreNiño + " ha dado vuelta.");
                                fila.agregarNiño(niñoDarVuelta); // Agregar al final de la fila después de dar vuelta
                                System.out.println(nombreNiño + " se ha vuelto a poner en la fila.");
                            } else {
                                System.out.println("No se pudo encontrar al niño " + nombreNiño + " en la fila.");
                            }
                            break;
                        case "ponersefila":
                            fila.agregarNiño(new Niño(nombreNiño)); // Agregar un nuevo niño a la fila
                            System.out.println(nombreNiño + " se ha puesto en la fila.");
                            break;
                        // Agregar más casos según las acciones del enunciado
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    cliente.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Verificar si el niño está en la fila
        private boolean verificarEnFila(String nombre) {
            for (Niño niño : fila.getFila()) {
                if (niño.getNombre().equals(nombre)) {
                    return true;
                }
            }
            return false;
        }

        // Encontrar al niño en la fila por su nombre
        private Niño encontrarNiñoEnFila(String nombre) {
            for (Niño niño : fila.getFila()) {
                if (niño.getNombre().equals(nombre)) {
                    return niño;
                }
            }
            return null;
        }
    }

    // Mostrar la fila constantemente en un hilo separado
    private static void mostrarFilaConstantemente() {
        while (true) {
            System.out.println(fila.mostrarFila()); // Mostrar la fila en la consola
            try {
                Thread.sleep(5000); // Mostrar la fila cada 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
