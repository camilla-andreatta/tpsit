Esercizio 4:
```javascript
// class syntax
class MyClass1 {
    constructor(prop1, prop2) {
        this.prop1 = prop1;
        this.prop2 = prop2;
    }
}
let obj1 = new MyClass1(2, 2);
console.log(`obj1 name properties: ${Object.getOwnPropertyNames(obj1)}`)  


// function syntax
function MyClass2(prop1, prop2) {
    this.prop1 = prop1;
    this.prop2 = prop2;
}
let obj2 = new MyClass2(2, 2);
console.log(`obj2 name properties: ${Object.getOwnPropertyNames(obj2)}`)
```

---
## Sintassi con `class`

```js
class MyClass1 {
    constructor(prop1, prop2) {
        this.prop1 = prop1;
        this.prop2 = prop2;
    }
}
```

Questa sembra una classe “tipo Java”, ma in JS:
> ⚠️ È solo zucchero sintattico sopra le funzioni.

### Il `constructor`

È il metodo che viene eseguito quando fai:

```js
let obj1 = new MyClass1(2, 2);
```

E succede:

|Passo|Cosa fa JS|
|---|---|
|1|Crea un oggetto vuoto|
|2|Imposta `this` su quell’oggetto|
|3|Esegue il constructor|
|4|Restituisce l’oggetto|

Alla fine `obj1` è:

```js
{ prop1: 2, prop2: 2 }
```

---

## Sintassi con funzione

```js
function MyClass2(prop1, prop2) {
    this.prop1 = prop1;
    this.prop2 = prop2;
}
```

Questa è la **versione vecchia** (prima delle classi ES6).

Ma quando fai:

```js
let obj2 = new MyClass2(2, 2);
```

⚡ Succede la STESSA IDENTICA COSA di prima.

---

## Confronto

```js
console.log(Object.getOwnPropertyNames(obj1))
console.log(Object.getOwnPropertyNames(obj2))
```

Output in entrambi i casi:

```
["prop1", "prop2"]
```

Perché?

Perché le proprietà assegnate con `this` dentro il costruttore diventano **proprietà proprie dell’oggetto**.

---

## Riassunto dei concetti chiave

|Concetto|Spiegazione|
|---|---|
|`class` in JS|Non è come Java, è solo sintassi più pulita|
|Funzione costruttore|Vecchio modo di creare oggetti|
|`new`|Crea oggetto e lega `this`|
|`this.prop = valore`|Crea proprietà nell’oggetto|
|Risultato finale|Gli oggetti creati sono identici|

### Differenza

Con `class` è più facile definire metodi:

```js
class MyClass {
  method() {}
}
```

Con funzione:

```js
MyClass.prototype.method = function() {}
```

Ma sotto sotto… è sempre prototype.