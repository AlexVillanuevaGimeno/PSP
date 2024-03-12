import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Centurion {
    String[][] ordenes = new String[5][2];
    int contadorLegionarios = 0;
    String respuesta ;
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

            try(ServerSocket servidorSocket = new ServerSocket(5001)) {
                for (int i = 0; i < 5; i++) {
                System.out.println("CENTURION ESCUCHANDO en 5001");
                Socket cliente = servidorSocket.accept();
                System.out.println("Orden a legionario");
                InputStream inputStreamCliente = cliente.getInputStream();
                OutputStream outputStreamCliente = cliente.getOutputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStreamCliente);
                DataOutputStream dataOutputStream = new DataOutputStream(outputStreamCliente);
                //paso el tipo de orden
                // salida 1
                dataOutputStream.writeUTF(ordenes[contadorLegionarios][0]);
                String tipoLegionario = dataInputStream.readUTF();

                System.out.println(tipoLegionario);
//                int contadorExploradores = dataInputStream.readInt();
//                int contadorSoldados = dataInputStream.readInt();
//                int contadorLogistica = dataInputStream.readInt();
                    //salida 2
                    //paso la orde
                switch (tipoLegionario) {
                    case "Logistica":
                        if (ordenes[contadorLegionarios][0].equals("TRAER")) {
                            respuesta = "ORDEN: " + ordenes[contadorLegionarios][1];
//                            dataOutputStream.writeUTF("ORDEN: " + ordenes[contadorLegionarios][1]);
                            System.out.println("ORDEN ENVIADA A UN LEGIONARIO DE LOGISTICA");
//                            System.out.println("ACTUALMENTE HAY " + contadorLogistica + " Legionarios de Logistica");
                        } else {
                            respuesta = "NO PUEDES REALIZAR ESTA ORDEN";
//                            dataOutputStream.writeUTF("NO PUEDES REALIZAR ESTA ORDEN");
                            System.out.println("Un legionario de logistica no puede hacer esta tarea");
                        }
                        dataOutputStream.writeUTF(respuesta);
                        break;
                    case "Soldado":
                        if (ordenes[contadorLegionarios][0].equals("VIGILAR")) {
                            respuesta = "ORDEN: " + ordenes[contadorLegionarios][1];
//                            dataOutputStream.writeUTF("ORDEN: " + ordenes[contadorLegionarios][1]);
                            System.out.println("ORDEN ENVIADA A UN LEGIONARIO SOLDADO");
//                            System.out.println("ACTUALMENTE HAY " + contadorSoldados + " Legionarios Soldados");

                        }else {
                            respuesta = "NO PUEDES REALIZAR ESTA ORDEN";
//                            dataOutputStream.writeUTF("NO PUEDES REALIZAR ESTA ORDEN");
                            System.out.println("Un legionario Soldado no puede hacer esta tarea");
                        }
                        dataOutputStream.writeUTF(respuesta);
                        break;
                    case "Explorador":
                        if (ordenes[contadorLegionarios][0].equals("MENSAJE")) {
                            respuesta = "ORDEN: " + ordenes[contadorLegionarios][1];
//                            dataOutputStream.writeUTF("ORDEN: " + ordenes[contadorLegionarios][1]);
                            System.out.println("ORDEN ENVIADA A UN LEGIONARIO EXPLORADOR");
//                            System.out.println("ACTUALMENTE HAY " + contadorExploradores + " Legionarios Exploradores");

                        }else{
                            respuesta = "NO PUEDES REALIZAR ESTA ORDEN";
//                            dataOutputStream.writeUTF("NO PUEDES REALIZAR ESTA ORDEN");
                            System.out.println("Un legionario Explorador no puede hacer esta tarea");
                        }
                        dataOutputStream.writeUTF(respuesta);
                        break;
                }
                contadorLegionarios++;
                    System.out.println(contadorLegionarios);
            }
            } catch (IOException e) {
                System.out.println("No tienes legionarios disponibles.");
            }
        }

    }
