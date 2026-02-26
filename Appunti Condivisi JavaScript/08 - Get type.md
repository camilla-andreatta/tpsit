Esercizio 6:
```js
// DEFINIZIONE CON CLASS
class MyClass {
    constructor(){
        console.log("object MyClass created");
        this.prop = "MyClassProp"; // proprietà propria dell'istanza
    }
}


// DEFINIZIONE CON FUNZIONE COSTRUTTORE
function MyFunction(){
    console.log("object MyFunction created");
    this.prop = "MyClassProp"; // proprietà propria dell'istanza
}


// CREAZIONE OGGETTI
let obj1 = new MyClass();
let obj2 = new MyFunction();


// typeof
// Tutti gli oggetti creati con new sono di tipo "object"
console.log('********* typeof **********');
console.log(typeof obj1); // object
console.log(typeof obj2); // object


// constructor.name
// Nome del costruttore da cui proviene l'oggetto
console.log('********* constructor.name **********');
console.log(Object.getPrototypeOf(obj1).constructor.name); // MyClass
console.log(Object.getPrototypeOf(obj2).constructor.name); // MyFunction


// PROTOTYPE REALE
// Verifica del collegamento al prototype
console.log('********* prototype check **********');
console.log(Object.getPrototypeOf(obj1) === MyClass.prototype); // true
console.log(Object.getPrototypeOf(obj2) === MyFunction.prototype); // true


// PROPRIETÀ PROPRIE
// Mostra solo le proprietà presenti direttamente nell'oggetto
console.log('********* Object.getOwnPropertyNames **********');
console.log(Object.getOwnPropertyNames(obj1)); // ["prop"]
console.log(Object.getOwnPropertyNames(obj2)); // ["prop"]


// SHADOWING (mascheramento)
// Aggiunta della stessa proprietà nel prototype
MyClass.prototype.prop = "protoPropC";
MyFunction.prototype.prop = "protoPropF";

console.log('********* prototype dopo modifica **********');
console.log(Object.getPrototypeOf(obj1));
console.log(Object.getPrototypeOf(obj2));


// Accesso alla proprietà
// Viene usata quella dell'oggetto, NON quella del prototype
console.log(obj1.prop); // "MyClassProp"
console.log(obj2.prop); // "MyClassProp"

```

---
## Creazione oggetti

```js
let obj1 = new MyClass();
let obj2 = new MyFunction();
```

Succede in entrambi i casi:

|Passo|Cosa fa JS|
|---|---|
|1|crea oggetto vuoto|
|2|collega il prototype|
|3|esegue costruttore|
|4|mette `prop` nell’oggetto|

Risultato per entrambi:

```js
{ prop: "MyClassProp" }
```

---

## `typeof`

```js
console.log(typeof obj1);
console.log(typeof obj2);
```

Output:

```
object
object
```

💡 Per JS **tutto ciò che è creato con `new` è un oggetto**, sia class che funzione.

---

## `constructor.name`

```js
Object.getPrototypeOf(obj1).constructor.name
Object.getPrototypeOf(obj2).constructor.name
```

Catena:

```
obj → prototype → constructor
```

Output:

```
MyClass
MyFunction
```

Serve a capire **da quale costruttore proviene l’oggetto**.

---

## Confronto prototype

```js
Object.getPrototypeOf(obj1) === MyClass.prototype
Object.getPrototypeOf(obj2) === MyFunction.prototype
```

Output:

```
true
true
```

Perché le istanze puntano al prototype del loro costruttore.

---

## Proprietà proprie

```js
Object.getOwnPropertyNames(obj1)
Object.getOwnPropertyNames(obj2)
```

Output:

```
["prop"]
["prop"]
```

Sono le proprietà messe dentro il costruttore con `this.prop`.

---

## Mascheramento (shadowing)

```js
MyClass.prototype.prop = "protoPropC";
MyFunction.prototype.prop = "protoPropF";
```

Ora i prototype hanno una proprietà `prop`.

Ma attenzione:

```
obj1 ha già prop = "MyClassProp"
obj2 ha già prop = "MyClassProp"
```

Quando accedi:

```js
console.log(obj1.prop);
console.log(obj2.prop);
```

Output:

```
MyClassProp
MyClassProp
```

Perché JS cerca così:
1. Nell’oggetto stesso
2. Se non trova → nel prototype

Qui la proprietà esiste già nell’oggetto, quindi quella del prototype è **nascosta**.

👉 Questo si chiama **mascheramento** o **shadowing**.

---

## Riassunto dei concetti chiave

- `class` e funzione costruttore producono oggetti allo stesso modo
- `typeof` di un’istanza è sempre `"object"`    
- Ogni oggetto ha un collegamento al `prototype` del suo costruttore, che si trova con `Object.getPrototypeOf(oggetto)`
- Le proprietà create con `this` sono **proprie dell’oggetto**    
- Le proprietà nel `prototype` sono condivise tra tutte le istanze    
- Se una proprietà esiste sia nell’oggetto che nel prototype → quella dell’oggetto **maschera** (shadowing) quella del prototype