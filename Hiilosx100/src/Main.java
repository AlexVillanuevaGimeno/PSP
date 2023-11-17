import java.sql.SQLOutput;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Contador contadorComun = new Contador();

        for (int i = 1; i <= 100; i++) {
            Thread thread = new Thread(new Hilo(contadorComun, i));
            thread.start();
        }

    }
}