```js
function createCounter() {
  let counter = 0;
  const myFunction = function() {
    counter = counter + 1;
    return counter;
  }
  return myFunction;
}  

let increment = createCounter();
const c1 = increment();
const c2 = increment();
const c3 = increment();

increment = createCounter();
const c4 = increment();  

console.log(`example increment: ${c1}, ${c2}, ${c3}, ${c4}`);
```

---

`createCounter` è una funzione che crea **una variabile locale `counter`** inizializzata a 0.
Dentro c’è `myFunction`, che **aumenta `counter` di 1 e lo ritorna**.
`createCounter` ritorna `myFunction`.

💡 Qui entriamo nel concetto di **closure**:
- La funzione ritornata (`myFunction`) **“ricorda” lo scope** in cui è stata creata, cioè la variabile `counter`.    
- Anche se `createCounter` ha già terminato l’esecuzione, `myFunction` può ancora accedere e modificare `counter`.


`increment` è la funzione `myFunction` creata da `createCounter()`.
Ogni volta che chiami `increment()`, `counter` **aumenta di 1**: `c1 = 1`, `c2 = 2`, `c3 = 3`.


Viene poi creata una **nuova closure** con `counter = 0`.
Ne deriva che la prima closure non viene più usata e che `c4 = 1`, perché il nuovo `counter` parte da zero.


Infine stampa: `example increment: 1, 2, 3, 1`, che mostra chiaramente come **ogni closure mantiene il proprio stato**.

---

## Riassunto dei concetti chiave

1. Una **closure** è una funzione che **ricorda lo scope in cui è stata creata**.
2. Variabili locali dello scope originale restano “vive” finché la funzione ritornata esiste.
3. Puoi avere **più closure indipendenti**, ciascuna con il suo stato interno.
4. Questo meccanismo permette di creare contatori, generatori, o funzioni con memoria privata.