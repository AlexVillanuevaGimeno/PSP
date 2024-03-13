import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Centurion {
    String[][] ordenes = new String[5][2];
    int legionarios = 0;
    int puerto =4500;

    public Centurion(String host, int port) {
        while (true) {
            try (Socket server = new Socket(host, port)) {
                InputStream inputStreamServidor = server.getInputStream();
                OutputStream outputStreamServidor = server.getOutputStream();
                DataInputStream dataInputStreamServidor = new DataInputStream(inputStreamServidor);
                DataOutputStream dataOutputStreamServidor = new DataOutputStream(outputStreamServidor);
                for (int i = 0; i < 5; i++) {
                    String ordenTipoCesar = dataInputStreamServidor.readUTF();
                    String ordenConcretaCesar = dataInputStreamServidor.readUTF();
                    ordenes[i][0] = ordenTipoCesar;
                    ordenes[i][1] = ordenConcretaCesar;
                    System.out.println("Orden " + ordenes[i][0] + ", contenido: " + ordenes[i][1]);
                }

            } catch (IOException e) {
                System.out.println("No te puedes comunicar con el cesar");
            }
            for (int i = 0; i < 5; i++) {
                try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
                    Socket cliente = servidorSocket.accept();
                    InputStream inputStreamCliente = cliente.getInputStream();
                    OutputStream outputStreamCliente = cliente.getOutputStream();
                    DataInputStream dataInputStream = new DataInputStream(inputStreamCliente);
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStreamCliente);
                    Boolean legionarioLibre = dataInputStream.readBoolean();

                    if (legionarioLibre == true) {
                        dataOutputStream.writeUTF(ordenes[legionarios][0]);
                        String tipoLegionario = dataInputStream.readUTF();
                        System.out.println("He recibido un legionario de tipo " + tipoLegionario);

                        switch (tipoLegionario) {
                            case "Logística":
                                if (ordenes[legionarios][0].equals("TRAER")) {
                                    dataOutputStream.writeUTF(ordenes[legionarios][1]);
                                    System.out.println("Se ha enviado una orden a un legionario de Logística");
                                } else {
                                    dataOutputStream.writeUTF("NO PUEDES REALIZAR ESTA ORDEN");
                                }
                                break;
                            case "Soldado":
                                if (ordenes[legionarios][0].equals("VIGILAR")) {
                                    dataOutputStream.writeUTF(ordenes[legionarios][1]);
                                    System.out.println("Se ha enviado una orden a un legionario Soldado");
                                } else {
                                    dataOutputStream.writeUTF("NO PUEDES REALIZAR ESTA ORDEN");
                                }
                                break;
                            case "Explorador":
                                if (ordenes[legionarios][0].equals("MENSAJE")) {
                                    dataOutputStream.writeUTF(ordenes[legionarios][1]);
                                    System.out.println("Se ha enviado una orden a un legionario Explorador");
                                } else {
                                    dataOutputStream.writeUTF("NO PUEDES REALIZAR ESTA ORDEN");
                                }
                                break;
                        }
                        legionarios++;
                        if (legionarios == 5) {
                            legionarios = 0;
                        }
                    }else{
                        System.out.println("Legionario no esta libre");
                    }
                    } catch (IOException e){
                        System.out.println("No se han podido conectar legionarios");
                    }
            }
            puerto = puerto +100;
        }
    }
}
