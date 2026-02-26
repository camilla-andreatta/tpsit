Quando c'è un evento in JS (tipo `click`, `keydown`, ecc.), **l’evento NON appartiene solo all’elemento cliccato**.
L’evento **viaggia nel DOM**, seguendo delle regole precise. Questo "viaggio" si chiama **event propagation**.

---
## Cos'è l'Event Propagation

Event propagation =
👉 **come un evento si muove tra gli elementi HTML**

Non nasce “fermo” sul bottone.
Parte dall’alto della pagina e poi torna su.

Questo viaggio ha **3 fasi ben definite**.

---

## Le 3 fasi

### 1. Capturing phase

- L’evento parte dal **root del DOM** (`document`)
- Scende verso l’elemento cliccato    
- Passa da tutti i genitori    

Percorso concettuale:

```
document → html → body → outer → inner → button
```

⚠️ Di default **NON vedi questa fase**, a meno che tu non la chieda esplicitamente.

---

### 2️. Target phase

- L’evento arriva **sull’elemento cliccato**
- Qui viene eseguito il listener del target 

In questo caso:

```
button
```

---

### 3️. Bubbling phase

- L’evento **risale** dal target fino al root
- Passa di nuovo da tutti i genitori

Percorso:

```
button → inner → outer → body → html → document
```

👉 **Questa è la fase di default**.

---

## Analisi dell’HTML

```html
<div id="outer">
  <div id="inner">
    <button id="btn">Click</button>
  </div>
</div>
```

Gerarchia DOM:

```
outer
 └─ inner
     └─ button
```

---

## Event listener di default (bubbling)

```js
outer.addEventListener('click', () => console.log('outer'));
inner.addEventListener('click', () => console.log('inner'));
btn.addEventListener('click', () => console.log('button'));
```

Qui:
- non passi il terzo parametro
- quindi stai ascoltando **in bubbling**

### Click su `button`

Ordine di esecuzione:

```
button → inner → outer
```

Perché?
- l’evento parte dal target
- poi risale verso i genitori

Questo è il comportamento **standard di JS**.

---

## Capturing phase esplicita

```js
outer.addEventListener(
  'click',
  () => console.log('outer (capturing)'),
  true
);
```

Quel `true` significa:
👉 **ascolta l’evento in capturing, non in bubbling**

Ora cosa succede cliccando il bottone?

### Ordine completo:
1. `outer (capturing)` ← fase di cattura
2. `button` ← target
3. `inner` ← bubbling
4. `outer` ← bubbling

📌 Quindi:
- capturing = scende
- bubbling = risale

---

## 🔹 Fermare la propagazione: `stopPropagation()`

```js
btn.addEventListener('click', (e) => {
    console.log('button');
    e.stopPropagation();
});
```

Qui stai dicendo:
> “L’evento **muore qui**, non andare oltre”.

Risultato:
- viene eseguito **solo** il listener del button
- `inner` e `outer` **non ricevono nulla**    

Importantissimo:
- `stopPropagation()` **NON blocca il comportamento di default**
- blocca **solo il viaggio dell’evento**    

---

## 🔹 Differenza CRUCIALE

### `stopPropagation()`
❌ blocca la propagazione  
✅ l’azione di default resta

### `preventDefault()`
❌ blocca il comportamento di default  
✅ l’evento continua a propagarsi

---

## `preventDefault()`

`preventDefault()` serve a dire:
> “Questo evento è partito, ma **non fare l’azione standard del browser**”.

---

### Form submit

```js
form.addEventListener('submit', (e) => {
    e.preventDefault();
});
```

Comportamento normale:
- invio form    
- ricarica pagina

Con `preventDefault()`:
- niente reload    
- gestisci tu i dati (JS, fetch, AJAX, ecc.)

---

### Link

```js
link.addEventListener('click', (e) => {
    e.preventDefault();
});
```

Comportamento normale:
- vai all’URL del link

Con `preventDefault()`:
- il browser **non naviga**    
- puoi fare routing custom (SPA)

---

### Context menu (tasto destro)

```js
element.addEventListener('contextmenu', (e) => {
    e.preventDefault();
});
```

Comportamento normale:
- menu contestuale del browser

Con `preventDefault()`:
- puoi mostrare **un tuo menu personalizzato**

---

### Drag & Drop (caso super importante)

```js
dropZone.addEventListener('dragover', (e) => {
    e.preventDefault();
});
```

Qui è obbligatorio:
- senza `preventDefault()`
- il browser **non permette il drop**

Questo è uno di quei casi che agli esami piacciono molto.

---

## Riassunto dei concetti chiave

- Gli eventi **viaggiano nel DOM**
- 3 fasi: capturing → target → bubbling
- Di default ascolti in bubbling
- `true` nel listener = capturing
- `stopPropagation()` = blocca il viaggio
- `preventDefault()` = blocca l’azione standard