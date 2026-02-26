Esercizio 1:
```javascript
let obj = { prop1: "prop1" };
console.log(obj);
obj.prop2 = 12;

console.log(obj);
Object.preventExtensions( obj );
console.log(`obj is extensible: ${Object.isExtensible( obj )}`);
obj.prop3 = 666;
console.log(obj);

for(prop in obj) {
  console.log(obj[prop]);
}
```
## Creazione dell’oggetto

```js
let obj = { prop1: "prop1" };
```

Qui stai creando un oggetto con **una sola proprietà**:

|Chiave|Valore|
|---|---|
|prop1|"prop1"|

È come dire:

> “Ho una scatola chiamata `obj` che contiene un’etichetta `prop1` con valore `"prop1"`”.

---

## Stampa dell’oggetto

```js
console.log(obj);
```

Output:

```js
{ prop1: "prop1" }
```

---

## Aggiunta di una nuova proprietà

```js
obj.prop2 = 12;
```

Stai **modificando l’oggetto dopo la creazione**.  
Ora dentro ci sono:

| Chiave | Valore  |
| ------ | ------- |
| prop1  | "prop1" |
| prop2  | 12      |

Output successivo:

```js
{ prop1: "prop1", prop2: 12 }
```

📌 **Teoria importante:**  
Gli oggetti in JS sono **dinamici** → puoi aggiungere proprietà in qualsiasi momento.

---

## Blocco dell’estensione dell’oggetto

```js
Object.preventExtensions(obj);
```

Significa:

> ❗ Da ora in poi **NON puoi più aggiungere nuove proprietà** a questo oggetto.

Ma attenzione:

- puoi ancora **modificare** quelle esistenti
- puoi **eliminarle**
- non puoi crearne di nuove

---

## Verifica

```js
console.log(`obj is extensible: ${Object.isExtensible(obj)}`);
```

Output:

```
obj is extensible: false
```

---

## Tentativo di aggiungere una proprietà

```js
obj.prop3 = 666;
```

⚠️ Qui il codice **prova** ad aggiungere `prop3`.

Ma l’oggetto **non è più estendibile**.

👉 Risultato:

- In modalità normale: l’istruzione viene **ignorata**
- In strict mode darebbe errore

Quindi l’oggetto rimane:

```js
{ prop1: "prop1", prop2: 12 }
```

---

## Ciclo sulle proprietà

```js
for (prop in obj) {
  console.log(obj[prop]);
}
```

Questo è un **for...in**, serve per scorrere le proprietà di un oggetto.

Ad ogni giro:

- `prop` contiene il **nome della proprietà**
- `obj[prop]` prende il valore

Giri:

|Iterazione|prop|obj[prop]|
|---|---|---|
|1|"prop1"|"prop1"|
|2|"prop2"|12|

Output finale:

```
prop1
12
```

---

## Riassunto dei concetti chiave

1. **Gli oggetti sono dinamici**
2. Esiste il concetto di **oggetto estendibile**
3. `Object.preventExtensions()` blocca l’aggiunta di nuove proprietà
4. `Object.isExtensible()` controlla lo stato
5. `for...in` scorre le **chiavi** di un oggetto
6. `obj[prop]` è accesso dinamico alle proprietà