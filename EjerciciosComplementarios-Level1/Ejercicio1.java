import java.util.Scanner;

/**Solicitar por consola el nombre del usuario e imprimir por pantalla el siguiente 
 * mensaje “HOLA {USUARIO}!!!” */

public class Ejercicio1 {
    public static void main(String[] args) {
        System.out.print("Ingrese su nombre: ");
        Scanner scan = new Scanner(System.in);
        String nombre = scan.nextLine();
        scan.close();
        System.out.println("¡Hola " + nombre + "!");
        
    }
}