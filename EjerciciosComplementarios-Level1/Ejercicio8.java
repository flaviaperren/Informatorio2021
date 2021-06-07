import java.util.Scanner;

/**Crear una aplicación que solicite de entrada los datos de una persona en este orden:
 * Nombre y Apellido
 * Edad
 * Dirección
 * Ciudad
 * Luego imprimirá el siguiente mensaje:
 * {Ciudad} - {Dirección} - {Edad} - {Nombre y Apellido}
 */

public class Ejercicio8 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese Nombre y Apellido: ");
        String nombreApellido = scan.nextLine();
        System.out.print("Ingrese Edad: ");
        String edad = scan.nextLine(); 
        System.out.print("Ingrese Dirección: ");
        String direccion = scan.nextLine();
        System.out.print("Ingrese Ciudad: ");
        String ciudad = scan.nextLine(); 
        scan.close();

        System.out.println(ciudad + " - " + direccion + " - " + edad + " - " + nombreApellido);
    }
}
