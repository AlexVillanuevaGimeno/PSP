import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Random;


public class Legionario extends Thread implements Runnable {
    String host;
    int puerto;
    String tipo;
    Boolean libre = true;

    public Legionario(String host, int puerto){
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try(Socket server = new Socket(host,puerto)){
            InputStream inputStream = server.getInputStream();
            OutputStream outputStream = server.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBoolean(this.libre);
            String tipoLegionario = getTipo();
            this.tipo = tipoLegionario;
            System.out.println("Soy un legionario de tipo " + tipoLegionario);
            String tipoOrden = dataInputStream.readUTF();
            System.out.println("Me han asignado una orden de tipo " + tipoOrden);
            dataOutputStream.writeUTF(tipoLegionario);
            String ordenCenturion = dataInputStream.readUTF();
            if (ordenCenturion != "NO PUEDES REALIZAR ESTA ORDEN"){
                switch (tipoOrden){
                    case "MENSAJE":
                        if (tipoLegionario.equals("Explorador")){
                            libre = false;
                            String ordenAEjecutar = ordenCenturion;
                            System.out.println("Tengo que "+ordenAEjecutar);
                            System.out.println("Enviando mensaje...");
                            sleep(getTiempoRandom("MENSAJE"));
                            this.libre = true;
                            dataOutputStream.writeInt(compruebaLibres());
                        }else{
                            System.out.println("Me han enviado una orden que no puedo ejecutar");
                            this.libre = true;
                            dataOutputStream.writeInt(compruebaLibres());

                            sleep(1000);
                        }
                        break;
                    case "TRAER":
                        if (tipoLegionario.equals("Logística")){
                            libre = false;
                            String ordenAEjecutar = ordenCenturion;
                            System.out.println("Tengo que " + ordenAEjecutar);
                            System.out.println();
                            System.out.println("Trayendo lo que ha pedido cesar...");
                            sleep(getTiempoRandom("TRAER"));
                            this.libre = true;
                            dataOutputStream.writeInt(compruebaLibres());

                        }else{
                            System.out.println("Me han enviado una orden que no puedo ejecutar");
                            this.libre = true;
                            sleep(1000);
                            dataOutputStream.writeInt(compruebaLibres());

                        }
                        break;
                    case "VIGILAR":
                        if (tipoLegionario.equals("Soldado")){
                            libre = false;
                            String ordenAEjecutar = ordenCenturion;
                            System.out.println("Tengo que "+ordenAEjecutar);
                            System.out.println();
                            System.out.println("Vigilando...");
                            sleep(3000);
                            this.libre = true;
                            dataOutputStream.writeInt(compruebaLibres());

                        }else{
                            System.out.println("Me han enviado una orden que no puedo ejecutar");
                            this.libre = true;
                            sleep(1000);
                            dataOutputStream.writeInt(compruebaLibres());

                        }
                        break;
                }
            }else{
                System.out.println("Me han enviado una orden que no puedo ejecutar");
                this.libre = true;
                sleep(1000);
                dataOutputStream.writeInt(compruebaLibres());

            }
//            System.out.println();
        } catch (IOException e) {
            System.out.println("No has podido contactar con tu centurión");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public String getTipo() {
        Random random = new Random();
        String[] tipos = {
                "Explorador",
                "Soldado",
                "Logística",
        };
        int indice = random.nextInt(tipos.length);
        this.tipo = tipos[indice];
        return tipos[indice];
    }

    public int getTiempoRandom(String accion){
        Random random = new Random();
        int milisegundos = 0;
        switch (accion){
            case "MENSAJE":
                milisegundos = random.nextInt(5) + 3;
                break;
            case "TRAER":
                milisegundos = random.nextInt(4) + 2;
                break;
        }


        return milisegundos * 1000;
    }

    public int compruebaLibres(){
        if (this.libre == true){
            return this.puerto;
        }else{
            return 0;
        }
    }

}
