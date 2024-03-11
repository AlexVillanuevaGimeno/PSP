import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Centurion {
    String[][] ordenes = new String[5][2];
    int contadorLegionarios;

    public Centurion(String host, int port){

        try(Socket server = new Socket(host,port)) {
            InputStream inputStreamServidor = server.getInputStream();
            OutputStream outputStreamServidor = server.getOutputStream();
            DataInputStream dataInputStreamServidor = new DataInputStream(inputStreamServidor);
            DataOutputStream dataOutputStreamServidor = new DataOutputStream(outputStreamServidor);
            for (int i = 0; i < 5; i++) {
                String ordenTipoCesar = dataInputStreamServidor.readUTF();
                String ordenConcretaCesar = dataInputStreamServidor.readUTF();
                ordenes[i][0] = ordenTipoCesar;
                ordenes[i][1] = ordenConcretaCesar;
                System.out.println("Orden "+ordenes[i][0]+", contenido: "+ordenes[i][1]);
            }

        } catch (IOException e) {
            System.out.println("No te puedes comunicar con el cesar");
        }

        try(ServerSocket servidorSocket = new ServerSocket(4500)){
            Socket cliente = servidorSocket.accept();
            InputStream inputStreamCliente = cliente.getInputStream();
            OutputStream outputStreamCliente = cliente.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStreamCliente);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStreamCliente);
            String tipoLegionario = dataInputStream.readUTF();
            System.out.println(tipoLegionario);
            int contadorExploradores = dataInputStream.readInt();
            int contadorSoldados = dataInputStream.readInt();
            int contadorLogistica = dataInputStream.readInt();
            contadorLegionarios++;
            switch (tipoLegionario){
                case "Logistica":
                        if (ordenes[contadorLegionarios][0].equals("TRAER")){
                            dataOutputStream.writeUTF("ORDEN: " + ordenes[contadorLegionarios][1]);
                            System.out.println("ORDEN ENVIADA A UN LEGIONARIO DE LOGISTICA");
                        }
                    break;
                case "Soldado":
                        if (ordenes[contadorLegionarios][0].equals("VIGILAR")){
                            dataOutputStream.writeUTF("ORDEN: " + ordenes[contadorLegionarios][1]);
                            System.out.println("ORDEN ENVIADA A UN LEGIONARIO SOLDADO");
                        }
                    break;
                case "Explorador":
                        if (ordenes[contadorLegionarios][0].equals("MENSAJE")){
                            dataOutputStream.writeUTF("ORDEN: " + ordenes[contadorLegionarios][1]);
                            System.out.println("ORDEN ENVIADA A UN LEGIONARIO EXPLORADOR");
                        }
                    break;
            }
        } catch (IOException e) {
            System.out.println("No tienes legionarios disponibles.");
        }
    }

}