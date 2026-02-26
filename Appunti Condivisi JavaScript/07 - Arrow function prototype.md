Esercizio 5:
```js
// ===== FUNZIONE NORMALE (costruttore) =====
function DoSomething(){}

// prototipo della funzione (non quello delle istanze)
console.log(Object.getPrototypeOf(DoSomething)); // Function.prototype

// prototype delle istanze
console.log(DoSomething.prototype === Object.getPrototypeOf(DoSomething)); // false


// ===== ARROW FUNCTION =====
const DoSomethingArrow = () => {};

console.log(Object.getPrototypeOf(DoSomethingArrow)); // Function.prototype
console.log(DoSomethingArrow.prototype); // undefined


// ===== AGGIUNTA PROPRIETÀ AL PROTOTYPE =====
DoSomething.prototype.foo = "bar";
console.log(DoSomething.prototype);


// ===== CREAZIONE ISTANZA =====
let instance = new DoSomething();
instance.prop = "some value";

console.log(instance); // proprietà propria
console.log(instance.foo); // ereditata dal prototype


// ===== MODIFICA PROTOTYPE TRAMITE ISTANZA =====
Object.getPrototypeOf(instance).bla = "bla";

console.log(Object.getPrototypeOf(instance));
console.log(Object.getPrototypeOf(instance) === DoSomething.prototype); // true
```

---
## Funzione normale

```js
function doSomething(){}
```

Una funzione normale in JS:
- è un oggetto 
- può essere usata con `new`
- ha una proprietà speciale: **`prototype`**

---

### Prototype della funzione

```js
console.log(Object.getPrototypeOf(doSomething));
```

Restituisce il prototipo della funzione stessa, cioè:

```
Function.prototype
```

---

```js
console.log(doSomething.prototype === Object.getPrototypeOf(doSomething));
```

❌ FALSE
Perché:

| Cosa                                 | Significato                                   |
| ------------------------------------ | --------------------------------------------- |
| `Object.getPrototypeOf(doSomething)` | prototipo della funzione                      |
| `doSomething.prototype`              | oggetto che diventerà prototipo delle istanze |
Sono due cose diverse.

---

## Arrow function

```js
const doSomethingArrowFunction = () => {};
```

⚠️ DIFFERENZA ENORME:

Le arrow function:
❌ Non hanno `prototype`  
❌ Non possono essere usate con `new`  
❌ Non sono costruttori

```js
console.log(Object.getPrototypeOf(doSomethingArrowFunction));
```
→ `Function.prototype`

```js
console.log(doSomethingArrowFunction.prototype);
```
→ `undefined`, perché non possono avere istanze

---

## Aggiunta proprietà al prototype

```js
doSomething.prototype.foo = "bar";
```

Qui stai dicendo:

> Tutti gli oggetti creati con `new doSomething()` avranno accesso a `foo`.

---

## Creazione istanza

```js
let instance = new doSomething();
instance.prop = "some value";
```

Ora `instance` è:

```js
{
  prop: "some value"
}
```

Ma **eredita** da:

```js
doSomething.prototype → { foo: "bar" }
```

---

### Accesso alla proprietà ereditata

```js
console.log(instance.foo);
```

Output:

```
bar
```

Perché JS cerca:
1. Nell’oggetto
2. Se non trova → nel prototype

---

## Modifica del prototype dopo la creazione

```js
Object.getPrototypeOf(instance).bla = "bla";
```

Aggiungi `bla` al prototype.

TUTTE le istanze lo vedranno.

---

```js
console.log(Object.getPrototypeOf(instance) === doSomething.prototype);
```

→ TRUE  
Perché le istanze puntano proprio lì.

---

## Riassunto dei concetti chiave

|Concetto|Spiegazione|
|---|---|
|Funzione normale|può essere costruttore|
|Arrow function|NON è costruttore|
|`.prototype`|usato dalle istanze|
|`Object.getPrototypeOf()`|prototipo reale|
|Ereditarietà prototipale|oggetti ereditano da altri oggetti|
