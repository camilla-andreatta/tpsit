Normalmente, quando pensi a un evento, immagini:
> “clicco un bottone → metto il listener sul bottone”

Funziona, ma **non scala**.  
Ed è qui che entra in gioco **event delegation**.

---

## Event Delegation: l’idea di base

**Event delegation** significa:

> Invece di ascoltare l’evento su ogni elemento figlio, ascolti l’evento su **un solo elemento padre** e poi capisci **chi ha generato davvero l’evento** usando `event.target`.

Questo funziona perché:
- gli eventi **fanno bubbling**    
- quindi arrivano sempre al contenitore padre    
Se non esistesse il bubbling, la delegation **non sarebbe possibile**.

---

## Perché NON mettere tanti listener

```js
buttons.forEach(btn => {
    btn.addEventListener('click', () => {
        console.log(btn.textContent);
    });
});
```

Qui stai dicendo:
- un listener per ogni bottone
- se i bottoni diventano 1000 → 1000 listener
- se aggiungi bottoni dinamicamente → devi riaggiungere listener

❌ inefficiente
❌ difficile da mantenere
❌ poco elegante

---

## La soluzione: un solo listener

```js
container.addEventListener('click', (e) => {
    if (e.target.tagName === 'BUTTON') {
        console.log(e.target.textContent);
    }
});
```

Qui succede una cosa fondamentale:
- il `click` nasce sul bottone
- **fa bubbling**
- arriva al `container`
- tu intercetti l’evento
- guardi `e.target` per capire **chi è stato cliccato**

📌 `e.target` = elemento **reale** che ha generato l’evento

---

## Perché `tagName` funziona

```js
e.target.tagName === 'BUTTON'
```

Serve a:
- filtrare i click
- evitare di reagire a click “inutili” sul contenitore

Nota teorica:
- `tagName` è sempre MAIUSCOLO
- è una proprietà del DOM, non di JS “puro”

---

## Delegation robusta: `closest()`

```html
<ul id="list">
	<li data-id="1">
		<span class="name">Item 1</span>
		<button class="delete">Delete</button>
	</li>
</ul>
```
```js
const list = document.getElementById('list');

list.addEventListener('click', (e) => {
    // Trova l'elemento <li> più vicino
    const listItem = e.target.closest('li');
    
    if (!listItem) return; // Click fuori da un <li>
    
    // Click su delete button
    if (e.target.closest('.delete')) {
        const id = listItem.dataset.id;
        deleteItem(id);
        listItem.remove();
    }
    
    // Click sul nome
    if (e.target.closest('.name')) {
        const id = listItem.dataset.id;
        editItem(id);
    }
});
```

Nella pratica reale, **l’utente non clicca sempre l’elemento giusto**.

Esempio:
- clicca su un’icona
- clicca su uno `span`
- clicca su un testo dentro un bottone

Qui `e.target` **non è il bottone**, ma un figlio.

---

### Perché `closest()` è fondamentale

```js
const listItem = e.target.closest('li');
```

Significa:
> “Parti dall’elemento cliccato e risali il DOM finché trovi un `<li>`”.
> Se non lo trovi → `null`.

Questo rende la delegation:
- più sicura    
- più realistica
- adatta a HTML complessi

---

### Analisi dell’esempio `<ul>`

```js
list.addEventListener('click', (e) => {
    const listItem = e.target.closest('li');
    
    if (!listItem) return;
```

Qui stai facendo una **guardia logica**:
- se il click non è avvenuto dentro un `<li>`
- esci subito

---

### Click su delete

```js
if (e.target.closest('.delete')) {
    const id = listItem.dataset.id;
    deleteItem(id);
    listItem.remove();
}
```

Qui succedono 3 cose teoricamente importanti:
1. riconosci **che tipo di azione** è
2. usi `data-id` per collegare DOM e dati
3. modifichi il DOM **solo nel punto giusto**

---

## Data attributes come “API del DOM”

```html
<button data-action="save">Save</button>
```

`data-*` serve a:
- descrivere il comportamento
- separare HTML e JS    
- evitare `if` infiniti    

