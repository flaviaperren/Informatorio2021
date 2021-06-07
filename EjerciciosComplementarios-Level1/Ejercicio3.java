import java.util.Scanner;

/**Confeccionar un programa que dado un número entero como dato de entrada 
 * imprima la secuencia de números (incrementos de 1) de la siguiente forma:
 * 1
 * 1 2
 * 1 2 3
 * 1 2 3 4
 * 1 2 3 4 5
 */

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese un número: ");
        int nro = scan.nextInt();
        scan.close();
        
        for (int n=1; n<=nro; n++) {
            for(int i=1; i<=n; i++) {
                System.out.print(i);
            }
            System.out.println("");
            
        
        }
        
    }
    
}
