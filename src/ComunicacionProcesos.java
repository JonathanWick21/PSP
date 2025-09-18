import java.io.*;
import java.util.Random;

public class ComunicacionProcesos {
    public static void main(String[] args) {

        //Proceso

        ProcessBuilder pb = new ProcessBuilder("sh");
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        try {
            Process p = pb.start();
            // salida del proceso (output)
            //error -> input
            //estandar --> input

            //entrada del proceso (input)
            //intelliJ --> output
//            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
//                String linea;
//                while ((linea = bufferedReader.readLine()) != null){
//                    System.out.println(linea);
//                }
//            }
//            InputStream is = p.getInputStream();
//            BufferedReader br = new BufferedReader(
//                    new InputStreamReader(is)
//            );
//            String line;
//            while ((line = br.readLine()) != null){
//                System.out.println(line);
//            }
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                bw.write("ls");
                bw.close();
                String line;
                while ((line = br.readLine()) != null){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
