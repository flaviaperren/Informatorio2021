import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**Crear un ArrayList y cargarlo con tus ciudades favoritas de Argentina, 
 * luego imprimir por pantalla el ranking
 */

public class Ejercicio1 {
    public static void main(String[] args) {
        List <String> ciudad = new ArrayList<>();
        listaCiudad(ciudad);
        System.out.println("Ranking de ciudades:");
        for(String cd:ciudad) {
            System.out.println((ciudad.indexOf(cd) + 1) + " --> " + cd);    
        }     
    }

    public static void listaCiudad(List<String> ciudad) {
        Scanner scan = new Scanner(System.in);
        boolean seguir = true;
        while (seguir) {
        System.out.print("Ingrese una ciudad o 0 para salir: ");
        String cd = scan.nextLine();
        if (cd.equals("0")) {
            seguir = false; 
            scan.close();
        } else {
            ciudad.add(cd);   
        }
        } 
    }
                
}