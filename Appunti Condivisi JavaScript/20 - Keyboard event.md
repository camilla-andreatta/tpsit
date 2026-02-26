```html
<body>
	<div class="fx">
		<div>
			<textarea rows="5" name="test-target" id="test-target" class="textarea is-info"></textarea>
			<button type="button" name="btn-reset" id="btn-reset" class="button is-dark">Reset</button>
		</div>
		
		<div class="flex">
			<pre id="console-log"></pre>
		</div>
	</div>
</body>
```
```js
const textarea = document.getElementById("test-target");
const consoleLog = document.getElementById("console-log");
const btnReset = document.getElementById("btn-reset");

function logMessage(message) {
	consoleLog.innerText += `${message}\n`;
}

textarea.addEventListener("keydown", (e) => {
	if (!e.repeat) {
		logMessage(`Key "${e.key}" pressed [event: keydown]`);
	} else {
		logMessage(`Key "${e.key}" repeating [event: keydown]`);
	}
});



textarea.addEventListener("beforeinput", (e) => {
	logMessage(`Key "${e.data}" about to be input [event: beforeinput]`);
});

textarea.addEventListener("input", (e) => {
	logMessage(`Key "${e.data}" input [event: input]`);
});

textarea.addEventListener("keyup", (e) => {
	logMessage(`Key "${e.key}" released [event: keyup]`);
});

btnReset.addEventListener("click", (e) => {
	let child = consoleLog.firstChild;
	while (child) {
		consoleLog.removeChild(child);
		child = consoleLog.firstChild;
	}
	textarea.value = "";
});
```

- Questo esempio mostra **come catturare e distinguere diversi eventi legati alla tastiera** (`keydown`, `keyup`, `beforeinput`, `input`).
- Inoltre, mostra come **loggare questi eventi in tempo reale** su una pagina web.
- Lo scopo è capire **la sequenza degli eventi della tastiera e cosa fanno**.

---

## Gli elementi HTML principali

```html
<textarea id="test-target" class="textarea is-info"></textarea>
<button id="btn-reset" class="button is-dark">Reset</button>
<pre id="console-log"></pre>
```

- `textarea` → dove l’utente digita i tasti
- `button` → serve a resettare `textarea` e log
- `pre` → visualizza in maniera leggibile la sequenza degli eventi, mantenendo i ritorni a capo

---

## Funzione di log

```js
function logMessage(message) {
    consoleLog.innerText += `${message}\n`;
}
```

- Semplice funzione per **aggiungere testo al `pre`**, una riga alla volta.
- Mostra chiaramente la sequenza di eventi che avvengono quando si preme un tasto.

---

## Eventi catturati

### `keydown`

```js
textarea.addEventListener("keydown", (e) => {
    if (!e.repeat) {
        logMessage(`Key "${e.key}" pressed [event: keydown]`);
    } else {
        logMessage(`Key "${e.key}" repeating [event: keydown]`);
    }
});
```

- Si attiva **quando un tasto viene premuto**.
- `e.key` → il tasto premuto    
- `e.repeat` → `true` se il tasto viene tenuto premuto e ripete l’evento
- `keydown` avviene **prima di inserire il carattere nella textarea**    

---

### `beforeinput`

```js
textarea.addEventListener("beforeinput", (e) => {
    logMessage(`Key "${e.data}" about to be input [event: beforeinput]`);
});
```

- Evento più recente, introdotto negli standard moderni
- Si attiva **subito prima che il contenuto della textarea cambi**
- `e.data` → contiene il carattere che sta per essere inserito    

---

### `input`

```js
textarea.addEventListener("input", (e) => {
    logMessage(`Key "${e.data}" input [event: input]`);
});
```

- Si attiva **dopo che il contenuto della textarea è cambiato**
- Serve per gestire modifiche del testo, sia da tastiera che da copia-incolla
- `input` è **più generale di `keydown`**, perché cattura **qualsiasi cambiamento**

---

### `keyup`

```js
textarea.addEventListener("keyup", (e) => {
    logMessage(`Key "${e.key}" released [event: keyup]`);
});
```

- Si attiva **quando il tasto viene rilasciato**
- `keyup` completa la sequenza `keydown → input → keyup`

---

## Reset dei log

```js
btnReset.addEventListener("click", (e) => {
    let child = consoleLog.firstChild;
    while (child) {
        consoleLog.removeChild(child);
        child = consoleLog.firstChild;
    }
    textarea.value = "";
});
```

- Ciclo che **rimuove tutti i figli del `pre`**, cancellando il log
- Resetta anche `textarea.value`    
- Mostra come manipolare il DOM in modo dinamico con eventi click

