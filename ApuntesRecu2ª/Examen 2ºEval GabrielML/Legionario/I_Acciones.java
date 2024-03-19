import java.util.Arrays;
import java.util.Random;

public interface I_Acciones {
    //Métodos para el cifrado y descifrado.
    String[] abecedario = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    default String descifrar(String t_cifrado) {
        StringBuilder t_plano = new StringBuilder();
        for (char c : t_cifrado.toCharArray()) {
            if (Character.isLetter(c)) {
                String letra = String.valueOf(c).toLowerCase();
                int indice = (Arrays.asList(abecedario).indexOf(letra) - 3 + 26) % 26;
                if (Character.isUpperCase(c)) {
                    t_plano.append(abecedario[indice].toUpperCase());
                } else {
                    t_plano.append(abecedario[indice]);
                }
            } else {
                t_plano.append(c);
            }
        }
        return t_plano.toString();
    }
    default String cifrar(String t_plano) {
        StringBuilder t_cifrado = new StringBuilder();
        for (char c : t_plano.toCharArray()) {
            if (Character.isLetter(c)) {
                String letra = String.valueOf(c).toLowerCase();
                int indice = (Arrays.asList(abecedario).indexOf(letra) + 3) % 26;
                if (Character.isUpperCase(c)) {
                    t_cifrado.append(abecedario[indice].toUpperCase());
                } else {
                    t_cifrado.append(abecedario[indice]);
                }
            } else {
                t_cifrado.append(c);
            }
        }
        return t_cifrado.toString();
    }

    
    //Método para generar números aleatorios.
    default int crearAleatorio(int maximo){
        Random random = new Random();
        return random.nextInt(maximo) + 1;
    }
}