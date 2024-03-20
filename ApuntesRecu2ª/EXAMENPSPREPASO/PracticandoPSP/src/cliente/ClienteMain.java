package cliente;

import servidor.Servidor;

public class ClienteMain {

    public static void main(String[] args) {
        int puerto = 4500;
        for (int i = 1; i <= 5; i++) {
                Cliente cliente = new Cliente("localhost",puerto,i);
                puerto += 100;
            }


        }

    }

