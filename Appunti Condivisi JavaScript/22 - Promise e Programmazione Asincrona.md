Partiamo dall’idea base: **JavaScript è single-threaded**.  
Questo significa che può fare **una cosa alla volta**. Se un’operazione bloccasse il thread (tipo una richiesta di rete sincrona), il browser si congelerebbe.  
Le Promise servono proprio a questo: **gestire operazioni lente senza bloccare il programma**.

---

### Stati di una Promise

Una Promise è come una “promessa” appunto: _succederà qualcosa, ma non so ancora quando_.

- `pending`: la promessa è in corso
- `fulfilled`: è andata a buon fine
- `rejected`: è fallita

Cose importantissime da ricordare:
- una Promise **può cambiare stato una sola volta** (può transitare da `pending` a `fulfilled` O `rejected` una sola volta)
- dopo `fulfilled` o `rejected` è **immutabile**
- `resolve` e `reject` **non fermano il codice**, ma notificano lo stato

```
┌─────────┐
│ pending │ (iniziale)
└────┬────┘
     │
     ├──── resolve() ───► fulfilled (successo)
     │
     └──── reject() ────► rejected (errore)
```

---

### Creazione di una Promise

```js
const promise = new Promise((resolve, reject) => {
    // Operazione asincrona
    setTimeout(() => {
        const success = Math.random() > 0.5;
        
        if (success) {
            resolve('Operazione riuscita!'); // → fulfilled
        } else {
            reject(new Error('Operazione fallita!')); // → rejected
        }
    }, 1000);
});
```

```js
new Promise((resolve, reject) => { ... })
```

Qui stai dicendo:
> “Ehi JS, io avvio un’operazione asincrona. Quando finisce, ti chiamo.”

- `resolve(value)` → successo    
- `reject(error)` → errore    

Nota importante: **non è obbligatorio che l’operazione sia asincrona**, ma _di solito lo è_ (fetch, timeout, file, ecc).

---

### then / catch: la chain

```js
promise
    .then((result) => {
        console.log('Success:', result);
        return result.toUpperCase(); // può ritornare valore...
    })
    .then((upperResult) => {
        console.log('Uppercase:', upperResult);
        return fetch('/api/data'); // ...o altra promise
    })
    .then((response) => response.json())
    .then((data) => {
        console.log('Data:', data);
    })
    .catch((error) => {
        // Cattura TUTTI gli errori nella chain
        console.error('Error:', error);
    })
    .finally(() => {
        // Eseguito sempre, sia in caso di successo che errore
        console.log('Cleanup');
    });
```

Ogni `.then()`:
- riceve il risultato della Promise precedente
- **ritorna sempre una Promise**

Questo è il motivo per cui puoi fare:

```js
.then(...)
.then(...)
.then(...)
```

Regola d’oro:
- se ritorni un valore → viene “wrappato” in `Promise.resolve(valore)`
- se ritorni una Promise → JS aspetta che si risolva
- se lanci un errore → vai dritta nel `.catch()`

Il `.catch()`:
- intercetta **qualsiasi errore precedente**
- è come un `try/catch`, ma asincrono

Il `.finally()`:
- viene eseguito **sempre**
- non riceve valori
- perfetto per cleanup (spinner, lock, reset UI)

---

### async / await

```js
// Funzione asincrona ritorna sempre una Promise
async function fetchUserData(userId) {
    try {
        // await pausa l'esecuzione fino a risoluzione promise
        const response = await fetch(`/api/users/${userId}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const user = await response.json();
        return user; // automaticamente wrapped in Promise.resolve()
        
    } catch (error) {
        console.error('Failed to fetch user:', error);
        throw error; // automaticamente wrapped in Promise.reject()
    }
}

// Uso
const user = await fetchUserData(123); // top-level await (solo in modules)

// O con then
fetchUserData(123)
    .then(user => console.log(user))
    .catch(err => console.error(err));
