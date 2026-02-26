```html
<body>
	<h1>es00 get</h1>
	<p>Un esempio di richiesta GET ad un sito di test.</p>
	<p id="post"></p>
</body>
```
```js
fetch("http://localhost:3000/posts/1").then(
    response => response.json()
).then((data) => {
    let post = document.getElementById("post");
    post.innerHTML = `post n.${data.id}: title: ${data.title}, views: ${data.views}`;            
});
```
```json
{
	"posts": [
		{ "id": "1", "title": "first", "views": 100 },
		{ "id": "2", "title": "second", "views": 200 },
		{ "id": "3", "title": "third", "views": 300 }
	]
}
```
## Obiettivo dell’esercizio

Qui non leggiamo più un file statico (`.txt`), ma facciamo una **vera richiesta HTTP GET a una API** che ritorna **JSON**.

Stai simulando:
- un backend
- una REST API
- una risposta strutturata (oggetti, array)

Con **json-server** fai finta di avere un server vero, ma tutto “in casa”.

---

## Ambiente: perché Live Server + json-server

Hai **due server diversi**:
1. **Live Server**
    - serve l’HTML + JS
    - es: `http://127.0.0.1:5500`
2. **json-server**
    - espone API REST
    - es: `http://localhost:3000/posts/1`

Questo è importante perché:
- stai facendo **una richiesta HTTP reale**
- `fetch` lavora esattamente come lavorerebbe in produzione
(qui non hai problemi CORS perché json-server li abilita di default)

---

## Il database JSON (`db.json`)

```json
{
	"posts": [
		{ "id": "1", "title": "first", "views": 100 },
		{ "id": "2", "title": "second", "views": 200 },
		{ "id": "3", "title": "third", "views": 300 }
	]
}
```

json-server interpreta questo file così:
- `/posts` → **array di post**
- `/posts/1` → **singolo post**

Quindi:

```txt
GET /posts     → [{...}, {...}, {...}]
GET /posts/1   → { id, title, views }
```

Questo collega direttamente alla frase che hai scritto:
> `Response.json()` ritorna o un singolo oggetto o un array di oggetti.

---

## HTML

```html
<p id="post"></p>
```

Di nuovo:
- il DOM è già pronto
- JS deve solo **inserire i dati ricevuti**

---

## La fetch GET

```js
fetch("http://localhost:3000/posts/1")
```

Qui succede:
- richiesta HTTP GET
- verso una API REST
- ritorna una **Promise**

---

## `Response.json()`

```js
.then(response => response.json())
```

Secondo MDN:
- `response.json()`:
    - legge il body
    - fa `JSON.parse()` automaticamente
    - ritorna **una Promise**

Il risultato (`data`) sarà:

```js
{
  id: "1",
  title: "first",
  views: 100
}
```

Se avessi fatto:

```js
fetch("http://localhost:3000/posts")
```

`data` sarebbe stato:

```js
[
  { id: "1", title: "first", views: 100 },
  { id: "2", title: "second", views: 200 },
  { id: "3", title: "third", views: 300 }
]
```

Stessa funzione, **tipo diverso di dato**.

---

## Uso dei dati nel DOM

```js
.then((data) => {
    let post = document.getElementById("post");
    post.innerHTML = 
      `post n.${data.id}: title: ${data.title}, views: ${data.views}`;
});
```

Qui:
- `data` è un **oggetto JS**
- accedi alle proprietà con `data.id`, `data.title`, `data.views`
- usi template string → moderno e leggibile

Questo è esattamente ciò che si fa in:
- SPA
- dashboard
- frontend che parla con backend

---

## Flusso completo

1. pagina caricata da Live Server
2. JS esegue `fetch`
3. browser manda richiesta GET
4. json-server risponde con JSON
5. `response.json()` converte il body
6. Promise risolta → `data`
7. DOM aggiornato

Tutto **asincrono**, nessun reload.

---

## Versione async/await (equivalente)

```js
async function loadPost() {
    const response = await fetch("http://localhost:3000/posts/1");
    const data = await response.json();

    document.getElementById("post").innerHTML =
      `post n.${data.id}: title: ${data.title}, views: ${data.views}`;
}

loadPost();
```

Stesso identico comportamento, solo più leggibile.

---

## Riassunto dei concetti chiave

- `fetch` → Promise
- `response.json()` → parsing automatico
- API REST → risorse (`/posts/1`)
- JSON → formato standard client-server
- DOM aggiornato dopo risoluzione Promise