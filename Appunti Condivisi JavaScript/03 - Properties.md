Esercizio 2:
```javascript
let list = [1, 2, 3];
console.log(Object.keys(list));
console.log(Object.getOwnPropertyNames(list));
console.log(Object.getOwnPropertyDescriptor(list, 'length'));
console.log(list.length);
console.log(list['length']);

let obj1 = {}
Object.defineProperty( obj1, "prop1",
{
  value: "test",
  writable: true,
  enumerable: true,
  configurable: true
});
console.log(obj1);

obj2 = {}
Object.defineProperty( obj2, "prop2",
{
  value: "test",
  writable: false,
  enumerable: true,
  configurable: true
});
console.log(obj2);
obj2.prop2 = "test 2";
console.log(obj2);

obj3 = {}
Object.defineProperty( obj3, "prop3",
{
  value: "test",
  writable: true,
  enumerable: false,
  configurable: true
});
console.log(Object.keys(obj3));
console.log(Object.getOwnPropertyNames(obj3));
for(prop in obj3) {
  console.log(obj3[prop]);
}
```


---

## Gli array sono oggetti

```js
let list = [1, 2, 3];
```

Un array in JavaScript **è un oggetto speciale**.

Infatti ha proprietà come:

| Chiave   | Valore |
| -------- | ------ |
| "0"      | 1      |
| "1"      | 2      |
| "2"      | 3      |
| "length" | 3      |

---

### Object.keys

```js
console.log(Object.keys(list));
```

Restituisce **solo le proprietà enumerabili**.
proprietà enumerabili = proprietà con l'attributo enumerable : true, che sono visibili iterando sull'oggetto con metodi come il for ... in ... o il metodo Object.keys(obj)

Output:

```js
["0", "1", "2"]
```

📌 Non include `"length"` perché non è enumerable.
Le proprietà non enumerabili sono proprietà con attributo enumerable : false e sono visibili solamente con il metodo Object.getOwnPropertyNames(obj)
sono proprietà non-enumerabili proprietà particolari, per esempio la proprietà length di un array.

Quindi `Object.keys()` su un array restituisce solo le **chiavi**, cioè gli indici.


---

### Object.getOwnPropertyNames

```js
console.log(Object.getOwnPropertyNames(list));
```

Restituisce **TUTTE** le proprietà dell’oggetto.

Output:

```js
["0", "1", "2", "length"]
```


---

### Descrittore della proprietà length

```js
console.log(Object.getOwnPropertyDescriptor(list, 'length'));
```

Output (semplificato):

```js
{
  value: 3,
  writable: true,
  enumerable: false,
  configurable: false
}
```

💡 Qui entra la teoria importante:

|Attributo|Significato|
|---|---|
|**value**|valore della proprietà|
|**writable**|si può modificare?|
|**enumerable**|appare nei cicli e in Object.keys()?|
|**configurable**|si può cancellare o ridefinire?|


---

### length letto in due modi

```js
console.log(list.length);
console.log(list['length']);
```

Sono **identici** → stesso valore, 3.


---

## Creazione proprietà con controllo

Qui non usiamo la sintassi normale `{}` ma:

```js
Object.defineProperty()
```

Serve per **decidere le caratteristiche interne** della proprietà.


---

### obj1 — proprietà normale

```js
Object.defineProperty(obj1, "prop1", {
  value: "test",
  writable: true,
  enumerable: true,
  configurable: true
});
```

Questa è praticamente una proprietà “normale”.

Può:
- essere modificata
- essere vista nei cicli
- essere cancellata


---

### obj2 — proprietà NON modificabile

```js
Object.defineProperty(obj2, "prop2", {
  value: "test",
  writable: false,
  enumerable: true,
  configurable: true
});
```

Qui cambia:

| writable: false |

Significa:  
❌ Non puoi cambiare il valore.

```js
obj2.prop2 = "test 2";
```

👉 Ignorato (oppure errore in strict mode)

Output resta:

```js
{ prop2: "test" }
```


---

### obj3 — proprietà nascosta

```js
Object.defineProperty(obj3, "prop3", {
  value: "test",
  writable: true,
  enumerable: false,
  configurable: true
});
```

Qui cambia:

| enumerable: false |

Significa:
- Non appare in `Object.keys`
- Non appare in `for...in`

### Infatti:

```js
console.log(Object.keys(obj3)); 
// []
```

```js
console.log(Object.getOwnPropertyNames(obj3));
// ["prop3"]
```

```js
for (prop in obj3) {
  console.log(obj3[prop]);
}
// niente output
```


---

## Riassunto dei concetti chiave

Una proprietà NON è solo:

```
chiave → valore
```

Ma è:

```
chiave → valore + regole interne
```

Le 3 regole principali:

|Regola|Effetto|
|---|---|
|writable|si può cambiare il valore?|
|enumerable|si vede nei cicli?|
|configurable|si può cancellare o ridefinire?|


---

### Differenza fondamentale

```js
obj.prop = 5;
```

crea proprietà con:
- writable: true
- enumerable: true
- configurable: true

```js
Object.defineProperty()
```

ti permette di **rompere questo comportamento standard**.