
class OperacionIngreso implements Runnable{
    private CuentaBanco cuentaBanco;
    private  double cantidad;

    public OperacionIngreso(CuentaBanco cuentaBanco, double cantidad) {
        this.cuentaBanco = cuentaBanco;
        this.cantidad = cantidad;
    }


    @Override
    public void run() {
        cuentaBanco.retirar(cantidad);
    }
}


public class SincroHilos {
    public static void main(String[] args) {

        CuentaBanco cuentaJon = new CuentaBanco(10000, "Jon", "Elche", "123456789");

        Runnable r = () -> {
            cuentaJon.retirar(2500);
        };

        Runnable r2 = () -> {
            cuentaJon.ingresar(5000);
        };

        Thread hilo1 = new Thread(r);
        hilo1.start();


        Thread hilo2 = new Thread(new OperacionIngreso(cuentaJon, 5000));
        hilo2.start();

        Thread hilo3 = new  Thread(r2);
        hilo3.start();

        Thread hilo4 = new Thread(() -> cuentaJon.retirar(5000));
        hilo4.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Balance final esperado: 5000, balance obtenido: " + cuentaJon.getBalance());
    }
}
