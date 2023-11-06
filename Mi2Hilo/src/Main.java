// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        HiloX hiloA = new HiloX("A");
        HiloX hiloB = new HiloX("B");
        HiloX hiloC = new HiloX("C");
        hiloA.setThread(hiloB);
        hiloB.setThread(hiloC);
        hiloC.setThread(hiloA);
//        hiloA.setPriority(Thread.MAX_PRIORITY);
//        hiloB.setPriority(Thread.NORM_PRIORITY);
//        hiloC.setPriority(Thread.MIN_PRIORITY);
        hiloA.start();
        hiloB.start();
        hiloC.start();





        System.out.println("Soy el main. Adios mundo");
        }
    }
