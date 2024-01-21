import java.util.Random;

public class Estudiante extends Thread{

    private Examen ex;
    private boolean acabado;
    private Integer ID;

    public Estudiante(Integer ID, Examen e){
        ex = e;
        acabado = false;
        this.ID = ID;
    }

    public void estudiarPregunta(int tiempoPregunta, int numPregunta){

        System.out.println("El estudiante " + ID + " comienza la pregunta " + numPregunta);

        Random rd = new Random();
        int estudiado = rd.nextInt(3) + 1;

        int t;

        switch (estudiado){
            case 1:
                t = tiempoPregunta - rd.nextInt(tiempoPregunta / 2);
                break;
            case 3:
                t = tiempoPregunta + rd.nextInt(tiempoPregunta / 2);
                break;
            default:
                t = tiempoPregunta;
                break;
        }

        try {
            sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // El examen ha acabado antes de que el estudiante pudiese acabar la pregunta
        if(!ex.isHaAcabado()){
            System.out.println("El estudiante " + ID + " ha acabado la pregunta " + numPregunta + " en " + t / 100 + " minutos");
        }

    }

    @Override
    public void run() {

        System.out.println("Estudiante " + ID + " comienza el examen");

        for (int i = 0; i < ex.getPreguntas().size(); i++) {
            estudiarPregunta(ex.getPreguntas().get(i), i);

            if(ex.isHaAcabado()){
                break;
            }
        }

        if(!ex.isHaAcabado()){
            acabado = true;
        }

    }

    public Examen getEx() {
        return ex;
    }

    public void setEx(Examen ex) {
        this.ex = ex;
    }

    public boolean isAcabado() {
        return acabado;
    }

    public void setAcabado(boolean acabado) {
        this.acabado = acabado;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
