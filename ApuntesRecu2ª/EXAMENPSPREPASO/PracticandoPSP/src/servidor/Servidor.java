package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    int puerto;

    public Servidor(int puerto){
        while (true){
            System.out.println("Servidor escuchando en el puerto  "+puerto);
            try {
                ServerSocket server = new ServerSocket(puerto);
                while (true){
                    //Aceptamos la peticion del cliente
                    Socket socketClinte = server.accept();
                    //Creamos un manejador que extendera de la clase hilo para poder ejecutar varios a la vez y le pasaremos el socket
                    ManejadorMulticliente manejador = new ManejadorMulticliente(socketClinte);
                    manejador.start();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
