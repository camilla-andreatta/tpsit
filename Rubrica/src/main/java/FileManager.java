import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String path;

    public FileManager(String path) {
        this.path = path;
    }

    // salva la lista di contatti in un file CSV
    public void salvaContatti(List<Contatto> contatti) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            for (Contatto c : contatti) {
                // scrive nome,cognome,numero
                out.println(c.getNome() + "," + c.getCognome() + "," + c.getNumero());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // carica la lista di contatti dal file CSV
    public List<Contatto> caricaContatti() {
        List<Contatto> contatti = new ArrayList<>();
        File file = new File(path);

        // se il file non esiste, lo crea
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return contatti;
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parti = line.split(",", -1); // -1 per mantenere eventuali campi vuoti
                if (parti.length == 3) {
                    Contatto c = new Contatto(parti[0], parti[1], parti[2]);
                    contatti.add(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contatti;
    }
}
