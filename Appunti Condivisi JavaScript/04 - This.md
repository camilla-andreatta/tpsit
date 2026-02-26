Esercizio 3:
```javascript
let rabbit = {};

rabbit.color = "white";
rabbit.speak = function(line) {
  console.log(`The ${this.color} rabbit says '${line}'`);
};

rabbit.speak('hello');
```


---

## Creazione oggetto

```js
let rabbit = {};
rabbit.color = "white";
```

Oggetto con una proprietà:

|chiave|valore|
|---|---|
|color|"white"|


---

## Aggiunta di una FUNZIONE dentro l’oggetto

```js
rabbit.speak = function(line) {
  console.log(`The ${this.color} rabbit says '${line}'`);
};
```

Qui stai aggiungendo una proprietà che **contiene una funzione**.

Quando una funzione sta dentro un oggetto si chiama:

### Metodo

|Termine|Significato|
|---|---|
|Funzione|Blocco di codice|
|Metodo|Funzione che appartiene a un oggetto|


---

## Il mistero di `this`

Dentro la funzione c’è:

```js
this.color
```

`this` significa:

> **l’oggetto che sta eseguendo il metodo**

⚠️ Non è fisso. Dipende da **CHI chiama la funzione**.

---

## Chiamata del metodo

```js
rabbit.speak('hello');
```

Qui:
- l’oggetto che chiama è `rabbit`
- quindi `this = rabbit`

All’interno della funzione diventa:

```js
this.color → rabbit.color → "white"
```

Output:

```
The white rabbit says 'hello'
```

---

## **Regola fondamentale**

👉 **`this` è deciso al momento della chiamata, NON quando la funzione è scritta.**

Il prof con questo esercizio vuole introdurre:

|Caso|Valore di `this`|
|---|---|
|Metodo chiamato con oggetto|quell’oggetto|
|Funzione normale|window (o undefined in strict mode)|
|Arrow function|NON ha il suo this|


---

## Perché non scrivere semplicemente `rabbit.color`?

Perché così il metodo funziona solo per **quel** rabbit.

Con `this` invece il metodo diventa riutilizzabile:

```js
let rabbit2 = { color: "black", speak: rabbit.speak };
rabbit2.speak("hi");
```

Output:

```
The black rabbit says 'hi'
```

✨ Stessa funzione, oggetti diversi.

---

## Riassunto dei concetti chiave

1. Un oggetto può contenere funzioni
2. Una funzione dentro un oggetto è un **metodo**
3. `this` si riferisce all’oggetto che chiama il metodo
4. `this` rende il codice riutilizzabile
5. Le stringhe template `` `${}` `` inseriscono variabili nel testo