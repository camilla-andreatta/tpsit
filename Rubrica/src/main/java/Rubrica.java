import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Rubrica extends JFrame implements ActionListener {

    List<Contatto> contacts = new ArrayList<>();

    DefaultListModel<Contatto> modello = new DefaultListModel<Contatto>();
    JList<Contatto> rubrica = new JList<>(modello);
    JScrollPane scroll = new JScrollPane(rubrica);

    JButton aggiungi = new JButton("Aggiungi");
    JButton modifica = new JButton("Modifica");
    JButton elimina = new JButton("Elimina");

    String path = "src/main/resources/rubrica.csv";
    FileManager fileManager = new FileManager(path);

    Rubrica() {
        // caricamento dei dati dal file csv all'avvio
        contacts = fileManager.caricaContatti();
        modello.addAll(contacts);

        setTitle("Rubrica");
        setBounds(20, 30, 500, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Layout principale
        setLayout(new BorderLayout());

        // pannello superiore
        JPanel pannelloSup = new JPanel();
        pannelloSup.setLayout(new BorderLayout());
        pannelloSup.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Sotto-pannello per i BOTTONI
        JPanel pannelloBottoni = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        aggiungi.setActionCommand("Aggiungi");
        aggiungi.addActionListener(this);
        pannelloBottoni.add(aggiungi);

        modifica.setActionCommand("Modifica");
        modifica.addActionListener(this);
        pannelloBottoni.add(modifica);

        elimina.setActionCommand("Elimina");
        elimina.addActionListener(this);
        pannelloBottoni.add(elimina);

        pannelloSup.add(pannelloBottoni, BorderLayout.NORTH);

        // barra di ricerca
        JPanel pannelloSearch = new JPanel(new BorderLayout());
        pannelloSearch.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JTextField searchField = new JTextField();
        pannelloSearch.add(new JLabel("   Cerca: "), BorderLayout.WEST);
        pannelloSearch.add(searchField, BorderLayout.CENTER);

        pannelloSup.add(pannelloSearch, BorderLayout.SOUTH);

        add(pannelloSup, BorderLayout.NORTH);

        // lista contatti
        JPanel pannelloInf = new JPanel(new BorderLayout());
        pannelloInf.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scroll = new JScrollPane(rubrica);
        pannelloInf.add(scroll, BorderLayout.CENTER);

        add(pannelloInf, BorderLayout.CENTER);

        // renderer personalizzato per le celle della lista
        this.rubrica.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
                        cellHasFocus);
                Contatto c = (Contatto) value;
                label.setText(c.getNome() + " " + c.getCognome() + " - " + c.getNumero());
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                label.setFont(new Font("Arial", Font.PLAIN, 16));
                return label;
            }
        });

        rubrica.setFixedCellHeight(30);

        // filtro di ricerca
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void aggiornaFiltro() {
                String text = searchField.getText().toLowerCase();
                modello.clear();
                for (Contatto c : contacts) {
                    if (c.getNome().toLowerCase().contains(text) ||
                            c.getCognome().toLowerCase().contains(text) ||
                            c.getNumero().contains(text)) {

                        modello.addElement(c);
                    }
                }
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                aggiornaFiltro();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                aggiornaFiltro();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                aggiornaFiltro();
            }
        });

        setVisible(true);
    }

    public void aggiungi() {
        Contatto nuovo = contattoConUtente(null);
        if (nuovo != null) {
            contacts.add(nuovo);
            contacts.sort((o1, o2) -> o1.getNome().compareTo(o2.getNome()));
            modello.clear();
            modello.addAll(contacts);

            // Messaggio di conferma
            JOptionPane.showMessageDialog(null,
                    "Contatto aggiunto correttamente!",
                    "Conferma",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean numeroValido(String numero) {
        if (numero == null || numero.isEmpty()) {
            return false;
        }

        for (int i = 0; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public void modifica() {
        int indexContact = this.rubrica.getSelectedIndex();
        if (indexContact != -1) {
            Contatto contact = this.contacts.get(indexContact);
            contact = this.contattoConUtente(contact);
            contacts.set(indexContact, contact);
            contacts.sort((o1, o2) -> o1.getNome().compareToIgnoreCase(o2.getNome()));

            modello.clear();
            modello.addAll(contacts);

            JOptionPane.showMessageDialog(null,
                    "Contatto modificato correttamente!",
                    "Conferma",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void elimina() {
        int indexContact = this.rubrica.getSelectedIndex();
        if (indexContact != -1) {
            this.contacts.remove(indexContact);
            modello.clear();
            modello.addAll(contacts);

        }
    }

    public Contatto contattoConUtente(Contatto contact) {
        // per creare un nuovo contatto se necessario
        // usiamo un array per permettere la modifica del valore all'interno del
        // listener
        final Contatto[] nuovoContatto = new Contatto[1];

        JTextField nomeField = new JTextField();
        JTextField cognomeField = new JTextField();
        JTextField numeroField = new JTextField();

        JLabel nomeLabel = new JLabel("Nome:");
        JLabel cognomeLabel = new JLabel("Cognome:");
        JLabel numeroLabel = new JLabel("Numero:");

        nomeLabel.setBounds(20, 60, 80, 25);
        cognomeLabel.setBounds(20, 100, 80, 25);
        numeroLabel.setBounds(20, 140, 80, 25);

        nomeField.setBounds(120, 60, 250, 25);
        cognomeField.setBounds(120, 100, 250, 25);
        numeroField.setBounds(120, 140, 250, 25);

        // se stiamo modificando un contatto esistente i campi saranno già compilati
        if (contact != null) {
            nomeField.setText(contact.getNome());
            cognomeField.setText(contact.getCognome());
            numeroField.setText(contact.getNumero());
        }

        JDialog dialog = new JDialog(this, true);
        dialog.setTitle(contact == null ? "Nuovo Contatto" : "Modifica Contatto");
        dialog.setBounds(100, 100, 420, 260);

        JPanel pannelloInserimento = new JPanel();
        pannelloInserimento.setLayout(null);
        dialog.add(pannelloInserimento);

        JLabel titolo = new JLabel("Inserisci dati contatto");
        titolo.setBounds(120, 10, 200, 30);
        pannelloInserimento.add(titolo);

        JButton okBtn = new JButton("OK");
        JButton annullaBtn = new JButton("Annulla");

        okBtn.setBounds(120, 180, 100, 30);
        annullaBtn.setBounds(230, 180, 100, 30);

        pannelloInserimento.add(okBtn);
        pannelloInserimento.add(annullaBtn);

        pannelloInserimento.add(nomeLabel);
        pannelloInserimento.add(nomeField);
        pannelloInserimento.add(cognomeLabel);
        pannelloInserimento.add(cognomeField);
        pannelloInserimento.add(numeroLabel);
        pannelloInserimento.add(numeroField);

        okBtn.addActionListener(e -> {
            String nome = nomeField.getText().trim();
            String cognome = cognomeField.getText().trim();
            String numero = numeroField.getText().trim();

            // Controllo numero
            if (!numeroValido(numero)) {
                JOptionPane.showMessageDialog(
                        dialog,
                        "Inserisci un numero di telefono valido (solo cifre).",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
                return; // blocca la chiusura del dialog
            }

            if (contact == null) {
                // Creo un nuovo contatto
                nuovoContatto[0] = new Contatto(
                        nomeField.getText(),
                        cognomeField.getText(),
                        numeroField.getText());
            } else {
                // Modifico il contatto esistente
                contact.setNome(nomeField.getText());
                contact.setCognome(cognomeField.getText());
                contact.setNumero(numeroField.getText());
            }
            dialog.dispose();
        });

        // Pulsante Annulla
        annullaBtn.addActionListener(e -> {
            nuovoContatto[0] = null; // in caso di nuovo contatto
            dialog.dispose();
        });

        dialog.setVisible(true);

        return contact == null ? nuovoContatto[0] : contact;
        // se stiamo modificando, ritorniamo comunque il contatto esistente già
        // aggiornato

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Aggiungi":
                aggiungi();
                fileManager.salvaContatti(contacts);
                break;
            case "Modifica":
                modifica();
                fileManager.salvaContatti(contacts);
                break;
            case "Elimina":
                int result = JOptionPane.showConfirmDialog(
                        this,
                        "Sei sicuro di voler eliminare il contatto?",
                        "Conferma",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    elimina();
                    fileManager.salvaContatti(contacts);
                    JOptionPane.showMessageDialog(null,
                            "Contatto eliminato correttamente!",
                            "Conferma",
                            JOptionPane.INFORMATION_MESSAGE);
                }

                break;
        }
    }

}
