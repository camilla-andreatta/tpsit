public class Main {
    public static void main(String[] args) {
        Forno forno = new Forno();

        for (int i = 1; i <= 5; i++) {
            new Ordine(forno, i).start();
        }
    }
}