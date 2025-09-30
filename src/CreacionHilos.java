import java.util.concurrent.TimeUnit;

class Hilo extends Thread {

    public String name;

    public Hilo(String name){
        super.setName(name);
    }

    @Override
    public void run() {
        System.out.println("Soy el hilo " + Thread.currentThread().getName());
    }
}

class Hilo2 implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Soy hilo 2");
    }
}


public class CreacionHilos {


    public static void main(String[] args) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("suma...");
            } catch (InterruptedException e) {
                System.err.println("he sido interrupted");
                Thread.currentThread().interrupt();
                break;
            }
        }
        Hilo h = new Hilo("hilo 1");
        h.start();

        h.interrupt(); //Coloca una flag de interrupt y lanza una excepcion de InterruptedE

        try {
            h.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Soy el hilo principal");

        Thread h2 = new Thread(new Hilo2());
        h2.start();

        Runnable r = () -> System.out.println("Soy hilo " + Thread.currentThread().getName());

        Thread t = new Thread(r, "hilo3");
        t.start();
        //proceso --> su heap, sus registros, sus operaciones entrada/salido(i/o)
        //hilos comparten heap, stack(pila)

        //java 21
        //Loom --> hilos virtuales

    }
}