In JS:

```js
const action = button.dataset.action;
```

📌 `dataset`:
- legge tutti i `data-*`
- converte in camelCase    
- restituisce stringhe    

---

## Pattern avanzato: mappa azioni → funzioni

```html
<div id="toolbar" data-delegate="toolbar">
	<button data-action="save">Save</button>
	<button data-action="load">Load</button>
	<button data-action="delete">Delete</button>
</div>
```
```js
const toolbar = document.getElementById('toolbar');

// Mappa azioni → funzioni
const actions = {
    save: () => console.log('Saving...'),
    load: () => console.log('Loading...'),
    delete: () => console.log('Deleting...')
};

toolbar.addEventListener('click', (e) => {
    const button = e.target.closest('[data-action]');
    if (!button) return;
    
    const action = button.dataset.action;
    if (actions[action]) {
        actions[action]();
    }
});
```

Questo pattern è importante perché:
- elimina `switch` e `if`    
- rende il codice estendibile    
- è tipico di framework moderni

Delegation + data attributes = **architettura**.

---

## Todo list dinamica: perché la delegation è obbligatoria

```js
const todoList = document.getElementById('todo-list');
const addButton = document.getElementById('add-todo');
const input = document.getElementById('todo-input');

let todoId = 0;

// Delega su lista
todoList.addEventListener('click', (e) => {
    const item = e.target.closest('.todo-item');
    if (!item) return;
    
    // Toggle complete
    if (e.target.closest('.checkbox')) {
        item.classList.toggle('completed');
    }
    
    // Delete
    if (e.target.closest('.delete-btn')) {
        item.remove();
    }
    
    // Edit
    if (e.target.closest('.edit-btn')) {
        const textSpan = item.querySelector('.todo-text');
        const newText = prompt('Edit:', textSpan.textContent);
        if (newText) textSpan.textContent = newText;
    }
});

// Aggiungi todo
addButton.addEventListener('click', () => {
    const text = input.value.trim();
    if (!text) return;
    
    const html = `
        <li class="todo-item" data-id="${todoId++}">
            <input type="checkbox" class="checkbox">
            <span class="todo-text">${text}</span>
            <button class="edit-btn">Edit</button>
            <button class="delete-btn">Delete</button>
        </li>
    `;
    
    todoList.insertAdjacentHTML('beforeend', html);
    input.value = '';
});
```

Qui gli elementi:
- **non esistono all’inizio**
- vengono creati con `insertAdjacentHTML`

Se mettessi listener sui singoli item:  
❌ non funzionerebbero sui nuovi elementi

Con delegation invece:
- il listener è sempre lì    
- intercetta anche gli elementi futuri

Questo è uno dei motivi principali per cui la delegation esiste.

---

## `event.target` vs `event.currentTarget`

```html
<div id="outer">
	<button id="btn">
		<span id="icon">🔥</span> Click
	</button>
</div>
```
```js
outer.addEventListener('click', (e) => {
    console.log('target:', e.target.id);           // 'icon', 'btn', o 'outer'
    console.log('currentTarget:', e.currentTarget.id); // sempre 'outer'
});

// Click su span:
// target: "icon" (elemento effettivamente cliccato)
// currentTarget: "outer" (elemento con il listener)
```

Differenza concettuale:
- `target` → **chi ha generato l’evento**
- `currentTarget` → **chi sta gestendo l’evento**

Nel caso della delegation:
- `currentTarget` è sempre il contenitore
- `target` cambia a seconda di dove clicchi

### Click su `<span>`

```txt
target: span
currentTarget: outer
```

📌 Questa differenza è la base per capire **perché la delegation funziona**.

---

## Riassunto dei concetti chiave

- Event delegation sfrutta il bubbling
- Un solo listener su un contenitore
- `event.target` indica l’elemento cliccato
- `event.currentTarget` indica chi ascolta
- `closest()` rende la delegation robusta
- `data-*` collega HTML e comportamento
- È indispensabile per elementi dinamici
- È una tecnica fondamentale del DOM moderno