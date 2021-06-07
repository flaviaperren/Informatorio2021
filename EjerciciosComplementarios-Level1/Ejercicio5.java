import java.util.Scanner;

/**Se desea una aplicación que solicite 2 números enteros y realice la operación de 
 * multiplicación por sumas sucesivas (sin uso de librerías).
 */

public class Ejercicio5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese un número: ");
        int nro1 = scan.nextInt();
        System.out.print("Ingrese otro número: ");
        int nro2 = scan.nextInt();
        scan.close();
        int multip=0;

        for (int i=1; i<=nro2; i++) {
            multip = nro1 + multip;
            
        }
        System.out.println(nro1 + " x " + nro2 + " = " + multip);
    }
}
