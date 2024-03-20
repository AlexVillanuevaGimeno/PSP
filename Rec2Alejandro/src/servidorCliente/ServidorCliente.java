package servidorCliente;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCliente {
    static final String HOST = "localhost";
    static final int puerto = 5000;
    private int id;
    public ServidorCliente(String host, int puerto) {
        //AQUI ACTUA COMO UN CLIENTE Y REALIZA UNA PETICION A UN SERVIDOR
        try {
            Socket server = new Socket(host, puerto);

            InputStream inputStream = server.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            OutputStream outputStream = server.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            String mensajeServidor = dataInputStream.readUTF();
            dataOutputStream.writeUTF("Soy el ServidorCliente "+id);
            System.out.println(mensajeServidor);

        } catch (IOException e) {
            System.out.println("No has podido conectar con el servidor");
        }

        //AQUI ACTUA COMO UN SERVIDOR Y ACEPTA 5 PETICIONES DE CLIENTES
        try {
            int puertoCliente = 4500;
            while(puertoCliente<10000){
                ServerSocket cliente = new ServerSocket(puertoCliente);
                Socket socketCliente = cliente.accept();

                InputStream inputStream = socketCliente.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                OutputStream outputStream = socketCliente.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                dataOutputStream.writeUTF("Te has conectado con el Servidor-Cliente");
                String mensajeCliente = dataInputStream.readUTF();
                System.out.println(mensajeCliente);
                puertoCliente += 10;
            }


        } catch (IOException e) {
            System.out.println("No has podido recibir al cliente");
        }
    }

}
