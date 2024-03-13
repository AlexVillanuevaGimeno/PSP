import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Decurion {
    String [][] ordenes = new String[5][2];
    int centuriones = 0;
    int puerto = 10000;

    int prioridadTraer = 0;
    int prioridadMensaje = 0;
    int prioridadVigilar = 0 ;

    private String[] getOrden(int prioridadTraer, int prioridadMensaje, int prioridadVigilar) {
        Random random = new Random();
        int indice = -1;
        prioridadTraer = prioridadTraer * random.nextInt();
        prioridadMensaje = prioridadMensaje * random.nextInt();
        prioridadVigilar = prioridadVigilar * random.nextInt();

        String ordenes[][] = {
                {"TRAER", "Traer " + traerAlgo()},
                {"MENSAJE", "Mandar el siguiente mensaje a " + getReceptor() + ": " + getMensaje()},
                {"VIGILAR", "Vigilar " + getLugar() + " durante " + getTiempo()}
        };
        if (prioridadTraer > prioridadMensaje && prioridadTraer > prioridadVigilar){
             indice = 0;
        }
        if (prioridadMensaje > prioridadTraer && prioridadMensaje > prioridadVigilar){
            indice =1;
        }
        if (prioridadVigilar > prioridadTraer && prioridadVigilar > prioridadMensaje){
            indice = 2;
        }
        return ordenes[indice];
    };

    public Decurion(String host, int port){
        while (true){
            try(Socket server = new Socket(host,port)){
                System.out.println("Estoy conectado");
                InputStream inputStreamServidor = server.getInputStream();
                OutputStream outputStreamServidor = server.getOutputStream();
                DataInputStream dataInputStreamServidor = new DataInputStream(inputStreamServidor);
                DataOutputStream dataOutputStreamServidor = new DataOutputStream(outputStreamServidor);
                prioridadTraer = dataInputStreamServidor.readInt();
                prioridadMensaje = dataInputStreamServidor.readInt();
                prioridadVigilar = dataInputStreamServidor.readInt();


            }  catch (IOException e) {
                throw new RuntimeException(e);
            }
            //CONEXION DE LOS CENTURIONES
            for (int i = 0; i < 4; i++) {
                try(ServerSocket serverSocket = new ServerSocket(puerto)){
                    Socket cliente = serverSocket.accept();
                    InputStream inputStreamCliente = cliente.getInputStream();
                    OutputStream outputStreamCliente = cliente.getOutputStream();
                    DataInputStream dataInputStream = new DataInputStream(inputStreamCliente);
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStreamCliente);

                    for (int j= 0; j < 5; j++) {
                        String orden[] = getOrden(prioridadTraer,prioridadMensaje,prioridadVigilar);
                        String tipoOrden = orden[0];
                        String mensajeOrden = orden[1];
                        String ordenCifrada = cifrarMensaje(mensajeOrden);
                        dataOutputStream.writeUTF(tipoOrden);
                        dataOutputStream.writeUTF(ordenCifrada);
                    }
                    System.out.println("ORDENES DADAS AL CENTURION");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            puerto = puerto +100;
        }
    }


    public String traerAlgo(){
        Random random = new Random();
        String[] deseosDelCesar = {
                "prisioneros",
                "cerveza",
                "papel de pergamino"
        };
        int indice2 = random.nextInt(deseosDelCesar.length);
        String frase = deseosDelCesar[indice2];
        switch (frase){
            case "prisioneros":
                int cantidadPrisioneros = getCantidad();
                if(cantidadPrisioneros==1){
                    frase = cantidadPrisioneros + " prisionero";
                }else{
                    frase = cantidadPrisioneros + " prisioneros";
                }
                break;
            case "cerveza":
                int cantidadCervezas = getCantidad();
                if(cantidadCervezas==1){
                    frase = cantidadCervezas + " cerveza";
                }else{
                    frase = cantidadCervezas + " cervezas";
                }

                break;
            case "papel de pergamino":
                frase = "papel de pergamino";
                break;
        }
        return frase;
    }

    public int getCantidad(){
        Random random = new Random();
        int[] mensajes = {
                1,
                2,
                3,
                4,
                5,
                10,
                100,
                200
        };
        int indice = random.nextInt(mensajes.length);
        return mensajes[indice];
    }

    public String getReceptor(){
        Random random = new Random();
        String[] receptores = {
                "Cleopatra de Egipto",
                "Filo de Tracia",
                "el Rey Artavasdes de Armenia",
                "el Rey Farasmanes de Iberia",
                "la Reina Cartimandua de los Brigantes"
        };
        int indice = random.nextInt(receptores.length);
        return receptores[indice];
    }

    public String getMensaje(){
        Random random = new Random();
        String[] mensajes = {
                "Me gustaría acordar un tratado de paz",
                "Me gustaría establecer una alianza",
                "Os declaro la guerra",
                "Me gustaría estrechar lazos con usted"
        };
        int indice = random.nextInt(mensajes.length);
        return mensajes[indice];
    }

    public String getLugar(){
        Random random = new Random();
        String[] lugares = {
                "las murallas",
                "la torre centinela",
                "el santuario",
                "las minas"
        };
        int indice = random.nextInt(lugares.length);
        return lugares[indice];
    }

    public static String getTiempo() {
        Random random = new Random();
        String[] tiempoPosible = {
                "1 día",
                "2 días",
                "3 días",
                "4 días",
                "5 días",
                "6 días",
                "7 días",
        };
        int indice = random.nextInt(tiempoPosible.length);
        return tiempoPosible[indice];
    }

    /**
     *
     * @param mensaje
     * @return
     */
    public static String cifrarMensaje(String mensaje) {
        char[] abecedario = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] mensajeCifrado = new char[mensaje.length()];

        for (int i = 0; i < mensaje.length(); i++) {
            char caracter = mensaje.charAt(i);
            int indice = -1;

            // Verificar si el caracter está en mayúsculas
            if (Character.isUpperCase(caracter)) {
                indice = caracter - 'A';
                mensajeCifrado[i] = abecedario[(indice + 3) % 26];
            }
            // Verificar si el caracter está en minúsculas
            else if (Character.isLowerCase(caracter)) {
                indice = caracter - 'a';
                mensajeCifrado[i] = Character.toLowerCase(abecedario[(indice + 3) % 26]);
            }
            // Si no es una letra, mantener el mismo caracter
            else {
                mensajeCifrado[i] = caracter;
            }
        }

        return new String(mensajeCifrado);
    }
}
