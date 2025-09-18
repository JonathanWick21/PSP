import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        FileWriter f = null;
//        try {
//            f = new FileWriter("file.txt");
//            f.write("texto");
//        } catch (IOException e){
//            System.err.println();
//        } finally {
//            try {
//                f.close();
//            } catch (IOException e) {
//                System.err.println();
//            }
//        }
//
//        //Esto hace lo mismo que el el codigo de arriba
//        try (FileWriter f2 = new FileWriter("")){
//            f.write("texto");
//        } catch (IOException e) {
//            System.err.println();
//        }

        //Programación secuencial -> una detrás de otra
        //Concurrencia -> más de un proceso en un periodo de tiempo
        //Paralelismo -> más de un proceso en ejecución al mismo tiempo
        //Programación distribuida -> distintas máquinas

        //Planificación
        //FIFO -> First in first out
        //SFJ -> shortest job first
        //RR -> round robin (quantum)
        //Priority

// /////////////
    try {

        //En windows: "cmd", "/c", "dir"
        //Linux: "sh", "-c", "ls"
        //Opcion 1
        String[] commands = {"sh"};
        Process p = Runtime.getRuntime().exec(commands);

        //Opcion 2
        ProcessBuilder pb = new ProcessBuilder("ping", "-n", "3", "wikipedia.org");
        Process p2 = pb.start();

        int code = p2.waitFor(); //Bloqueante
        System.out.println(code);

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}


    }
