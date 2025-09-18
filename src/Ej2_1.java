import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej2_1 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("ls", "-l");

        try {
            Process p = pb.start();

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                }
            }
            System.out.println("Codigo " + p.waitFor());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
