import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**Crear una función que dado 2 argumentos (int, siendo el primero menor al segundo), 
 * nos devuelva un array de Strings. Con la secuencia de números enteros de principio a final. 
 * Pero si el número es multiplo de 2 colocara el valor “Fizz”, si es múltiplo de 3 “Buzz” 
 * y si es a la vez múltiplo de ambos colocara “FizzBuzz”. 
 * Observacion: Los 2 argumentos indican con que valor se arranca a calcular y el segundo 
 * con qué valor debe frenar (no se incluye en el cálculo)
 * Ejemplo: (1, 5) ----> calculará valores de 1, 2, 3, 4 
 */


 /*Los números son ingresados en orden ascendente, primero el más chico y luego el más grande*/
public class Ejercicio7 {
    public static void main(String[] args) {
        List<String> numeros= new ArrayList<>();
        listaNumeros(numeros);
        System.out.println(numeros);
    }

    public static void listaNumeros(List <String> numeros) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese dos números: ");
        int nro1 = scan.nextInt();
        int nro2 = scan.nextInt();
        scan.close();
        
        Integer nro=(nro1 - 1);
        for (int i=nro1; i<nro2; i++) {
            nro=nro + 1;
            if(nro % 2 == 0 && nro % 3 == 0) {
                numeros.add("FizzBuzz");
            } else if(nro % 2 == 0) {
                numeros.add("Fizz");
            } else if(nro % 3 == 0) {
                numeros.add("Buzz");
            } else {
                numeros.add(nro.toString());
            }  
        }
        

    }
}
