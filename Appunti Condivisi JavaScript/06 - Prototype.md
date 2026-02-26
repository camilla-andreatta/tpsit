```js
function doSomething() {}
```

Questa funzione è un oggetto, e in JS **ogni oggetto ha un prototipo**.

---

```js
Object.getPrototypeOf(doSomething)
```

Significa:
> “Qual è l’oggetto da cui **la funzione doSomething** eredita?”

Risposta: `Function.prototype`
Perché **doSomething è una funzione**, quindi eredita da `Function.prototype`.

---

```js
doSomething.prototype
```

NON è il prototipo della funzione ❗
È un oggetto speciale che serve quando fai:

```js
new doSomething()
```

È il prototipo che verrà assegnato agli OGGETTI creati con `new`

---

|Espressione|Significato|
|---|---|
|`Object.getPrototypeOf(doSomething)`|prototipo **della funzione**|
|`doSomething.prototype`|prototipo **delle istanze create dalla funzione**|

---


```
doSomething  (è una funzione)
   ↑
   | eredita da
   |
Function.prototype
```


```
new doSomething()  (istanza)
        ↑
        | eredita da
        |
doSomething.prototype
```


---

```js
console.log(Object.getPrototypeOf(doSomething) === Function.prototype); // true
```

```js
let x = new doSomething();
console.log(Object.getPrototypeOf(x) === doSomething.prototype); // true
```

---


```js
console.log(doSomething.prototype === Object.getPrototypeOf(doSomething));
```

> “Perché è false?”

> Perché `Object.getPrototypeOf(doSomething)` restituisce il prototipo della funzione (Function.prototype), mentre `doSomething.prototype` è l’oggetto usato come prototipo delle istanze create con `new doSomething()`. Sono due oggetti diversi con ruoli diversi.

---
## Appunti ripasso

link: [MDN Object](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object) [MDN Prototype](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/getPrototypeOf)

Come funziona l'ereditarietà?
non c'è più il meccanismo a classi in cui definiamo come sono fatti gli oggetti. Tutti gli oggetti hanno un prototype.
defineProperty è un set (c'è anche il get)
`__proto__` è deprecato, veniva usato per recuperare il prototipo
Object.getPrototypeOf, metodo della classe Object, è utilizzato per recuperare il prototipo.

In alcuni funzioni c'è la proprietà prototype (!= da prototipo di). 
Il vantaggio di lavorare con le classi era quello di creare un modello/uno stampo, qui recupero questa possibilità di riutilizzo del codice settando il prototype, per cui l'oggetto a cui settiamo il prototype erediterà da esso le sue proprietà. Il prototype viene quindi utilizzato non per creare un singolo oggetto, ma tanti.

Con la sintassi class (di cui non ho necessità), c'è un metodo **constructor** (che se immaginiamo la classe come un oggetto il constructor è una sua proprietà) in cui c'è il this.
Le funzioni(non arrow) hanno anche il ruolo di **creare oggetti** (allo stesso modo di una classe), per questo il this e new non sono legati propriamente alle classi.

il prototipo dell'oggetto funzione è la stessa cosa del campo prototype? No!
Object.getPrototypeOf(function) è Object Function
se ho una arrow function, molto veloci, non ha il campo prototype --> non si creano oggetti con arrowFunction.
function e arrowFunction non sono quindi soluzioni alternative.
La differenza tra gli oggetti `Function` e `ArrowFunction` è che la prima ha la proprietà `prototype`, la seconda no.

```Javascript
const fun = {p1,p2} => {
      this.p1 = p1;
      this.p2 = p2;
}

let obj = new fun (3, "ciao");
console.log(obj);
```
**errore** : fun is not a constructor poiché non c'è prototype non posso creare oggetti

``` Javascript
const fun = function(p1,p2) {
      this.p1 = p1;
      this.p2 = p2;
}

fun.prototype.p0 = 666;

let obj = new fun (3, "ciao");

console.log(Object.getPrototypeOf(obj)); // Output: { p0 : 666 }
console.log(obj.p0); //funziona!!!, Output : 666
console.log(obj) //non mostra p0, Output: fun { p1 : 3, p2 : 'ciao'}
console.log(Object.keys(obj)); //Output : [ 'p1', 'p2' ]
console.log(Object.getOwnPropertyNames(obj)); //Output : [ 'p1', 'p2' ]
console.log(Object.getOwnPropertyNames(Object.getPrototypeOf(obj))); //Output : [ 'constructor', 'p0']
```
questo funziona!!!!!

Il campo prototype di una funzione è il prototipo degli oggetti che io creo con quella funzione (non fa riferimento quindi al prototipo della funzione), permette di creare il prototipo di tutti quegli oggetti che andrò a creare con quella funzione.

In js si possono aggiungere proprietà ad un oggetto perchè non è creato con uno stampo come in Java (non ci sono classi)

getOwnPropertiesNames (sue dell'oggetto e non del prototipo) recupera tutte le proprietà di un oggetto (keys recupera solo quelle dette enumerabili a cui puoi accedere ).

per creare prima l'oggetto e poi il prototipo Object.create(proto)
`let g = Object.create(proto)`
Object.setPrototypeOf() cambiare prototipo di un oggetto è una cosa da non fare nel js moderno

oggetto book con proprietà title e author <-- chapter1 avrà anche lui title
per recuperare title del libro : Object.getPrototypeOf(chapter1).title
un book non è un capitolo, ma chat1 fa parte del book

definizione di prototipo di un oggetto: punto di partenza da cui posso costruire un oggetto in js

non esiste una ereditarietà a classi, ma a oggetti, qui non ci sono gerarchie is-a 