```

Questa è la parte che spesso confonde:  
**async/await NON sostituisce le Promise**  
→ è solo un modo più leggibile per usarle

```js
async function f() {
  return 5;
}
```

Questo è IDENTICO a:

```js
function f() {
  return Promise.resolve(5);
}
```

`await`:
- può essere usato **solo dentro funzioni async*
- “sospende” la funzione finché la Promise non si risolve
- **non blocca il thread**, solo quella funzione

Mental model giusto:
> `await` = “aspetta qui, poi riprendi”

---

### try / catch con async

```js
try {
  await qualcosa();
} catch (e) {
  ...
}
```

È la versione asincrona del try/catch classico.  
Qualsiasi Promise `rejected` → entra nel `catch`.

Attenzione:
- se **non rilanci l’errore**, la Promise risulta fulfilled
- `throw error` serve per propagare il problema

---

### `Promise.all`

```js
// Esegue tutte le promise in parallelo
// Risolve quando TUTTE sono completate
// Rigetta se ANCHE UNA SOLA fallisce

const promises = [
    fetch('/api/users'),
    fetch('/api/posts'),
    fetch('/api/comments')
];

try {
    const [users, posts, comments] = await Promise.all(promises);
    // Tutte completate con successo
} catch (error) {
    // Almeno una è fallita
    console.error('One request failed:', error);
}
```

Usala quando:
- ti servono **tutti i risultati**
- le operazioni sono **indipendenti**
- vuoi eseguirle **in parallelo**

⚠️ Se UNA fallisce → tutto fallisce  
Questo è voluto: è una garanzia di consistenza.

---

### `Promise.allSettled`

```js
// Come Promise.all ma NON rigetta mai
// Ritorna risultato di tutte le promise (successo o fallimento)

const results = await Promise.allSettled([
    fetch('/api/users'),
    fetch('/api/posts'),
    Promise.reject('error')
]);

results.forEach((result, index) => {
    if (result.status === 'fulfilled') {
        console.log(`Promise ${index} succeeded:`, result.value);
    } else {
        console.log(`Promise ${index} failed:`, result.reason);
    }
});

// Output:
// Promise 0 succeeded: Response {...}
// Promise 1 succeeded: Response {...}
// Promise 2 failed: error
```

Questa è molto “da mondo reale”.
Usala quando:
- vuoi sapere **come è andata ogni operazione**    
- non vuoi che un errore blocchi tutto    

Ottima per:
- dashboard    
- batch di richieste
- logging    

---

### `Promise.race`

```js
// Risolve/rigetta appena la PRIMA promise completa

const timeout = new Promise((_, reject) => {
    setTimeout(() => reject(new Error('Timeout!')), 5000);
});

const dataFetch = fetch('/api/slow-endpoint');

try {
    const result = await Promise.race([dataFetch, timeout]);
    console.log('Data received:', result);
} catch (error) {
    console.error('Timeout or fetch failed:', error);
}
```

Qui vince **chi arriva prima**, nel bene o nel male.
Tipico uso:
- timeout
- fallback
- competizione tra risorse

Nota: se la prima è un errore → perdi subito.

---

### `Promise.any`

```js
// Risolve appena la PRIMA promise ha SUCCESSO
// Rigetta solo se TUTTE falliscono

const mirrors = [
    fetch('https://mirror1.com/data'),
    fetch('https://mirror2.com/data'),
    fetch('https://mirror3.com/data')
];

try {
    const fastestResponse = await Promise.any(mirrors);
    const data = await fastestResponse.json();
    console.log('Data from fastest mirror:', data);
} catch (error) {
    // AggregateError - contiene tutti gli errori
    console.error('All mirrors failed:', error.errors);
}
```

Simile a race, ma:
- ignora i fallimenti
- vuole **almeno un successo**

Perfetta per:
- mirror
- CDN
- servizi ridondanti

Se tutte falliscono → `AggregateError`  

---

## Pattern pratici

### Retry + backoff

```js
async function fetchWithRetry(url, maxRetries = 3) {
    for (let i = 0; i < maxRetries; i++) {
        try {
            const response = await fetch(url);
            if (response.ok) return response;
            
            throw new Error(`HTTP ${response.status}`);
        } catch (error) {
            if (i === maxRetries - 1) throw error;
            
            // Exponential backoff
            const delay = Math.pow(2, i) * 1000;
            console.log(`Retry ${i + 1}/${maxRetries} after ${delay}ms`);
            await new Promise(resolve => setTimeout(resolve, delay));
        }
    }
}

