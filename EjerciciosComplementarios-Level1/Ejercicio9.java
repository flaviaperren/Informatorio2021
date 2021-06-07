import java.util.Scanner;

/**Dado un String de entrada (frase, texto, etc) calcular la cantidad 
 * de veces que aparece una letra dada.
 */

public class Ejercicio9 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese una frase: ");
        String frase = scan.nextLine();
        System.out.print("Ingrese una letra: ");
        char letra = scan.next().charAt(0);
        scan.close();
        char[] partes = frase.toCharArray();
        int contador = 0;
        

        for(int i=0; i< frase.length(); i++) {
            
            if( letra == partes[i]) {
                contador ++;
            }
        }
        System.out.println("El número de veces que aparece la letra '" + letra + "' es: " + contador);

    }
}
