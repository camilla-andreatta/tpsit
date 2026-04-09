public class Main {
    public static void main(String[] args) throws InterruptedException {

        Tabella tabella = new Tabella();

        Giocatore player1 = new Giocatore('X', tabella, 0);
        Giocatore player2 = new Giocatore('O', tabella, 1);

        System.out.println("Tabella iniziale:");
        tabella.stampaTabella();
        System.out.println();

        player1.start();
        player2.start();

        player1.join();
        player2.join();

        System.out.println("Partita terminata");
    }
}