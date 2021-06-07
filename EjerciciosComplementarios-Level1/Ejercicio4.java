import java.util.Scanner;

/**Realizar un programa que calcule el factorial de un número:
 * n! = 1 x 2 x 3 x 4 x 5 x … x (n-1) x n.
 */

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese un número: ");
        int nro = scan.nextInt();
        scan.close();
        int fact = 1;
        
        for(int i=2; i<=nro; i++) {
            fact= fact*i;
        }
        
        System.out.println("El factorial de " + nro + " es: " + fact);
        
    }
}
