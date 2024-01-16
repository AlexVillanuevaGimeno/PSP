import java.util.ArrayList;
import java.util.Arrays;

public class Cancion {
    private int idCancion;
    private String titulo;
    private ArrayList<String> estrofa;

    public Cancion() {
    }

    public Cancion(int idCancion, String titulo) {
        this.idCancion = idCancion;
        this.titulo = titulo;
    }

    public Cancion(int idCancion, String titulo, ArrayList<String> estrofa) {
        this.idCancion = idCancion;
        this.titulo = titulo;
        this.estrofa = estrofa;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getEstrofa() {
        return estrofa;
    }

    public void setEstrofa(ArrayList<String> estrofa) {
        this.estrofa = estrofa;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "idCancion=" + idCancion +
                ", titulo='" + titulo + '\'' +
                ", estrofa=" + estrofa +
                '}';
    }
}
