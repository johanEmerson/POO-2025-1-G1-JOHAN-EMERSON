import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    Persona[] personas;
    Scanner sc = new Scanner(System.in);


    public void registrarAsistencia() {
        System.out.println("Ingrese la cantidad de estudiantes matriculados");
        int cantidad = sc.nextInt();
        personas = new Persona[cantidad];
        for (int i = 0; i < personas.length; i++) {
            Persona personaX = new Persona();
            System.out.println("Ingrese el nombre del persona");
            personaX.nombre = sc.next();
            System.out.println("Ingrese el estado del persona (P=Presente, F=Falta)");
            personaX.estado = sc.next();
            personas[i] = personaX;
        }
        System.out.println("Reporte de asistencia:");
        for (int i = 0; i < personas.length; i++) {
            System.out.println(personas[i].nombre + "\t" + personas[i].estado);
        }
    }


    public static void main(String[] args) {
        // new Main().registrarAsistencia();
        Main main = new Main();
        main.registrarAsistencia();
    }
}