public class Main {
    public static void main(String[] args) {
        int puerto =  5000;
        for (int i = 0; i < 4; i++) {
            Centurion centurion = new Centurion("localhost", puerto);
        }

    }
}