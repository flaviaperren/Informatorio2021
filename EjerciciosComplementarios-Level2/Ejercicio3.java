import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**Crear una lista que contenga como elementos la numeración de cartas de una baraja francesa 
 * (solo los valores, no figuras). Se deberá cargar el ArrayList (en orden), 
 * imprimir, imprimir en orden inverso (reverse), desordenar (mezclar) el arrayList y volver a imprimir.
 */

public class Ejercicio3 {
    public static void main(String[] args) {
        List <String> cartas = new ArrayList<>();
        baraja(cartas);
        System.out.println("Baraja de cartas francesa: " + cartas); 
        
        Collections.reverse(cartas);
        System.out.println("Baraja de cartas en orden inverso: " + cartas); 
        
        Collections.shuffle(cartas);
        System.out.println("Baraja de cartas mezclada: " + cartas); 
        
    }

    public static void baraja(List <String> cartas) {
        cartas.add("As");
        cartas.add("2");
        cartas.add("3");
        cartas.add("4");
        cartas.add("5");
        cartas.add("6");
        cartas.add("7");
        cartas.add("8");
        cartas.add("9");
        cartas.add("10");
        cartas.add("J");
        cartas.add("Q");
        cartas.add("K");
    }

    
    
}
