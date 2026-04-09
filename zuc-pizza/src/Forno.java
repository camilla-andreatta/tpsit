import java.util.concurrent.Semaphore;

public class Forno {
    private final Semaphore semaforo;

    public Forno() {
        semaforo = new Semaphore(4);
    }

    public int availableSlots() {
        return semaforo.availablePermits();
    }

    public void cucina(int id, int numPizze) throws InterruptedException {
        semaforo.acquire(numPizze);
        System.out.println("Ordine " + id + ": " + numPizze + " pizze infornate. "
                + "Posti liberi rimasti: " + availableSlots());
        Thread.sleep(5000);
        System.out.println("Ordine " + id + ": tutte le pizze pronte!");
        semaforo.release(numPizze);
        System.out.println("Ordine " + id + " completato! Posti liberi: " + availableSlots());
    }
}