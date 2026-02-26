```js
let val = 7 

function createAdder() {
  addNumbers = (a, b) => a + b;
  return addNumbers
}

let adder = createAdder();
let sum = adder(val, 8);

console.log(`fynction returning function: ${sum}`);
```

- `val` è una **variabile globale**, visibile ovunque. 
- `createAdder` è una funzione che **ritorna un’altra funzione** (`addNumbers`).
- `addNumbers` prende due numeri e li somma.    
- Attenzione: senza `let/const`, `addNumbers` diventa globale (non ottimale, ma funziona).
- `adder` poi diventa la funzione `addNumbers`.
- Chiamandola con `(val, 8)` calcola `7 + 8 = 15`.

---

### Riassunto dei concetti chiave

1. Le funzioni possono **ritornare altre funzioni**.
2. Una variabile globale è visibile ovunque, una locale solo dentro la funzione.
3. Puoi usare la funzione ritornata come una normale funzione, assegnandola a una variabile.
4. Questo è il concetto base che porta alle **closure**, dove la funzione “ricorda” lo scope in cui è stata creata.