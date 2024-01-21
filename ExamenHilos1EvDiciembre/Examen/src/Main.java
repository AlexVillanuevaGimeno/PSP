import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {



    public static void main(String[] args) {

        Examen e = new Examen();
        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        System.out.println("Comienza el examen");

        for (int i = 0; i < 5; i++) {
            Estudiante estudiante = new Estudiante(i + 1, e);
            estudiante.start();
            estudiantes.add(estudiante);
        }

        // No me acordaba de cómo parsear de double a Integer
        Integer tiempoTotal = Integer.valueOf((Math.ceil(e.getTiempoTotal()) + "").split("\\.")[0]);

        System.out.println("Tiempo total para hacer el examen: " + tiempoTotal);

        try {
            Thread.sleep(tiempoTotal);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("El examen ha acabado");
        e.setHaAcabado(true);

        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante es = estudiantes.get(i);
            if(!es.isAcabado()){
                System.out.println("El estudiante " + es.getID() + " no ha podido acabar el examen");
            }
        }
    }
}