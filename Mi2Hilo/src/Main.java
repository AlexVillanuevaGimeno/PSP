// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ThreadX threadA = new ThreadX('A');
        ThreadX threadB = new ThreadX('B');
        ThreadX threadC = new ThreadX('C');

        threadA.setNextThread(threadB);
        threadB.setNextThread(threadC);
        threadC.setNextThread(threadA);

        ThreadX.setCurrentThread(threadA);
        threadA.start();
        threadB.start();
        threadC.start();


        System.out.println("I'm the main class");
    }
}
