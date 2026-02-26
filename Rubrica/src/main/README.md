## Rubrica Telefonica in Java
# Camilla Andreatta

Il progetto Rubrica Telefonica consente di gestire una rubrica di contatti permettendo l’inserimento, la modifica, l’eliminazione e la ricerca dei contatti.
I dati vengono salvati in modo persistente su file CSV e ricaricati automaticamente all’avvio del programma.

# Funzionalità Principali

- **Gestione Contatti**: Inserimento, modifica ed eliminazione di contatti

- Persistenza su File **CSV**: Salvataggio automatico dei contatti dopo ogni modifica

- **Caricamento** Automatico: I contatti vengono caricati all’avvio del programma

- **Barra di Ricerca**: Filtro dinamico per nome, cognome o numero

- **Validazione Dati**: Controllo che il numero di telefono sia valido (solo cifre)

- **Ordinamento Automatico**: I contatti vengono ordinati alfabeticamente per nome

# Scelte di Sviluppo
1. Struttura dell’Interfaccia Grafica

- **Layout a Due Pannelli**:
  - Pannello superiore con pulsanti e barra di ricerca
  - Pannello inferiore con la lista dei contatti scrollabile

- **Renderer Personalizzato**: uso di DefaultListCellRenderer per migliorare la leggibilità dei contatti nella lista.

2. Gestione dei Contatti
- **Classe Contatto**: 
Ogni contatto è rappresentato da un oggetto Contatto contenente: nome, cognome, numero di telefono. Questo approccio permette una gestione ordinata e orientata agli oggetti dei dati.

- **Collezione dei Contatti**
    
    I contatti sono memorizzati in una List<Contatto>, che consente: inserimento dinamico, ordinamento e una facile integrazione con la JList grafica.

3. Persistenza su File CSV
- Classe **FileManager**

    La classe FileManager si occupa esclusivamente della gestione dei file.
    - Salvataggio dei contatti in formato CSV (nome;cognome;numero)
    - Caricamento dei contatti all’avvio del programma
    - Creazione automatica del file e delle cartelle se non esistono

    **Percorso del File**

    Il file CSV è salvato in: src/main/resources/rubrica.csv

4. Validazione dei Dati
- **Controllo del Numero di Telefono**

    Prima di salvare un contatto viene verificato che:
    - il campo numero non sia vuoto
    - il numero contenga solo cifre

    La validazione viene effettuata con un metodo dedicato che scorre carattere per carattere la stringa, migliorando la leggibilità e la comprensione del codice.

5. Barra di Ricerca
- **Filtro Dinamico**

    La barra di ricerca utilizza un DocumentListener per aggiornare in tempo reale la lista dei contatti. 
    
    Il filtro agisce su nome, cognome e numero, modificando solo la visualizzazione.