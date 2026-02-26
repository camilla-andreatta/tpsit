## Il problema che crea le closure

```js
function outer() {
  let x = 10;

  function inner() {
    console.log(x);
  }

  return inner;
}

let f = outer();
f();
```

Cosa dovrebbe succedere “a logica”?
- `outer()` crea `x = 10`
- poi finisce
- **x dovrebbe sparire**, no?

E invece…
Output: `10`

---
## Cos’è una **closure**

👉 **Una closure è una funzione che si porta dietro lo scope in cui è nata.**

In questo caso:
- `inner` nasce dentro `outer`
- quindi **si ricorda `x`**
- anche quando `outer` è finita

📌 Non ricorda il valore  
📌 Ricorda **la variabile**

---
## Disegno mentale

Quando fai:

```js
let f = outer();
```

Non ottieni solo una funzione.

Ottieni:

```
f ──▶ function inner
        └── x = 10
```

Quella “borsa invisibile” con `x` dentro è la **closure**.

---
## `x` è ancora viva perché serve

JS dice:

> “Finché qualcuno usa `x`, io non la distruggo”.

E `inner` la usa.

---
## Altro esempio

```js
function contatore() {
  let count = 0;

  return function() {
    count++;
    return count;
  };
}

let c1 = contatore();
console.log(c1()); // 1
console.log(c1()); // 2
console.log(c1()); // 3
```

Qui:
- `count` è **privata**
- non è globale    
- vive solo nella closure


Quante variabili `count` esistono qui?

```js
let a = contatore();
let b = contatore();
```

Ogni chiamata a `contatore()` crea **una nuova closure con la propria variabile `count`**.
Quindi `a` e `b` **non condividono lo stesso `count`**, sono completamente indipendenti.
Quindi la risposta è: 2, una per ogni variabile.

---
## Definizione di closure

> Una closure è una funzione che mantiene l’accesso alle variabili del proprio scope esterno anche dopo che la funzione esterna ha terminato l’esecuzione.

---
## Appunti ripasso

La closure è un comportamento. 
Cosa serve per una closure:
1. funzione che ritorna una funziona, la funzione che viene ritornata deve essere descritta nella funzione di partenza (scope = contesto in cui tutti i nomi sono associati ad un valore)
quando termino la funzione c'è una zona di memoria che fa riferimento allo scope della funzione, quando la funzione finisce la zona viene rilasciata. SE nello scope della funzione esterna c'è una var che viene citata in quello della funzione esterna, se ritorno la funzione  esterna, viene mantenuto in vita finchè c'è un riferimento allo scope interno, anche lo scope esterno perchè se lo scope esterno che contiene la variabile venisse ucciso anche la funzione interna non funzionerebbe più.
2. accesso ad una variabile dello scope esterno
il return chiama a cascata l'eliminazione degli scope dalla più piccola alla più grande funzione
In java non estono le closure perchè in java le funzioni non sono oggetti