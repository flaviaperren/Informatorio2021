import java.util.Scanner;

/**Se desea una aplicación que solicite 2 números enteros y realice 
 * la operación de potencia (sin uso de librerías).
 */

public class Ejercicio6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese un número: ");
        int nro = scan.nextInt();
        System.out.print("Ingrese la potencia: ");
        int pot = scan.nextInt();
        scan.close();
        int res=1;

        for (int i=1; i<=pot; i++) {
            res=nro*res;
        }
        System.out.println("El resultado es: " + res);
    }
}
