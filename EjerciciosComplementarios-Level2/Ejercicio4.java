import java.util.List;
import java.util.ArrayList;

/**Cargar un arrayList con 12 nombres de estudiantes (String), 
 * luego separarlos en 3 cursos (3 arrayList) e imprimir dichos cursos.
 */

public class Ejercicio4 {
    public static void main(String[] args) {
        List <String> estudiantes = new ArrayList<>();
        listaEstudiantes(estudiantes);
        System.out.println(estudiantes);
        List <String> curso1= estudiantes.subList(0,4);
        List <String> curso2= estudiantes.subList(4,8);
        List <String> curso3= estudiantes.subList(8,12);
        System.out.println("Curso 1: " + curso1); 
        System.out.println("Curso 2: " + curso2); 
        System.out.println("Curso 3: " + curso3); 
    }
        public static void listaEstudiantes(List<String> estudiantes) {
            estudiantes.add("Pedro Picapiedras");
            estudiantes.add("Dexter");
            estudiantes.add("Lisa Simpson");
            estudiantes.add("Penélope Glamour");   
            estudiantes.add("Felix"); 
            estudiantes.add("Jhonny Bravo"); 
            estudiantes.add("Mickey Mouse");
            estudiantes.add("Bart Simpson");
            estudiantes.add("Clarence");
            estudiantes.add("Sailor Moon");
            estudiantes.add("Bugs Bunny");
            estudiantes.add("Daisy");
        }
            
        
}

