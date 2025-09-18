import java.io.IOException;

public class Ej1_6 {
    public static void main(String[] args) {

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("mkdir", "./nuevo_directorio");
            Process p = processBuilder.start();

            int codigo1 = p.waitFor();

            ProcessBuilder processBuilder2 = new ProcessBuilder("rmdir", "./nuevo_directorio");
            Process p2 = processBuilder2.start();
            int codigo2 = p2.waitFor();

            System.out.println("Codigo 1 = " + codigo1);
            System.out.println("Codigo 2 = " + codigo2);

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }
}