// Uso
try {
    const response = await fetchWithRetry('/api/unreliable');
    const data = await response.json();
} catch (error) {
    console.error('Failed after retries:', error);
}
```

Questo è codice **da produzione vera**.  
Spiega benissimo:
- resilienza
- controllo del flusso
- uso intelligente di async/await

Concetto chiave:
> se qualcosa può fallire, ritenta… ma con calma

---

### Timeout wrapper

```js
function withTimeout(promise, ms) {
    const timeout = new Promise((_, reject) => {
        setTimeout(() => reject(new Error('Timeout')), ms);
    });
    
    return Promise.race([promise, timeout]);
}

// Uso
try {
    const data = await withTimeout(
        fetch('/api/slow'),
        3000 // timeout dopo 3 secondi
    );
} catch (error) {
    console.error('Request timeout:', error);
}
```

Pattern elegantissimo:
- non modifichi la Promise originale
- aggiungi comportamento esterno

È una dimostrazione perfetta che:
> le Promise sono composabili

---

## Loading state manager

```js
class AsyncTaskManager {
    constructor() {
        this.loading = false;
        this.error = null;
        this.data = null;
    }
    
    async execute(asyncFn) {
        this.loading = true;
        this.error = null;
        
        try {
            this.data = await asyncFn();
        } catch (error) {
            this.error = error;
            console.error('Task failed:', error);
        } finally {
            this.loading = false;
        }
        
        return this.data;
    }
}

// Uso con UI
const manager = new AsyncTaskManager();

async function loadUsers() {
    await manager.execute(async () => {
        const response = await fetch('/api/users');
        return response.json();
    });
    
    // Update UI
    if (manager.loading) {
        showSpinner();
    } else if (manager.error) {
        showError(manager.error);
    } else {
        displayUsers(manager.data);
    }
}
```
---

### Sequential vs Parallel

```js
// Esegui promise una dopo l'altra (NON in parallelo)
async function sequential(asyncFunctions) {
    const results = [];
    
    for (const fn of asyncFunctions) {
        const result = await fn();
        results.push(result);
    }
    
    return results;
}

// Uso
const tasks = [
    () => fetch('/api/step1').then(r => r.json()),
    () => fetch('/api/step2').then(r => r.json()),
    () => fetch('/api/step3').then(r => r.json())
];

const results = await sequential(tasks);
// step1 completa → poi step2 → poi step3
```

Questa distinzione è super importante:
- `Promise.all` → parallelo
- `for + await` → sequenziale

Errore comune degli studenti:

```js
array.forEach(async () => { await ... })
```

❌ **NON funziona come pensano**  
(perché forEach non aspetta)

Il tuo esempio `sequential()` è quello corretto.

---

### Promise Pool (livello avanzato)

```js
async function promisePool(asyncFunctions, poolSize = 3) {
    const results = [];
    const executing = [];
    
    for (const fn of asyncFunctions) {
        const promise = fn().then(result => {
            results.push(result);
            executing.splice(executing.indexOf(promise), 1);
        });
        
        executing.push(promise);
        
        if (executing.length >= poolSize) {
            await Promise.race(executing);
        }
    }
    
    await Promise.all(executing);
    return results;
}

// Uso: esegui 100 richieste ma massimo 5 alla volta
const tasks = Array.from({ length: 100 }, (_, i) => 
    () => fetch(`/api/item/${i}`).then(r => r.json())
);

const results = await promisePool(tasks, 5);
```

Qui siamo già a:
- performance
- controllo risorse
- architettura

Questo pattern dimostra che:
- sai cosa significa “concorrenza”
- sai evitare overload di server/browser

---

## Riassunto dei concetti chiave

- Promise = stato futuro
- async/await = sintassi più leggibile
- then/catch = chaining
- all / race / any = strategie diverse
- i pattern servono per **controllare il caos asincrono**

---

## Appunti ripasso

link : [MDN Promise](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise)

``` js
fetch('https://api.example.com/data')
  .then(response => {
      if (response.ok) {
          return response.json();
      } else {
          throw new Error('Network response was not ok.');
      }
  })
  .then(data => {
    // do something with the data
  })
  .catch(error => {
    // handle error
});
```