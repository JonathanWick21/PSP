import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CuentaBanco {

    private double balance;
    private String nombre;
    private String calle;
    private String telefono;

    private final Object lockBalance = new Object();
    private final Object lockDatosFiscales = new Object();


    public CuentaBanco(double balance, String nombre, String calle, String telefono) {
        this.balance = balance;
        this.nombre = nombre;
        this.calle = calle;
        this.telefono = telefono;
    }

    public void actualizarDatosFiscales(String nombre, String calle, String telefono){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this.lockDatosFiscales) {
            this.nombre = nombre;
            this.calle = calle;
            this.telefono = telefono;

            System.out.println("Datos fiscales actualizados");
        }
    }

    public synchronized void retirar(double cantidad){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        synchronized (this.lockBalance) {
            if (cantidad <= this.balance) {
                double balanceActual = this.balance;
                this.balance -= cantidad;
                System.out.printf("Balance actual: %.2f, retiradad de: %.2f Cuenta: %.2f \n", balanceActual, cantidad, this.balance);
            } else {
                System.out.println("Fondos insuficientes");
            }
        }
    }


    public  void ingresar(double cantidad) {
        try {
            Thread.sleep(3000); //simular la operación
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        synchronized (this.lockBalance) {
            double balanceActual = this.balance;
            this.balance += cantidad;
            System.out.printf("Balance actual: %.2f, ingreso de: %.2f \n", balanceActual, cantidad);
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCalle() {
        return calle;
    }

    public String getNombre() {
        return nombre;
    }

    public synchronized double getBalance() {
        return balance;
    }
}