---

## Sequenza teorica degli eventi della tastiera

1. `keydown` → tasto premuto
2. `beforeinput` → il carattere sta per essere inserito
3. `input` → il carattere è stato inserito
4. `keyup` → tasto rilasciato    

💡 Nota:
- Se premi e tieni premuto un tasto, `keydown` si ripete, ma `keyup` avviene solo alla fine.
- `beforeinput` e `input` si attivano **per ogni modifica reale al contenuto**.

---

### Riassunto dei concetti chiave

| Evento            | Quando succede                        | Proprietà principali                                     |
| ----------------- | ------------------------------------- | -------------------------------------------------------- |
| `keydown`         | Tasto premuto                         | `e.key`, `e.repeat`                                      |
| `beforeinput`     | Subito prima di cambiare il contenuto | `e.data`                                                 |
| `input`           | Dopo che il contenuto cambia          | `e.data`                                                 |
| `keyup`           | Tasto rilasciato                      | `e.key`                                                  |
| Sequenza completa | keydown → beforeinput → input → keyup | Permette di intercettare tutte le fasi della digitazione |
| DOM dinamico      | `removeChild`, `innerText`            | Usato per aggiornare log e resettare contenuti           |



## Esempi Pratici

### Controlli Videogioco

```js
const keys = {
    ArrowUp: false,
    ArrowDown: false,
    ArrowLeft: false,
    ArrowRight: false,
    Space: false
};

window.addEventListener('keydown', (e) => {
    if (e.key in keys) {
        keys[e.key] = true;
        e.preventDefault(); // evita scroll pagina
    }
});

window.addEventListener('keyup', (e) => {
    if (e.key in keys) {
        keys[e.key] = false;
    }
});

// Nel game loop
function update() {
    if (keys.ArrowUp) player.y -= player.speed;
    if (keys.ArrowDown) player.y += player.speed;
    if (keys.ArrowLeft) player.x -= player.speed;
    if (keys.ArrowRight) player.x += player.speed;
    if (keys.Space) player.shoot();
}
```

### Shortcuts

```js
document.addEventListener('keydown', (e) => {
    // Ctrl+S per salvare
    if (e.ctrlKey && e.key === 's') {
        e.preventDefault();
        saveDocument();
    }
    
    // Ctrl+Shift+P per command palette
    if (e.ctrlKey && e.shiftKey && e.key === 'P') {
        e.preventDefault();
        openCommandPalette();
    }
    
    // Escape per chiudere modal
    if (e.key === 'Escape') {
        closeModal();
    }
});
```

### Input con Validazione

```js
const numericInput = document.getElementById('numeric');

numericInput.addEventListener('beforeinput', (e) => {
    // Permetti solo numeri
    if (e.data && !/^\d$/.test(e.data)) {
        e.preventDefault();
    }
});

// Alternativa con keydown
numericInput.addEventListener('keydown', (e) => {
    // Permetti: backspace, delete, tab, escape, enter
    const allowedKeys = ['Backspace', 'Delete', 'Tab', 'Escape', 'Enter'];
    
    // Permetti: Ctrl+A, Ctrl+C, Ctrl+V, etc
    if (e.ctrlKey || e.metaKey) return;
    
    // Permetti: frecce
    if (e.key.startsWith('Arrow')) return;
    
    // Permetti: tasti speciali
    if (allowedKeys.includes(e.key)) return;
    
    // Blocca se non è un numero
    if (!/^\d$/.test(e.key)) {
        e.preventDefault();
    }
});
```

### Logger Completo

```js
const textarea = document.getElementById('test-target');
const log = document.getElementById('log');

function addLog(message, type = 'info') {
    const entry = document.createElement('div');
    entry.className = `log-entry log-${type}`;
    entry.textContent = `[${new Date().toLocaleTimeString()}] ${message}`;
    log.appendChild(entry);
    log.scrollTop = log.scrollHeight;
}

textarea.addEventListener('keydown', (e) => {
    const msg = e.repeat 
        ? `Key "${e.key}" REPEATING`
        : `Key "${e.key}" PRESSED`;
    addLog(msg, 'keydown');
});

textarea.addEventListener('beforeinput', (e) => {
    addLog(`About to input: "${e.data}"`, 'beforeinput');
});

textarea.addEventListener('input', (e) => {
    addLog(`Input: "${e.data}" (value: "${e.target.value}")`, 'input');
});

textarea.addEventListener('keyup', (e) => {
    addLog(`Key "${e.key}" RELEASED`, 'keyup');
});
```