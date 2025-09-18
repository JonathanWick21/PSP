import java.io.IOException;

public class Ej1_5 {
    public static void main(String[] args) {
        try {
            String[] comandos = {"sh", "cd", "paralalepipedo"};
            Process process = Runtime.getRuntime().exec(comandos);
            int codigo = process.waitFor();
            System.out.println(codigo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("macaco");
            Process process = processBuilder.start();
            int code = process.waitFor();
            System.out.println(code);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }
}
