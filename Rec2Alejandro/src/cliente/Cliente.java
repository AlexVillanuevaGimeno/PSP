package cliente;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente {
    int id;
    public Cliente(String host, int puerto, int id){
        this.id = id;
        try {
            Socket server = new Socket(host, puerto);

            InputStream inputStream = server.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            OutputStream outputStream = server.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            String mensajeServidorCliente = dataInputStream.readUTF();
            dataOutputStream.writeUTF("Hola soy el cliente "+id);
            System.out.println(mensajeServidorCliente);

        } catch (IOException e) {
            System.out.println("No has podido conectar con el ServidorCliente");
        }
    }
}
