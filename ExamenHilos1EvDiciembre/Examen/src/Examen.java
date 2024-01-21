import java.util.ArrayList;

public class Examen {

    private ArrayList<Integer> preguntas;
    private Double tiempoTotal;
    private boolean haAcabado;

    public Examen(){
        preguntas = new ArrayList<>();
        preguntas.add(100);
        preguntas.add(200);
        preguntas.add(300);
        preguntas.add(400);

        tiempoTotal = 0.0;
        preguntas.forEach((p) -> {
                tiempoTotal += p;
        });

        tiempoTotal = tiempoTotal * 1.25;
        tiempoTotal = Math.ceil(tiempoTotal);

        haAcabado = false;
    }


    public ArrayList<Integer> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Integer> preguntas) {
        this.preguntas = preguntas;
    }

    public Double getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(Double tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public boolean isHaAcabado() {
        return haAcabado;
    }

    public void setHaAcabado(boolean haAcabado) {
        this.haAcabado = haAcabado;
    }
}
