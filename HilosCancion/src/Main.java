import java.util.ArrayList;
import java.util.Scanner;

/*
Queremos cantar una canción entre un número “N” hilos que se conocerán en tiempo
de ejecución (a través de una lectura por teclado), de modo que cada uno de ellos cante una
estrofa (imprimiéndola por pantalla) e intentemos que por pantalla la canción se pueda leer de
un modo que no suene “raro”. Para cantar cada estrofa los hilos leen esa estrofa de un array
común, de modo que cada elemento del array es una de las estrofas. Todos los hilos por
supuesto comparten la pantalla, de modo que intentarán escribir en ella su estrofa, pero habrá
que tener en cuenta que antes de cantar mi estrofa el anterior debe haber cantado la suya.
Además cuando los hilos hayan teminado de “cantar” el programa principal pedirá un
aplauso para ellos.
Se pide:
 V1.0 – Versión en la que UNICAMENTE haya dos hilos de ejecución (además del main).
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Cancion> listCanciones = new ArrayList<>();
        Cancion bohemianRhapsody = new Cancion(1, "Bohemian Rhapsody");
        ArrayList<String> estrofasBohemianRhapsody = new ArrayList<>();
        estrofasBohemianRhapsody.add("Is this the real life?");
        estrofasBohemianRhapsody.add("Is this just fantasy?");
        estrofasBohemianRhapsody.add("Caught in a landslide,");
        estrofasBohemianRhapsody.add("No escape from reality.");
        estrofasBohemianRhapsody.add("I'm just a poor boy, nobody loves me.");
        estrofasBohemianRhapsody.add("He's just a poor boy from a poor family,");
        estrofasBohemianRhapsody.add("Spare him his life from this monstrosity.");
        estrofasBohemianRhapsody.add("Mama, just killed a man,");
        estrofasBohemianRhapsody.add("Put a gun against his head,");
        estrofasBohemianRhapsody.add("Pulled my trigger, now he's dead.");
//        int numEstrofas = 0;
//        numEstrofas = estrofasBohemianRhapsody.size();
        bohemianRhapsody.setEstrofa(estrofasBohemianRhapsody);
        listCanciones.add(bohemianRhapsody);
        ArrayList<Hilo> listHilos = new ArrayList<>();
        int numHilos = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("CUANTOS CANTANTES QUIERES QUE HAYA?");
        numHilos = sc.nextInt();
        sc.close();

        for (int i = 0; i < numHilos; i++) {
            Hilo hilo = new Hilo(i);
            listHilos.add(hilo);
        }
        System.out.println(listHilos.toString());
    }
}