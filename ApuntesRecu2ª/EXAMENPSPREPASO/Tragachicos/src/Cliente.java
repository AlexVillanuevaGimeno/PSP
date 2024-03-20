import java.io.*;
import java.net.*;
import java.util.Random;

public class Cliente {
    public static void main(String[] args) throws IOException {
        // Establecer la conexión con el servidor en localhost y puerto 12345
        Socket socket = new Socket("localhost", 12345);
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); // Para enviar mensajes al servidor
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); // Para recibir entrada del usuario desde la consola
        Random random = new Random(); // Para generar valores aleatorios

        try {
            while (true) {
                // Generar un nombre de niño aleatorio
                String nombreNiño = generarNombreNiño();

                // Enviar mensaje al servidor para poner al niño en la fila
                salida.println("ponersefila:" + nombreNiño);

                // Esperar 3 segundos antes de enviar el siguiente mensaje
                Thread.sleep(3000);

                // Enviar mensaje al servidor para que el niño suba
                salida.println("subir:" + nombreNiño);

                // Esperar 3 segundos antes de enviar el siguiente mensaje
                Thread.sleep(3000);

                // Enviar mensaje al servidor para que el niño baje
                salida.println("bajar:" + nombreNiño);

                // Esperar 3 segundos antes de enviar el siguiente mensaje
                Thread.sleep(3000);

                // Decidir aleatoriamente si el niño dará vuelta o no
                if (debeDarVueltaAleatoria()) {
                    // Enviar mensaje al servidor para que el niño dé vuelta
                    salida.println("darvuelta:" + nombreNiño);

                    // Esperar 3 segundos antes de enviar el siguiente mensaje
                    Thread.sleep(3000);

                    // Enviar mensaje adicional al servidor para que el niño se vuelva a poner en la fila
                    salida.println("ponersefila:" + nombreNiño);
                }

                // Esperar 3 segundos antes de enviar el siguiente mensaje
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            salida.close(); // Cerrar el flujo de salida
            entrada.close(); // Cerrar el flujo de entrada
            socket.close(); // Cerrar el socket
        }
    }

    // Generar un nombre de niño aleatorio
    private static String generarNombreNiño() {
        String[] nombres = {"Juan", "Pedro", "Ana", "María", "Luis", "Lucía", "Sofía", "Diego", "Elena"};
        Random random = new Random();
        return nombres[random.nextInt(nombres.length)];
    }

    // Decidir aleatoriamente si el niño debe dar vuelta
    private static boolean debeDarVueltaAleatoria() {
        Random random = new Random();
        // Probabilidad del 50% de que el niño dé vuelta
        return random.nextDouble() < 0.5;
    }
}
