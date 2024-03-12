//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int puerto = 4500;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Legionario legionario = new Legionario("localhost",puerto);
                legionario.start();
                try {
                    legionario.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            puerto = puerto + 100;
        }
        }
}