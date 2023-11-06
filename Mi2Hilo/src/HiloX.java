public class HiloX extends Thread{

    private String letra;
    private Thread thread;

    public HiloX(String letra) {this.letra = letra;}

    @Override
    public synchronized void run() {
        for (int i = 0; i < 10; i++) {
            if (letra.equals("A")){
                System.out.println(i + " Soy el hilo " + letra);
                try {
                    thread.interrupt();
                    this.wait();
                } catch (Exception e) {
                    System.out.println("Me han despertado soy B");
                }
            } else if (letra.equals("B")) {
                System.out.println(i + " Soy el hilo " + letra);
                try {
                    thread.interrupt();
                    this.wait();
                } catch (Exception e) {
                    System.out.println("Me han despertado soy C");

                }
            } else if (letra.equals("C")) {
                System.out.println(i + " Soy el hilo " + letra);
                try {
                    thread.interrupt();
                    this.wait();
                } catch (Exception e) {
                    System.out.println("Me han despertado soy A");
                }
            }




//            try {
//                Thread.sleep(1000);
//             //   System.out.println("\n ----------------------------------------------------");
//            } catch (InterruptedException e) {
////                System.out.println(" Soy el hilo " + letra + " me acaban de despertar\n");
//            }

        }

    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }
}
