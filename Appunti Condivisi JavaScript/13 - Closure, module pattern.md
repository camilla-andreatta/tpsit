```js
function coolModule() {
    let something = "cool";  // variabile locale privata
    let another = [1, 2, 3]; // variabile locale privata

    function doSomething() {      
        // funzione interna che legge una variabile locale
        console.log(something);
    }
    
    function doAnother() {        
        // funzione interna che legge un'altra variabile locale
        console.log(another.join("!"));
    }
    
    return {                      
        // ritorna un oggetto con funzioni pubbliche
        doSomething: doSomething, 
        doAnother: doAnother
    };
}

var module = coolModule();  // crea una nuova closure
module.doSomething();       // accede a `something`
module.doAnother();         // accede a `another`
```

---
## Scope locale e variabili private

```js
let something = "cool"; 
let another = [1, 2, 3];
```

Queste variabili sono **dichiarate dentro la funzione `coolModule`**, quindi **appartengono allo scope locale** della funzione, e **non esistono al di fuori di di esso**, quindi nessun codice esterno può modificarle direttamente.
Questo crea uno **“spazio privato”** per i dati, fondamentale per proteggere lo stato interno e prevenire accessi indesiderati dall’esterno.    

---

## Funzioni interne e closure

```js
function doSomething() { 
    console.log(something);
}

function doAnother() {
    console.log(another.join("!"));
}
```

Entrambe le funzioni sono **dichiarate dentro `coolModule`**, quindi possono accedere a `something` e `another`.
Quando le chiamiamo all’esterno, queste funzioni **continuano a ricordare lo scope in cui sono state create**.
Questo meccanismo si chiama **closure**: permette a una funzione di mantenere un “collegamento” alle variabili dello scope originale anche dopo che la funzione esterna ha terminato l’esecuzione.
Senza le closure, una volta terminata `coolModule`, le variabili `something` e `another` sarebbero sparite.

---

## Restituire un oggetto con metodi pubblici

```js
return {
    doSomething: doSomething, 
    doAnother: doAnother
};
```

Qui la funzione **ritorna un oggetto**.
Le proprietà dell’oggetto (`doSomething` e `doAnother`) sono **riferimenti alle funzioni interne**.
Questo significa che dall’esterno puoi chiamare solo questi metodi, e le funzioni stesse hanno accesso alle variabili private.
Tutte le altre variabili dello scope di `coolModule` restano **inaccessibili dall’esterno**.

---

## Utilizzo del modulo

```js
var module = coolModule();
module.doSomething(); // stampa "cool"
module.doAnother();   // stampa "1!2!3"
```

`module` è un oggetto con due metodi pubblici.
Quando chiami `module.doSomething()`, la funzione accede alla variabile `something` all’interno della closure e stampa “cool”.
Quando chiami `module.doAnother()`, la funzione accede alla variabile `another`, converte l’array in stringa separata da “!” e stampa `1!2!3`.
Notare che **le variabili interne non sono accessibili direttamente**: ad esempio, non puoi fare `module.something = "hot"` perché `something` non è esposto nell’oggetto.
Questo dimostra come il modulo mantenga **stato privato e sicuro**, ma offra **interfacce pubbliche controllate**.

---

### Perché è importante

Le closure permettono di **incapsulare dati**: solo le funzioni che ne hanno bisogno possono accedervi.
Il Module Pattern aiuta a **organizzare il codice**, evitando di mettere tutto nel globale, riducendo conflitti di nomi.

---

## Riassunto dei concetti chiave

| Concetto         | Spiegazione                                                                                                  |
| ---------------- | ------------------------------------------------------------------------------------------------------------ |
| Variabili locali | Visibili solo nello scope della funzione (`something`, `another`)                                            |
| Closure          | Le funzioni ritornate ricordano lo scope in cui sono state create, anche dopo la fine della funzione esterna |
| Module Pattern   | Organizza codice in un modulo con **stato privato** e metodi pubblici                                        |
| Metodi pubblici  | Permettono di leggere o manipolare variabili interne senza esporle globalmente                               |
| Encapsulamento   | Le closure e il Module Pattern sono strumenti per proteggere dati ed evitare conflitti globali               |
