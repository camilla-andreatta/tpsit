// 1. DOM: Seleziona tutti i bottoni.
// Visto che sono più di uno e hanno una classe, usa document.querySelectorAll
const bottoni = document.querySelectorAll(".btn-studente"); 

// 2. DOM: Seleziona il div dove mostreremo il risultato. 
// Ha un ID, quindi usa document.getElementById
const divRisultato = document.getElementById("risultato");

// Facciamo un ciclo per "attaccare" l'evento a ogni singolo bottone
bottoni.forEach(bottone => {

    // 3. EVENTI: Aggiungi in ascolto l'evento del click
    bottone.addEventListener("click", function() {

        // 4. DATA ATTRIBUTE: Andiamo a pescare il nome e il voto dal bottone cliccato
        // Suggerimento: si usa bottone.dataset.NOME_DEL_DATA_ATTRIBUTE
        let nomeStudente = this.dataset.nome;
        
        // Convertiamo il voto in numero (perché dall'HTML arriva come testo)
        let votoStudente = Number(this.dataset.voto); 

        // 5. BASI JS (Logica): Scriviamo la condizione. La sufficienza è 6.
        if (votoStudente >= 6) {
            
            // 6. DOM: Modifica il testo e il colore se promosso
            divRisultato.textContent = nomeStudente + " è promosso/a con " + votoStudente;
            divRisultato.style.color = "green"; // scrivi il colore (es: 'green')
            
        } else {
            
            // 7. DOM: Modifica il testo e il colore se bocciato
            divRisultato.textContent = nomeStudente + " è bocciato/a con " + votoStudente;
            divRisultato.style.color = "red"; // scrivi il colore (es: 'red')
            
        }
    });
});