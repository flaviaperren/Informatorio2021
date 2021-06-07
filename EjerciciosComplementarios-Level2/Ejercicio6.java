import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

/**Se dispone de una lista de Empleados, de cada empleado se conoce:
 * Nombre y Apellido
 * DNI
 * horasTrabajadas
 * valorPorHora
 * Todos los empleados están cargados en un Set (HashSet), 
 * se desea calcular el sueldo (horasTrabajadas x valorPorHora) de toda esa lista 
 * para luego almacenar en un Map (o Diccionario) donde la clave (key) es el dni 
 * y el valor (value) es el sueldo calculado. 
 */

public class Ejercicio6 {

    public static void main(String[] args) {
        
        Set<Empleado> lista= new HashSet<Empleado>();
        Empleado emp1= new Empleado("Juan Perez", "1111", "250", "310");
        Empleado emp2= new Empleado("Gladys Sánchez", "3333", "420", "250");
        Empleado emp3= new Empleado("Hugo Lopez", "2222", "330", "340");
        Empleado emp4= new Empleado("Sandra villalba", "4444", "170","390");
        Empleado emp5= new Empleado("Pablo Gomez", "5555", "280", "210");
        lista.add(emp1);
        lista.add(emp2);
        lista.add(emp3);
        lista.add(emp4);
        lista.add(emp5);

        System.out.println("Nombre y Apellido - DNI - Horas Trabajadas - Valor por Hora: ");
        for( Empleado l:lista){
            System.out.println(l);
        }

        Map <String, Integer> sueldo = new HashMap<>();
        for (Empleado l:lista){
            sueldo.put(l.dni, l.calcularSueldo());
        }

        for(Map.Entry<String, Integer> registro: sueldo.entrySet()) {
            System.out.println("dni: " + registro.getKey() + " - Sueldo: " + registro.getValue());
        }
}

    
}
class Empleado {
    private String nombreApellido;
    public String dni;
    private String horasTrabajadas;
    private String valorHora;

    public Empleado(String nombreApellido, String dni, String horasTrabajadas, String valorHora) {
        this.nombreApellido = nombreApellido;
        this.dni = dni;
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    }

    
    public int calcularSueldo() {
        return(Integer.parseInt(this.horasTrabajadas) * Integer.parseInt(this.valorHora));
    }

    @Override
    public String toString() {
        return String.valueOf(this.nombreApellido) + "-" + (this.dni) + "-" + (this.horasTrabajadas)
        + "-" + (this.valorHora);
    }
}

