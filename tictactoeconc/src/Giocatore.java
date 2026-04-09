import java.util.Random;

public class Giocatore extends Thread {
    char simbolo;
    Tabella tabella;
    Random rnd = new Random();
    int indiceTurno;

    Giocatore(char simbolo, Tabella tabella, int indiceTurno) {
        this.simbolo = simbolo;
        this.tabella = tabella;
        this.indiceTurno = indiceTurno;
    }

    public void posizionaPedina() throws InterruptedException {
        //giocatore pensa tra i 500 e i 1000 ms
        long tempoPensiero = 500 + rnd.nextInt(501); // [500, 1000] ms
        Thread.sleep(tempoPensiero);

        synchronized (tabella) {

            //aspetta il suo turno
            while (tabella.inCorso && tabella.turno != indiceTurno) {
                tabella.wait();
            }

            // ricontrolla se la partita è ancora in corso
            if (!tabella.inCorso) return;

            int riga, colonna;
            do {
                riga = rnd.nextInt(3);
                colonna = rnd.nextInt(3);
            } while (tabella.tabella[riga][colonna] != '-');

            tabella.tabella[riga][colonna] = simbolo;

            System.out.println("Giocatore " + simbolo
                    + " gioca in (" + riga + ", " + colonna + ")");
            System.out.println("Stato attuale:");
            tabella.stampaTabella();
            System.out.println();

            if (tabella.checkWin(simbolo)) {
                System.out.println("Il giocatore " + simbolo + " ha vinto!");
                tabella.inCorso = false;
                tabella.notifyAll(); // sveglia l'altro thread in attesa
                return;
            }

            if (tabella.isPieno()) {
                System.out.println("Pareggio!");
                tabella.inCorso = false;
                tabella.notifyAll();
                return;
            }

            tabella.turno = 1 - indiceTurno;
            tabella.notifyAll(); // sveglia l'altro thread
        }
    }

    @Override
    public void run() {
        while (tabella.inCorso) {
            try {
                posizionaPedina();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}