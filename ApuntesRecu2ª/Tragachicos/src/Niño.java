public class Niño {
    private String nombre;
    private boolean haDadoVuelta;

    public Niño(String nombre) {
        this.nombre = nombre;
        this.haDadoVuelta = false; // Inicialmente, el niño no ha dado vuelta
    }

    public String getNombre() {
        return nombre;
    }

    public boolean haDadoVuelta() {
        return haDadoVuelta;
    }

    public void darVuelta() {
        this.haDadoVuelta = true;
    }
}
