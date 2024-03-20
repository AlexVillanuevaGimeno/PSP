import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Monitor extends Thread{
    public Monitor() {
    }

    @Override
    public void run() {
        try {
            // Creamos el servidor de sockets
            ServerSocket skServidor = new ServerSocket(23451);
            System.out.println(toString() + ": voy a recoger niños en el puerto" + 23451 + ".");
            // Generamos el bucle de atención a centuriones
            while (true) {
                Socket miCliente = skServidor.accept();
                Thread hiloNiño = new Thread(() -> atenderNiño(miCliente));
                hiloNiño.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void atenderNiño(Socket cliente) {
        try{

            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true); // Para enviar mensajes al servidor
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in)); // Para recibir entrada del usuario desde la consola
            int idNiño = entrada.read();
            System.out.println(toString() + ": el Niño " + idNiño + " ha llegado y se va a la fila");
            // Cerramos la conexión
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
