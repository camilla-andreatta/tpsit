## Cos’è lo scope

Lo **scope** è semplicemente:

> 👉 **dove una variabile è visibile e utilizzabile**

In altre parole:
- **dove nasce** una variabile
- **dove può essere usata**
- dove NON esiste più

---

## Variabili GLOBALI

Una variabile è **globale** se è dichiarata **fuori da tutte le funzioni**.

```js
let x = 10;

function test() {
  console.log(x);
}

test(); // 10
```

📌 Qui `x` è globale, quindi esiste ovunque (anche dentro la funzione).

---

## Variabili LOCALI (di funzione)

Una variabile è **locale** se è dichiarata **dentro una funzione**.

```js
function test() {
  let y = 5;
  console.log(y);
}

test();      // 5
console.log(y); // ❌ errore
```

📌 Qui `y` nasce **dentro** `test`, vive solo lì e fuori da lì **non esiste**.

---

## Scope a blocchi (`let` e `const`)

Con `let` e `const`, anche `{ }` crea uno scope.

```js
if (true) {
  let z = 3;
}

console.log(z); // ❌ errore
```

⚠️ Con `var` questo NON succede (ma per ora ignoriamolo).

---

## Come JS cerca le variabili

Quando JS incontra un nome:

```js
console.log(a);
```

Cerca così:
1. nello scope **corrente**    
2. nello scope **esterno**    
3. ancora più fuori    
4. fino allo scope globale    
5. se non trova → errore    
E **NON scende mai** dentro le funzioni figlie.

---

### Esempio

```js
let a = 1;

function outer() {
  let b = 2;

  function inner() {
    let c = 3;
    console.log(a, b, c);
  }

  inner();
}

outer();
```

Output: `1 2 3`

Perché `inner` vede:
- `c` (suo)    
- `b` (esterno)    
- `a` (globale)    

Ma:
```js
console.log(c); // ❌
```

---

## Riassunto dei concetti chiave

|Variabile|Dove è visibile|
|---|---|
|globale|ovunque|
|locale funzione|solo nella funzione|
|locale blocco|solo nel blocco `{}`|
