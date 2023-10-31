// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Thread hiloA = new Thread(new HiloX("A"));
        Thread hiloB = new Thread(new HiloX("B"));
        Thread hiloC = new Thread(new HiloX("C"));

        hiloA.start();
        hiloB.start();
        hiloC.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hiloA.interrupt();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        hiloA.interrupt();


        System.out.println("Soy el main. Adios mundo");
        }
    }
