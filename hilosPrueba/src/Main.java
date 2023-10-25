public class Main {
    public static void main(String[] args) {
    miHilo hilo = new miHilo();
    hilo.start();
    miHilo hilo2 = new miHilo();
    hilo2.start();
    miHilo hilo3 = new miHilo();
    hilo3.start();
    miHilo hilo4 = new miHilo();
    hilo4.start();
    miHilo hilo5 = new miHilo();
    hilo5.start();

    try {
        Thread.sleep(100);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
        System.out.println("ADIOS MUNDO CRUEL");

    }
}