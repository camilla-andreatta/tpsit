## Introduzione agli Oggetti

### Cos'è un Oggetto?
Un oggetto in JavaScript è una **struttura dati** che permette di raggruppare valori correlati sotto un unico nome. Pensa a un oggetto come a una "scatola" che contiene informazioni organizzate.

un oggetto è un dizionario, cioè una mappa in cui le chiavi sono stringhe con un valore assegnato; un oggetto può essere modificato perchè posso arricchirlo con ulteriori proprietà in runtime (questo è un altro motivo per cui non possono esistere le classi, le quali definiscono solo un modello per creare oggetti)

### Perché Usare gli Oggetti?
```javascript
// Senza oggetto (variabili separate)
let nome = "Anna";
let eta = 17;
let altezza = 165;
let citta = "Roma";
let iscritta = true;

// Con oggetto (dati organizzati)
let persona = {
  nome: "Anna",
  eta: 17,
  altezza: 165,
  citta: "Roma",
  iscritta: true
};
```

## Anatomia di un Oggetto

### Struttura Base
```javascript
let nomeOggetto = {
  chiave1: valore1,
  chiave2: valore2,
  chiave3: valore3
  // ... altre proprietà
};
```

### Terminologia Fondamentale

| Termine | Definizione | Esempio |
|---------|------------|---------|
| **Oggetto** | Contenitore di dati correlati | `let studente = {...}` |
| **Proprietà (Property)** | Coppia chiave-valore dentro l'oggetto | `nome: "Anna"` |
| **Chiave (Key)** | Nome della proprietà (sempre stringa) | `"nome"`, `"eta"` |
| **Valore (Value)** | Dato associato alla chiave | `"Anna"`, `17` |

## 📖 Accesso alle Proprietà

### Metodo 1: Notazione a Punto (Dot Notation)
```javascript
let persona = {
  nome: "Mario",
  eta: 25,
  professione: "Sviluppatore"
};

console.log(persona.nome);        // "Mario"
console.log(persona.eta);         // 25
console.log(persona.professione); // "Sviluppatore"
```
NB: non funziona con chiavi che contengono trattini o sono costituite da numeri
### Metodo 2: Notazione a Parentesi Quadre (Bracket Notation)
```javascript
console.log(persona["nome"]);        // "Mario"
console.log(persona["eta"]);         // 25

// Utile quando la chiave è in una variabile
let chiave = "professione";
console.log(persona[chiave]);        // "Sviluppatore"
```

### Differenze Chiave tra i Due Metodi
```javascript
let oggetto = {
  "nome-completo": "Anna Rossi",  // Chiave con trattino
  "123": "valore speciale"        // Chiave che inizia con numero
};

// NOTAZIONE A PUNTO NON FUNZIONA
// oggetto.nome-completo  // Errore!
// oggetto.123            // Errore!

// ✅ NOTAZIONE A PARENTESI FUNZIONA
console.log(oggetto["nome-completo"]);  // "Anna Rossi"
console.log(oggetto["123"]);            // "valore speciale"
```

## Tipi di Valori nelle Proprietà

### Oggetti Possono Contenere Qualsiasi Tipo di Dato
```javascript
let studenteComplesso = {
  // Tipi primitivi
  nome: "Luca",              // Stringa
  eta: 18,                   // Numero
  iscritto: true,            // Booleano
  
  // Tipi complessi
  voti: [8, 7, 9, 10],       // Array
  indirizzo: {               // Oggetto annidato
    via: "Roma 123",
    citta: "Milano"
  },
  
  // Funzioni (metodi)
  saluta: function() {
    return "Ciao, mi chiamo " + this.nome;
  },
  
  // Valori null/undefined
  telefono: null,
  hobby: undefined
};
```

## Modifica degli Oggetti

### 1. Modificare Valori Esistenti
```javascript
let auto = {
  marca: "Fiat",
  modello: "500",
  anno: 2020
};

auto.anno = 2023;           // Aggiorna il valore
auto["modello"] = "500X";   // Alternativa con parentesi
```

### 2. Aggiungere Nuove Proprietà
```javascript
auto.colore = "rosso";                 // Aggiunge 'colore'
auto["prezzo"] = 15000;               // Alternativa
auto.motore = {                        // Aggiunge oggetto annidato
  tipo: "benzina",
  cilindrata: 1200
};
```

### 3. Rimuovere Proprietà
```javascript
delete auto.colore;        // Rimuove la proprietà 'colore'
delete auto["prezzo"];     // Alternativa con parentesi

// Dopo la rimozione
console.log(auto.colore);  // undefined
```

## Verifica Proprietà

### Controllare se una Proprietà Esiste
```javascript
let libro = {
  titolo: "JavaScript Guide",
  pagine: 300
};

// Metodo 1: Operatore 'in'
console.log("titolo" in libro);      // true
console.log("autore" in libro);      // false

// Metodo 2: hasOwnProperty()
console.log(libro.hasOwnProperty("pagine"));  // true

// Metodo 3: Controllo con undefined
console.log(libro.editore !== undefined);     // false
```

