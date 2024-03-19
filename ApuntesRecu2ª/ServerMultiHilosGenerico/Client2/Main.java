package Client2;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Conectar al servidor en localhost en el puerto 12345
        Socket socket = new Socket("localhost", 12345);
        // Iniciar el cliente y enviar mensajes al servidor
        Client2 client = new Client2(socket);
        client.start();
    }
}