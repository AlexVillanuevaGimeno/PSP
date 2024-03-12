import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class JulioCesar {

    private String[] getOrden() {
        Random random = new Random();
        String ordenes[][] = {
                {"TRAER", "Traeme " + traerAlgo()},
                {"MENSAJE", "Manda el siguiente mensaje a " + getReceptor() + ":\n" + getMensaje()},
                {"VIGILAR", "Vigilad " + getLugar() + " durante " + getTiempo()}
        };
        int indice = random.nextInt(ordenes.length);
        return ordenes[indice];
    };



    public JulioCesar(int puerto) {
        while(true){
            try (ServerSocket server = new ServerSocket(puerto)) {
                System.out.println("Julio Cesar escuchando en el puerto " + puerto);
                for (int i = 0; i < 4; i++) {
                    Socket client = server.accept();
                    System.out.println("Orden dada a centurión.");

                    OutputStream outputStream = client.getOutputStream();
                    InputStream inputStream = client.getInputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    for (int j = 0; j < 5; j++) {
                        String[] orden = getOrden();
                        String tipoOrden = orden[0];
                        String mensajeOrden = orden[1];
                        String ordenCifrada = cifrarMensaje(mensajeOrden);
                        dataOutputStream.writeUTF(tipoOrden);
                        dataOutputStream.writeUTF(ordenCifrada);
                    }
                    dataOutputStream.writeUTF("ORDENES DADAS AL CENTURION");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
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


    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public static String getTiempo() {
        Random random = new Random();
        String[] tiempoPosible = {
                "1 día",
                "1 semana",
                "1 mes",
                "1 semestre"
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