## Confronto: Array vs Oggetti

| Caratteristica | Array | Oggetti |
|----------------|-------|---------|
| **Struttura** | Lista ordinata | Raccolta non ordinata |
| **Accesso** | Tramite indice numerico | Tramite chiavi (stringhe) |
| **Ordine** | Garantito | Non garantito (fino a ES6) |
| **Casi d'uso** | Dati omogenei, liste | Dati eterogenei, entità complesse |
| **Esempio** | `[10, 20, 30]` | `{nome: "Anna", eta: 17}` |
| **Metodi** | `push()`, `pop()`, `map()` | - (ma hanno metodi propri) |

## Casi d'Uso Reali

### 1. Rappresentazione di Entità del Mondo Reale
```javascript
// Utente di un sistema
let utente = {
  id: 12345,
  username: "annarossi",
  email: "anna@email.com",
  profilo: {
    nome: "Anna",
    cognome: "Rossi",
    dataNascita: "2005-05-15"
  },
  preferenze: {
    lingua: "it",
    notifiche: true,
    tema: "dark"
  }
};

// Prodotto in un e-commerce
let prodotto = {
  sku: "PROD-001",
  nome: "Smartphone XYZ",
  prezzo: 599.99,
  sconto: 15,
  disponibile: true,
  categorie: ["elettronica", "telefonia"],
  recensioni: [
    {utente: "Mario", voto: 5, commento: "Ottimo!"},
    {utente: "Laura", voto: 4, commento: "Buono"}
  ]
};
```

### 2. Configurazioni e Impostazioni
```javascript
let config = {
  apiUrl: "https://api.example.com",
  timeout: 5000,
  retryAttempts: 3,
  headers: {
    "Content-Type": "application/json",
    "Authorization": "Bearer token123"
  }
};
```

## Iterazione sugli Oggetti

### Metodi per Scorrere le Proprietà
```javascript
let persona = {
  nome: "Anna",
  eta: 17,
  citta: "Roma"
};

// 1. for...in loop
for (let chiave in persona) {
  console.log(chiave + ": " + persona[chiave]);
}

// 2. Object.keys() - restituisce array di chiavi
let chiavi = Object.keys(persona);
console.log(chiavi); // ["nome", "eta", "citta"]

// 3. Object.values() - restituisce array di valori
let valori = Object.values(persona);
console.log(valori); // ["Anna", 17, "Roma"]

// 4. Object.entries() - restituisce array di coppie [chiave, valore]
let entries = Object.entries(persona);
console.log(entries); // [["nome", "Anna"], ["eta", 17], ["citta", "Roma"]]
```

## Errori Comuni e Best Practices

### 1. Accesso a Proprietà Inesistenti
```javascript
let obj = {a: 1};
console.log(obj.b); // undefined (NON errore)

// Soluzione: controllare prima
if (obj.b !== undefined) {
  // Fai qualcosa con obj.b
}
```

### 2. Confusione tra . e []
```javascript
let key = "nome";
let persona = {nome: "Anna"};

console.log(persona.key);   // undefined (cerca proprietà "key")
console.log(persona[key]);  // "Anna" (usa valore della variabile)
```

### 3. Mutabilità degli Oggetti
```javascript
let obj1 = {x: 1};
let obj2 = obj1;  // Copia il RIFERIMENTO, non l'oggetto!

obj2.x = 2;
console.log(obj1.x); // 2 (anche obj1 è cambiato!)

// Per copiare davvero:
let obj3 = {...obj1}; // Spread operator (ES6+)
let obj4 = Object.assign({}, obj1); // Metodo classico
```

## Concetti Avanzati (Anticipazione)

### 1. Metodi negli Oggetti
```javascript
let calcolatrice = {
  valore: 0,
  
  somma: function(num) {
    this.valore += num;
    return this;
  },
  
  moltiplica: function(num) {
    this.valore *= num;
    return this;
  },
  
  reset: function() {
    this.valore = 0;
    return this;
  }
};

// Uso con chaining
calcolatrice.reset().somma(5).moltiplica(2);
console.log(calcolatrice.valore); // 10
```

### 2. Costruttori (Introduzione)
```javascript
// Funzione costruttore
function Persona(nome, eta) {
  this.nome = nome;
  this.eta = eta;
  this.saluta = function() {
    return "Ciao da " + this.nome;
  };
}

// Creazione di istanze
let persona1 = new Persona("Mario", 30);
let persona2 = new Persona("Laura", 25);
```

## Riassunto dei concetti chiave

1. **Sintassi**: `{chiave: valore, chiave2: valore2}`
2. **Chiavi**: Sono sempre stringhe (anche se non si vedono le virgolette)
3. **Accesso**: Usa `.` per chiavi semplici, `[]` per chiavi complesse o variabili
4. **Aggiunta**: `obj.nuovaChiave = valore`
5. **Modifica**: `obj.chiaveEsistente = nuovoValore`
6. **Rimozione**: `delete obj.chiave`
7. **Verifica**: `"chiave" in obj` o `obj.hasOwnProperty("chiave")`