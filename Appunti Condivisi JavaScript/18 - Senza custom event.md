```html
<body>
	<h1>Senza custom event</h1>
	<div style="width: 100px; border: solid 2px #000; padding: 10px">
		<div>one</div>
		<div id="yellow">two</div>
		<div>three</div>
		<div>four</div>
		<div>five</div>
	</div>
</body>
```
```js
let note = document.getElementById('yellow');

function highlight(elem, callback) {
	const bgColor = '#ffff55';
	elem.style.backgroundColor = bgColor;
	if (callback && typeof callback === 'function') {
		// callback(elem);
		setTimeout(() => {
			callback(elem);
		}, 2000);
	}
}

function addBorder(elem) {
	elem.style.border = "solid 2px #000";
}

highlight(note, addBorder);
```

- Questo esempio mostra come eseguire un’operazione su un elemento della pagina (`highlight`) e poi far partire **un’azione successiva** (`addBorder`) usando una **callback**.
- Non ci sono **custom events** in questo caso; vedremo più avanti come fare con gli eventi personalizzati.    

---

## Gli elementi HTML

```html
<div style="width: 100px; border: solid 2px #000; padding: 10px">
    <div>one</div>
    <div id="yellow">two</div>
    <div>three</div>
    <div>four</div>
    <div>five</div>
</div>
```

- Abbiamo un contenitore con 5 `div` interni.
- Solo uno di questi ha `id="yellow"`, che sarà **l’elemento su cui lavoriamo**.
- Stile iniziale: bordo nero di 2px, padding di 10px.

---

## Selezione dell’elemento

```js
let note = document.getElementById('yellow');
```

- `getElementById` prende l’elemento con ID `yellow`.
- Ora `note` punta a quel `div`, e possiamo manipolarlo con JS.

---

## La funzione `highlight`

```js
function highlight(elem, callback) {
    const bgColor = '#ffff55';
    elem.style.backgroundColor = bgColor;
    if (callback && typeof callback === 'function') {
        setTimeout(() => {
            callback(elem);
        }, 2000);
    }
}
```

Passo passo:
1. Riceve due argomenti:
    - `elem`: elemento da evidenziare
    - `callback`: funzione da eseguire **dopo**
2. Cambia lo **sfondo** dell’elemento a giallo chiaro (`#ffff55`).
3. Controlla se `callback` esiste ed **è una funzione** (`typeof callback === 'function'`).
4. Se sì, la esegue dopo 2 secondi con `setTimeout`.
    - Qui si vede il concetto di **callback**: passiamo una funzione come argomento per dire “quando finisco questa operazione, esegui quest’altra”.

📌 Importante: senza callback, `highlight` farebbe solo cambiare colore e basta.

---

## La funzione `addBorder`

```js
function addBorder(elem) {
    elem.style.border = "solid 2px #000";
}
```

- Questa funzione **aggiunge un bordo nero** all’elemento ricevuto.
- Serve come **azione successiva** a `highlight`.
- È un esempio semplice di **funzione callback**: viene passata come argomento a un’altra funzione.

---
## Riassunto dei concetti chiave

1. **Callback**
    - Una funzione passata come argomento ad un’altra funzione
    - Permette di eseguire codice **dopo che un’operazione è completata**, utile per operazioni asincrone come `setTimeout` o richieste HTTP

2. **Manipolazione DOM**
    - `elem.style.backgroundColor` e `elem.style.border` cambiano direttamente lo stile dell’elemento selezionato

3. **setTimeout**
    - Permette di eseguire codice **dopo un certo ritardo**
    - In combinazione con callback permette di **gestire sequenze di operazioni** senza bloccare il browser

4. **Sequenza di esecuzione**
    - JS legge le righe in ordine
    - Le callback permettono di **posticipare certe azioni**, mantenendo la logica chiara e separata

|Concetto|Spiegazione|
|---|---|
|Callback|Funzione passata come argomento per essere eseguita in un secondo momento|
|Manipolazione DOM|Cambiare stile, contenuto o attributi di un elemento HTML da JS|
|setTimeout|Permette di ritardare l’esecuzione di una funzione|
|Sequenza controllata|Usando callback, possiamo far succedere operazioni in ordine, anche se alcune sono asincrone|
|Eventuale passaggio dati|La callback riceve l’elemento su cui lavorare (`elem`)|
