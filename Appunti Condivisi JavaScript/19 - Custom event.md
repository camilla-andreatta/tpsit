```html
<body>
	<h1>con</h1>
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
		callback(elem);
	}
	
	const myEvent = new CustomEvent('highlight', {
		detail: {
			backgroundColor: bgColor
		}
	});
	
	// elem.dispatchEvent(myEvent);
	
	setTimeout(() => {
		elem.dispatchEvent(myEvent);
	}, 2000);
}

function addBorder(elem) {
	elem.style.border = "solid 2px #000";
}

note.addEventListener('highlight', function (e) {
	addBorder(this);
	console.log(e.detail);
});

// highlight div element
highlight(note);
```
Questo esempio mostra come usare un **Custom Event** in JavaScript per gestire azioni asincrone e comunicazioni tra funzioni.
Con gli eventi personalizzati, puoi far reagire più funzioni allo stesso evento, senza passare direttamente callback.
Rispetto all’esempio “senza custom event”, qui **la funzione `highlight` non deve sapere chi reagirà**, rende il codice più modulare e scalabile.

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

- Come prima, abbiamo 5 `div`, e solo `yellow` avrà lo sfondo giallo e il bordo aggiunto.

---

## Selezione dell’elemento

```js
let note = document.getElementById('yellow');
```

`note` punta al `div` con ID `yellow`, che sarà manipolato.

---

## La funzione `highlight`

```js
function highlight(elem, callback) {
    const bgColor = '#ffff55';
    elem.style.backgroundColor = bgColor;
    if (callback && typeof callback === 'function') {
        callback(elem);
    }

    const myEvent = new CustomEvent('highlight', {
        detail: {
            backgroundColor: bgColor
        }
    });

    setTimeout(() => {
        elem.dispatchEvent(myEvent);
    }, 2000);
}
```

Passo passo:
1. Cambia subito lo **sfondo** dell’elemento (`elem.style.backgroundColor = bgColor`).
2. Chiama **eventualmente la callback** se fornita (qui non la usiamo più, ma la lasciamo per compatibilità).
3. Crea un **Custom Event**:
    ```js
    const myEvent = new CustomEvent('highlight', {
        detail: { backgroundColor: bgColor }
    });
    ```
    - `'highlight'` → nome dell’evento
    - `detail` → informazioni aggiuntive che possiamo passare agli ascoltatori
4. Usa `setTimeout` per **dispatchare l’evento dopo 2 secondi**:
    ```js
    elem.dispatchEvent(myEvent);
    ```
    - Questo significa: “dopo 2 secondi, l’elemento ha subito un evento `highlight`”.

📌 Differenza chiave rispetto alle callback:
Non passiamo più direttamente una funzione; **chi ascolta l’evento decide cosa fare**, rendendo il codice più modulare.

---
## Funzione `addBorder`

```js
function addBorder(elem) {
    elem.style.border = "solid 2px #000";
}
```

- Come prima, serve a **aggiungere un bordo all’elemento**.
- La differenza è che **non la chiamiamo direttamente da `highlight`**, ma tramite l’evento.

---

## Ascoltatore dell’evento

```js
note.addEventListener('highlight', function (e) {
    addBorder(this);
    console.log(e.detail);
});
```

- Qui diciamo: “quando `note` riceve un evento `highlight`, esegui questa funzione”.
- `this` → l’elemento che ha ricevuto l’evento (`note`)
- `e.detail` → le informazioni che abbiamo passato con il Custom Event (`backgroundColor: '#ffff55'`)
- Dentro questa funzione chiamiamo `addBorder` e stampiamo il dettaglio nel console log.

💡 Questo mostra il **potere degli eventi personalizzati**: possiamo aggiungere più ascoltatori, ognuno che reagisce in modo diverso, senza modificare la funzione `highlight`.


---
## Dettagli aggiuntivi da MDN

### Sintassi completa di `new CustomEvent`
```js
let event = new CustomEvent(typeArg, customEventInit);
```

- `typeArg` → stringa con il **nome dell’evento** (`'highlight'`)
- `customEventInit` → oggetto opzionale con proprietà:
    - `detail` → qualsiasi dato tu voglia passare (es: `{ backgroundColor: '#ffff55' }`)
    - `bubbles` → `true/false`, decide se l’evento “risale” il DOM
    - `cancelable` → `true/false`, decide se l’evento può essere annullato con `event.preventDefault()`
💡 Nelle nostre lezioni hai visto solo `detail`, ma in teoria puoi controllare **propagazione e cancellazione** degli eventi.
---
### Proprietà dell’evento
Quando ascolti l’evento, l’oggetto passato al listener (`e` nel nostro esempio) ha:
- `e.type` → il nome dell’evento (`'highlight'`)
- `e.target` → l’elemento che ha dispatchato l’evento
- `e.currentTarget` → l’elemento a cui è attualmente associato l’ascoltatore
- `e.detail` → i dati passati con l’evento
📌 Notare la differenza tra `target` e `currentTarget`: importante per eventi **annidati** o quando usi `bubbles: true`.
---
### Propagazione e bubbling
- Gli eventi possono **risalire il DOM** se `bubbles` è `true`.
- Questo significa che un listener su un genitore può catturare eventi dei figli.
- Nei nostri esempi abbiamo lasciato `bubbles` di default (`false`), quindi l’evento resta **locale all’elemento**.
---
### Cancelable
- Se `cancelable: true`, puoi chiamare `e.preventDefault()` nell’ascoltatore per bloccare l’azione predefinita.
- Utile in form, link o altri comportamenti predefiniti.
---
### Riassunto dei concetti aggiuntivi MDN

|Concetto|Spiegazione|
|---|---|
|`bubbles`|Decide se l’evento risale il DOM|
|`cancelable`|Decide se l’evento può essere annullato|
|`e.type`|Nome dell’evento|
|`e.target`|Elemento che ha dispatchato l’evento|
|`e.currentTarget`|Elemento a cui è collegato il listener in quel momento|
|`detail`|Dati personalizzati passati con l’evento|

---
## Differenze principali rispetto alla versione senza custom event

|Versione|Come funziona|
|---|---|
|Callback|La funzione `highlight` deve sapere **chi chiamare dopo**|
|Custom Event|La funzione `highlight` **non sa chi reagirà**, solo dispatcha un evento; gli ascoltatori decidono cosa fare|
|Scalabilità|Con eventi puoi aggiungere molti listener senza cambiare `highlight`|
|Dettagli|Con `detail` puoi passare informazioni extra senza legare la logica|

---

## Riassunto dei concetti chiave

| Concetto               | Spiegazione                                                                           |
| ---------------------- | ------------------------------------------------------------------------------------- |
| Custom Event           | Evento creato da JS che possiamo dispatchare e ascoltare                              |
| `dispatchEvent`        | Fa partire l’evento su un elemento                                                    |
| `addEventListener`     | Ascolta un evento (standard o custom) su un elemento                                  |
| `detail`               | Oggetto opzionale passato con l’evento, utile per dati extra                          |
| Modularità             | Gli eventi permettono di separare chi produce l’evento da chi lo gestisce             |
| Closure e `setTimeout` | Mantengono la sequenza temporale e il contesto corretto anche se l’evento è asincrono |

>[!footer]
>nnn
>.callout[data-callout="my-callout-type"] {
    --callout-color: 0, 0, 0;
    --callout-icon: icon-id;
    --callout-icon: '<svg>...custom svg...</svg>';
}