public class HiloX extends Thread{

    private String letra;
    private Thread thread;

    public HiloX(String letra) {this.letra = letra;}

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " Soy el hilo " + letra);
            try {
                Thread.sleep(1000);
             //   System.out.println("\n ----------------------------------------------------");
            } catch (InterruptedException e) {
                System.out.println(" Soy el hilo " + letra + " me acaban de despertar");
            }

        }
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
