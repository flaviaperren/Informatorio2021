import java.util.List;
import java.util.ArrayList;

/**Dados 2 ArrayList que contienen horas-trabajadas (array1) y valor-por-hora(array2) 
 * del resumen de carga de horas semanal de un empleado. 
 * Se debe generar otra lista que contenga los totales y luego imprimir el total final a cobrar.
 */

public class Ejercicio5 {
    public static void main(String[] args) {
        List <Integer> horas = new ArrayList<>();
        horasTrabajadas(horas);
        System.out.println("Horas trabajadas de lunes a viernes: " + horas);
        
        List <Integer> valor = new ArrayList<>();
        valorHora(valor);
        System.out.println("Valor por hora: " + valor);

        List <Integer> total = new ArrayList<>();
        for(int i=0; i<horas.size(); i++){
            int valorTotal = (horas.get(i)) * (valor.get(i));
            total.add(valorTotal);
        }
        System.out.println("Valor total de las horas trabajadas por día: " + total);

        int sumatoria=0;
        for(int i=0; i<total.size(); i++) {
            int valorFinal = total.get(i);
            sumatoria = sumatoria + valorFinal;
        }
        System.out.println("Valor total: " + sumatoria);
    }

    public static void horasTrabajadas(List <Integer> horas) {
        horas.add(8);
        horas.add(5);
        horas.add(9);
        horas.add(6);
        horas.add(8);
    }

    public static void valorHora(List <Integer> valor) {
        valor.add(450);
        valor.add(320);
        valor.add(470);
        valor.add(500);
        valor.add(390);

    }

}
