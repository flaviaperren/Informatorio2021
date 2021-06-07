import java.util.Scanner;

/**Realizar un Programa que dado un String de entrada en minúsculas lo 
 * convierta por completo a mayúsculas. Sin uso de métodos o librerías que realicen toUppercase().
 */

public class Ejercicio7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese un frase: ");
        String frase = scan.nextLine();
        scan.close();

        char strArr[] = frase.toCharArray();
        for(int i=0; i < strArr.length; i++) {
            if(strArr[i] >='a' && strArr[i] <='z') {
                strArr[i] = (char) ((int) strArr[i] - 32);
            }
        }
        for(int i=0; i < strArr.length; i++) {
            System.out.print(strArr[i]);
        }
        
    }
}
