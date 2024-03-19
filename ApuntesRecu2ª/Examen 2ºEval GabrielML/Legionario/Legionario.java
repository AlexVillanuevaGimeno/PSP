import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Legionario extends Thread implements I_Acciones{
    //ATRIBUTOS
    static final String CENTURION_IP = "127.0.0.1";
    private int CENTURION_PORT;
    private String identificador;
    private char especialidad;

    //CONSTRUCTORES
    public Legionario(int id_centurion, int id_legionario){ //Crear centuriones con una especialidad aleatoria.
        CENTURION_PORT = 5000 + (id_centurion*10);
        identificador = id_centurion+"."+id_legionario;
        int n_especialidad = crearAleatorio(3);
        switch(n_especialidad){
            case 1:
                especialidad = 'E';
                break;
            case 2:
                especialidad = 'L';
                break;
            case 3:
                especialidad = 'S';
                break;
        }
        start();
    }
    public Legionario(int id_centurion, int id_legionario, char especialidad){ //Crear centuriones específicos.
        CENTURION_PORT = 5000 + (id_centurion*10);
        identificador = id_centurion+"."+id_legionario;
        this.especialidad = especialidad;
        System.out.println(toString()+" | Estableciendo conexión con:"+CENTURION_IP+":"+CENTURION_PORT);
        start();
    }

    //MÉTODOS
    @Override
    public void run(){
        //Generamos un bucle infinito de solicitar ordenes al cesar.
        while (true){
            //System.out.println(toString()+": voy a solicitar una orden a mi centurión.");
            esperar(2000); //Dejamos un pequeño tiempo de espera antes de volver a solicitar una orden.
            try {
                // Establece la conexión con el servidor.
                Socket skCliente = new Socket(CENTURION_IP, CENTURION_PORT);
    
                // Envía la especialidad para que el Centurión decida si asignarle o nó la orden.
                OutputStream aux = skCliente.getOutputStream();
                DataOutputStream flujoSalida = new DataOutputStream(aux);
                flujoSalida.writeChar(especialidad);
    
                //Obtiene la orden y la descifra.
                InputStream auxEntrada = skCliente.getInputStream();
                DataInputStream flujoEntrada = new DataInputStream(auxEntrada);
                String orden_recibida = flujoEntrada.readUTF();
    
                //Mostramos que hemos recibido una orden y la ejecutamos.
                if (!orden_recibida.equals("DENEGADO")){
                    String orden = descifrar(orden_recibida);
                    String[] orden_parametros = orden.split("\\|");
                    switch(orden_parametros[0]){
                        case "TRAER":
                            traer(orden_parametros[1], Integer.parseInt(orden_parametros[2]));
                            break;
                        case "DECIR":
                            decir(orden_parametros[1], orden_parametros[2]);
                            break;
                        case "VIGILAR":
                            vigilar(orden_parametros[1], Integer.parseInt(orden_parametros[2]));
                            break;
                    }
                }

                
                skCliente.close();
            } catch (IOException e) {
                System.err.println(toString()+": Reconectando mi Centurión.");
                esperar(2000);
            }
            
            
        }
    }

    
    //Métodos para cada acción.
    private void traer(String objeto, int cantidad){
        int tiempo = (crearAleatorio(3)+2)*1000;
        if(cantidad!=0){
            System.out.println(toString()+": voy a tardar "+tiempo/1000+" segundos en traer "+cantidad+" x "+objeto+".");
        } else{
            System.out.println(toString()+": voy a tardar "+tiempo/1000+" segundos en traer "+objeto+".");
        }
        esperar(tiempo);
        System.out.println(toString()+": he tardado "+(tiempo/1000)+" segundos en traer "+cantidad+" x "+objeto+".");
    }
    private void decir(String destinatario, String mensaje){
        int tiempo = (crearAleatorio(3)+4)*1000;
        System.out.println(toString()+": en "+tiempo/1000+" segundos habré mandado a "+destinatario+" el mensaje: "+mensaje+".");
        esperar(tiempo);
        System.out.println(toString()+": he tardado "+(tiempo/1000)+" segundos en mandar el mensaje a "+destinatario+".");
    }
    private void vigilar(String cosa, int tiempo){
        System.out.println(toString()+": voy a vigilar "+cosa+" por "+tiempo/1000+" segundos.");
        esperar(tiempo);
        System.out.println(toString()+": he acabado de vigilar "+cosa+" por "+tiempo/1000+" segundos.");
    }


    private void esperar(int tiempo){
        try {
            sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return "L"+identificador+"-"+especialidad;
    }
}