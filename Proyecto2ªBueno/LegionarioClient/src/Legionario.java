import java.io.*;
import java.net.Socket;
import java.util.Random;


public class Legionario {
    String tipo;
    int contadorSoldados = 0;
    int contadorLogistica = 0;
    int contadorExploradores = 0;
    public Legionario(String host, int puerto){
        try(Socket server = new Socket(host,puerto)){
            InputStream inputStream = server.getInputStream();
            OutputStream outputStream = server.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            System.out.println(dataInputStream.readUTF());
            String tipoLegionario = getTipo();
            sumaContadores(tipoLegionario);
            //PASO EL TIPO DE LEGIONARIO
            dataOutputStream.writeUTF(tipoLegionario);
            //PASO LOS CONTADORES
            dataOutputStream.writeInt(contadorExploradores);
            dataOutputStream.writeInt(contadorSoldados);
            dataOutputStream.writeInt(contadorLogistica);
        } catch (IOException e) {
            System.out.println("No has podido contactar con tu centurión");
        }
    }

    /**
     *
     * @param tipoLegionario
     */
    public void sumaContadores(String tipoLegionario) {
        switch (tipoLegionario) {
            case "Explorador":
                contadorExploradores++;
                break; // Agregar break para salir del switch después de ejecutar el case
            case "Soldado":
                contadorSoldados++;
                break; // Agregar break para salir del switch después de ejecutar el case
            case "Logistica":
                contadorLogistica++;
                break; // Agregar break para salir del switch después de ejecutar el case
            default:
                // Manejar casos no reconocidos
                System.out.println("Tipo de legionario no reconocido.");
                break;
        }
    }

    /**
     *
     * @return
     */
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

}
