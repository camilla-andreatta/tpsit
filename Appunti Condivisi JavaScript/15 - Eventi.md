## Cos’è un evento

Un **evento** è:

> 👉 **un’azione o un cambiamento che avviene in un elemento della pagina** e a cui possiamo “reagire” con del codice.

Esempi comuni di eventi:

| Evento      | Quando succede                          |
| ----------- | --------------------------------------- |
| `click`     | Quando l’utente clicca un elemento      |
| `focus`     | Quando un input riceve il cursore       |
| `blur`      | Quando un input perde il cursore        |
| `mouseover` | Quando il mouse passa sopra un elemento |
| `keydown`   | Quando l’utente preme un tasto          |
| `submit`    | Quando un form viene inviato            |

---

## Come associare un evento a un elemento

Ci sono diversi modi, ma i principali sono:

### Proprietà dell’elemento (`element.onclick`)

```js
let button = document.getElementById('myButton');
button.onclick = function() {
  console.log("Hai cliccato il bottone!");
};
```

- Qui diciamo: **quando clicchi `button`, esegui questa funzione**.
- Si può fare anche con `onfocus`, `onblur`, ecc, al posto di `onclick`.
- Limite: puoi assegnare **una sola funzione per tipo di evento** usando questo metodo.

### Event Listener (`addEventListener`)

```js
let button = document.getElementById('myButton');
button.addEventListener('click', function() {
  console.log("Hai cliccato il bottone!");
});
```

- Più flessibile del metodo precedente.
- Puoi **aggiungere più funzioni allo stesso evento** sullo stesso elemento.
- Sintassi:
    - Primo argomento: il tipo di evento (`'click'`, `'focus'`, ecc.)
    - Secondo argomento: la funzione da eseguire
    - Terzo argomento (opzionale): configurazioni aggiuntive (`true/false` per capture, ecc.)

---

## L’oggetto Event

Quando un evento viene attivato, la funzione riceve **un oggetto evento** (`event`):

```js
element.addEventListener('click', (event) => {
    // Proprietà comuni
    event.type;              // 'click'
    event.target;            // elemento che ha scatenato l'evento
    event.currentTarget;     // elemento su cui è registrato il listener
    event.timeStamp;         // timestamp evento
    
    // Coordinate (per eventi mouse)
    event.clientX;           // X relativo a viewport
    event.clientY;           // Y relativo a viewport
    event.pageX;             // X relativo a documento
    event.pageY;             // Y relativo a documento
    event.offsetX;           // X relativo all'elemento
    event.offsetY;           // Y relativo all'elemento
    
    // Modificatori
    event.ctrlKey;           // Ctrl premuto?
    event.shiftKey;          // Shift premuto?
    event.altKey;            // Alt premuto?
    event.metaKey;           // Cmd (Mac) o Win premuto?
    
    // Controllo propagazione
    event.preventDefault();  // blocca comportamento default
    event.stopPropagation(); // ferma bubbling/capturing
    event.stopImmediatePropagation(); // ferma anche altri listener su stesso elemento
});
```

- `event.target` → l’elemento che ha generato l’evento
- `event.type` → il tipo di evento (`click`, `focus`, ecc.)
- `event.key` → quale tasto è stato premuto
- `event.clientX/Y` → posizione del mouse
- `event.stopPropagation` → ferma il bubbling

---

## Eventi e closure

Spesso gli eventi vengono creati dentro cicli o funzioni (come `setupHelp` nella scheda 14).
Le **funzioni evento ricordano le variabili dello scope** in cui sono state create → **closure**.
Questo permette di associare **ogni input al suo suggerimento specifico**, anche se il ciclo è finito.

---

## Rimozione Event Listener

```js
// ❌ NON funziona - funzioni anonime diverse
button.addEventListener('click', () => console.log('click'));
button.removeEventListener('click', () => console.log('click'));


// ✅ Funziona - stessa reference
function handleClick() {
    console.log('click');
}

button.addEventListener('click', handleClick);
button.removeEventListener('click', handleClick);

// Pattern: cleanup
class Component {
    constructor(element) {
        this.element = element;
        this.handleClick = this.handleClick.bind(this);
        this.element.addEventListener('click', this.handleClick);
    }
    
    handleClick() {
        console.log('clicked');
    }
    
    destroy() {
        this.element.removeEventListener('click', this.handleClick);
    }
}
```
---

## Riassunto dei concetti chiave

| Concetto                   | Spiegazione                                                                       |
| -------------------------- | --------------------------------------------------------------------------------- |
| Evento                     | Azione o cambiamento in un elemento a cui possiamo reagire                        |
| `onclick`, `onfocus`, ecc. | Metodo rapido per associare una funzione a un evento                              |
| `addEventListener`         | Metodo più flessibile, permette più funzioni sullo stesso evento                  |
| Oggetto `event`            | Contiene informazioni sull’evento e sull’elemento che lo ha generato              |
| Closure negli eventi       | Le funzioni evento ricordano lo scope originale, utile per gestire dati specifici |
