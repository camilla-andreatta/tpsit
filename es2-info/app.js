// Il nostro Array di partenza
let parole = ["Informatica", "Verifica"];

const inputElement = document.getElementById("input-parola");
const btnAggiungi = document.getElementById("btn-aggiungi");
const bottoniAzione = document.querySelectorAll(".btn-azione"); // seleziona la classe btn-azione
const schermo = document.getElementById("schermo");

// 1. METODO ARRAY: Aggiungere un elemento
btnAggiungi.addEventListener("click", function() {
    let nuovaParola = inputElement.value;
    
    // ESERCIZIO: usa il metodo corretto per inserire 'nuovaParola' ALLA FINE dell'array 'parole'
    parole.push(nuovaParola); 
    
    schermo.textContent = "Parola aggiunta! L'array ora contiene: " + parole.join(", ");
    inputElement.value = ""; // Svuota l'input
});

// 2. METODI STRINGHE: Maiuscolo, Minuscolo, Lunghezza
bottoniAzione.forEach(bottone => {
    bottone.addEventListener("click", function() {
        
        // ESERCIZIO: leggi il data-attribute "data-azione" dal bottone
        let azioneScelta = this.dataset.azione; 
        
        // Prendiamo l'ultima parola inserita nell'array per fare i test
        // L'indice dell'ultimo elemento è la lunghezza dell'array meno 1
        let ultimaParola = parole[parole.length - 1];

        if (azioneScelta === "maiuscolo") {
            // ESERCIZIO: usa il metodo delle stringhe per trasformare tutto in maiuscolo
            schermo.innerHTML = ultimaParola.toUpperCase();
            
        } else if (azioneScelta === "minuscolo") {
            // ESERCIZIO: usa il metodo delle stringhe per trasformare tutto in minuscolo
            schermo.innerHTML = ultimaParola.toLowerCase();
            
        } else if (azioneScelta === "lunghezza") {
            // ESERCIZIO: usa la proprietà delle stringhe (e degli array) che conta i caratteri
            // (Attenzione: questo NON ha le parentesi tonde alla fine!)
            let numeroLettere = ultimaParola.length;
            schermo.innerHTML = "La parola " + ultimaParola + " ha " + numeroLettere + " lettere.";
        }
    });
});