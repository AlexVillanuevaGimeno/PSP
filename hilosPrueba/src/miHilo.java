public class miHilo extends Thread {
    private static int contador =0;

    private int id;

    public miHilo(){
        this.id=contador++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void run(){
        System.out.println("Hola este es el Hilo: " + id);
    }
}
