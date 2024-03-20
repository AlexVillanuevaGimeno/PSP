package cliente;

public class ClienteMain {

    public static void main(String[] args) {
        int i = 1;
        int puerto = 4500;
        while(puerto <10000){
            Cliente cliente = new Cliente("localhost",puerto,i);
            puerto += 10;
            i = i+1;
        }


    }

}

