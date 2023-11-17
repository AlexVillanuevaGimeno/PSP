public class Hilo extends  Thread{
    private Contador contador;
    private int id;

    public Hilo(Contador contador, int id) {
        this.contador = contador;
        this.id = id;
    }

    @Override
    public void run() {
            contador.incrementar();
            System.out.println("Hilo " + id + ", Contador: " + contador.obtenerValor());

    }
}
