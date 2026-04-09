import java.util.Random;

public class Ordine extends Thread{
    public int numPizze;
    public Forno forno;
    public int id;

    public void numOrdiniCasuale(){
        Random rand = new Random();
        this.numPizze = rand.nextInt(1,5);
    }

    public Ordine (Forno forno, int id){
        this.id = id;

        this.forno = forno;
        numOrdiniCasuale();
    }

    @Override
    public void run() {
        try {
            System.out.println("L'ordine " + id + " richiede: " + numPizze + " pizze.");
                forno.cucina(this.id, this.numPizze);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
