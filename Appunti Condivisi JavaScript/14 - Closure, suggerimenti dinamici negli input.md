```html
<body>
	<p id="help">Helpful notes will appear here</p>
	 <p>E-mail: <input type="text" id="email" name="email"></p>
	 <p>Name: <input type="text" id="name" name="name"></p>
	 <p>Age: <input type="text" id="age" name="age"></p>
</body>
```
```js
function showHelp(help) {
	document.getElementById('help').innerHTML = help;
}

function setupHelp() {
	let helpText = [
		{ id: 'email', help: 'Your e-mail address' },
		{ id: 'name', help: 'Your full name' },
		{ id: 'age', help: 'Your age (you must be over 16)' }
	];

	for (let i = 0; i < helpText.length; i++) {
		let item = helpText[i];
		document.getElementById(item.id).onfocus = () => {
			showHelp(item.help);
		}
	}
}

setupHelp();
```

---
## La funzione `showHelp`

```js
function showHelp(help) {
  document.getElementById('help').innerHTML = help;
}
```

Questa funzione prende un testo (`help`) e lo mostra nel paragrafo con `id="help"`.
`document.getElementById('help')` prende **l’elemento HTML** dove vogliamo scrivere il testo.
`.innerHTML = help` sostituisce il contenuto del paragrafo con la stringa passata.

---

## La funzione `setupHelp`

```js
function setupHelp() {  
	let helpText = [
	    { id: 'email', help: 'Your e-mail address' },
	    { id: 'name', help: 'Your full name' },
	    { id: 'age', help: 'Your age (you must be over 16)' }
	];
```

Qui creiamo un **array di oggetti**, dove ogni oggetto ha:
- `id`: l’ID del campo input da collegare
- `help`: il testo di aiuto da mostrare    

---

## Associare la funzione ai campi

```js
for (let i = 0; i < helpText.length; i++) {
	let item = helpText[i];
	document.getElementById(item.id).onfocus = () => {
		showHelp(item.help);
	}
}
```

Usiamo un **ciclo** per scorrere tutti gli oggetti nell’array `helpText`.
`document.getElementById(item.id)` prende l’input corrispondente.
`.onfocus = () => { ... }` significa: quando l’utente **clicca o entra nel campo**, esegui la funzione.
La funzione **chiama `showHelp(item.help)`**, mostrando il testo corretto nel paragrafo.

💡 Questo è un esempio pratico di **closure**:
- L’arrow function “ricorda” il valore di `item.help` per ogni iterazione del ciclo. 
- Anche dopo che il ciclo è finito, ogni campo mantiene il suo suggerimento corretto.

---
## Riassunto dei concetti chiave

|Concetto|Spiegazione|
|---|---|
|`document.getElementById()`|Permette di accedere a un elemento HTML tramite il suo `id`|
|`.innerHTML`|Modifica il contenuto interno di un elemento HTML|
|Eventi DOM (`onfocus`)|Permette di eseguire codice quando l’utente interagisce con un campo|
|Array di oggetti|Organizza dati correlati, utile per gestire più elementi in modo compatto|
|Closure|Le funzioni associate ricordano i dati dello scope originale (`item.help`) anche dopo il ciclo|
