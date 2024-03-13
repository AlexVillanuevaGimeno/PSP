public class Main {
    public static void main(String[] args) {
        int puerto =  10000;
        for (int i = 0; i < 4; i++) {
            Centurion centurion = new Centurion("localhost", puerto);
        }
        puerto = puerto +100;

    }
}