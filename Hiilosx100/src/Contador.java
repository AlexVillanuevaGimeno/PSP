public class Contador {
    private static int valor = 0;

    public static synchronized void incrementar() {
        valor++;
    }

    public static int obtenerValor() {
        return valor;
    }

}
