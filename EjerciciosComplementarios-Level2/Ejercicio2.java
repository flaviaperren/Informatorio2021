import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**Crear un ArrayList, agregar 5 números enteros. 
 * Luego, agregar un número entero al principio de la lista y otro al final. 
 * Por último, iterar e imprimir los elementos de la lista y el tamaño de la misma 
 * (antes y después de agregar a en la primera y última posición).
 */

public class Ejercicio2 {
    public static void main(String[] args) {
            List <Integer> numeros = new ArrayList<>();
            listaNumeros(numeros);
            
    } 
                 
    public static void listaNumeros(List<Integer> numeros) {
        Scanner scan = new Scanner(System.in);
        for (int i=0; i<5; i++) {
            System.out.print("Ingrese un número: ");
            int n= scan.nextInt();
            numeros.add(n); 
        }
        System.out.println(numeros);
        System.out.println("Tamaño de la lista antes: " + numeros.size());
       
        System.out.println("Ingrese dos números: ");
        int n1 = scan.nextInt();
        int n2 = scan.nextInt();
        numeros.add(0, n1);
        numeros.add(n2);
        scan.close();  
        System.out.println(numeros);
        System.out.println("Tamaño de la lista después: " + numeros.size());
    } 
}
