package servidor;

import java.io.*;
import java.net.Socket;

public class ManejadorMulticliente extends Thread implements Runnable {
    private Socket socketServidorCliente;

    public ManejadorMulticliente(Socket socketServidorCliente) {
      this.socketServidorCliente = socketServidorCliente;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socketServidorCliente.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            InputStream inputStream = socketServidorCliente.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataOutputStream.writeUTF("Se ha conectado con el servidor");
            String respuestaServidorCliente = dataInputStream.readUTF();
            System.out.println(respuestaServidorCliente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
