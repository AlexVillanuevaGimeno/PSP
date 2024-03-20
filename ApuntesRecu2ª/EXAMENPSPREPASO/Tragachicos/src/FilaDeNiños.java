import java.util.LinkedList;
import java.util.Queue;

public class FilaDeNiños {
    private Queue<Niño> fila;

    public Queue<Niño> getFila() {
        return fila;
    }

    public FilaDeNiños() {
        this.fila = new LinkedList<>();
    }

    public synchronized void agregarNiño(Niño niño) {
        fila.add(niño);
    }

    public synchronized Niño sacarNiño() {
        return fila.poll();
    }

    public synchronized void darVueltaNiño(Niño niño) {
        fila.add(niño); // Vuelve a colocar al niño en la fila para montarse nuevamente
    }

    public synchronized String mostrarFila() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fila de Niños: ");
        for (Niño niño : fila) {
            sb.append(niño.getNombre()).append(" ");
        }
        return sb.toString();
    }
}
