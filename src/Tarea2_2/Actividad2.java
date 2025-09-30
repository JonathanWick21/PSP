package Tarea2_2;

import java.util.concurrent.atomic.AtomicInteger;


    public class Actividad2 {
        // PRUEBA: comenta 'volatile' y observa comportamientos erráticos.
        private static volatile boolean running = true; // señal de parada
        private static volatile int phase = 1;          // 1 = A, 2 = B

        // Nota: usamos AtomicInteger solo para contar; 'volatile' NO da atomicidad.
        private static final AtomicInteger processedA = new AtomicInteger();
        private static final AtomicInteger processedB = new AtomicInteger();

        public static void main(String[] args) throws InterruptedException {
            Runnable worker = () -> {
                String name = Thread.currentThread().getName();
                while (running) { // si 'running' no es volatile, pueden no ver false
                    int p = phase; // si 'phase' no es volatile, pueden “quedarse” en 1
                    if (p == 1) {
                        // Trabajo tipo A
                        simulatedWork(1000); // hace algo ligerito
                        processedA.incrementAndGet();
                    } else if (p == 2) {
                        // Trabajo tipo B (otro camino)
                        simulatedWork(700);
                        processedB.incrementAndGet();
                    } else {
                        // Fase desconocida: espera activa cortita
                        try {
                            Thread.sleep(1); //simula hacer algo
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                System.out.println(name + " -> stop");
            };

            Thread w1 = new Thread(worker, "Worker-1");
            Thread w2 = new Thread(worker, "Worker-2");
            Thread w3 = new Thread(worker, "Worker-3");
            w1.start(); w2.start(); w3.start();

            // Monitor de progreso (lee contadores y fase actual)
            Thread monitor = new Thread(() -> {
                try {
                    while (running) {
                        System.out.printf("Monitor: phase=%d | A=%d | B=%d%n",
                                phase, processedA.get(), processedB.get());
                        Thread.sleep(300);
                    }
                } catch (InterruptedException ignored) { }
                System.out.printf("Monitor fin: phase=%d | A=%d | B=%d%n",
                        phase, processedA.get(), processedB.get());
            }, "Monitor");
            monitor.start();

            // 1) Deja trabajar en FASE 1
            Thread.sleep(1500);

            // 2) Cambiamos a FASE 2 (si 'phase' no es volatile, algunos hilos seguirán en 1)
            System.out.println("MAIN: cambio phase = 2");
            phase = 2;

            // 3) Deja trabajar en FASE 2
            Thread.sleep(1500);

            // 4) Señal de parada (si 'running' no es volatile, algunos no verán false)
            System.out.println("MAIN: running = false");
            running = false;

            // Espera a que terminen
            w1.join(); w2.join(); w3.join();
            monitor.join();

            System.out.printf("TOTAL: A=%d | B=%d%n", processedA.get(), processedB.get());
            System.out.println("MAIN: terminado");
        }

        private static void simulatedWork(int spins) {
            // Trabajo CPU-bound muy ligero + pista al JIT de espera activa
            for (int i = 0; i < spins; i++) {
                try {
                    Thread.sleep(1); //simula hacer algo
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

