[Esercizio 1](https://gitlab.com/divino.marchese/zuccante_src/-/tree/master/js/fetch/es01_read_file):
```html
<body>
    <h1>es01 read file</h1>
    <p>ecco cosa leggiamo nel file:</p>
	<p id="here"></p>
</body>
```
```js
fetch('/ciao.txt')
    .then(response => response.text())
    .then(
        (data) => {
            console.log(data);
            let here = document.getElementById("here");
            here.innerHTML = data;
        });
```
```txt
ciao
```
### Obiettivo dell’esercizio

L’obiettivo di `es001 read file` è **leggere il contenuto di un file di testo (`ciao.txt`) usando JavaScript** e mostrarlo dentro la pagina HTML, **senza ricaricare la pagina**.

Questo introduce tre concetti fondamentali:
- uso di `fetch()`
- gestione di Promise 
- lettura del body di una risposta HTTP

---

### Perché serve un server (Live Server)

`fetch()` **NON può leggere file locali** con `file://` per motivi di sicurezza (CORS).

Se aprissi l’HTML con doppio click:

```
file:///C:/...
```

la richiesta:

```js
fetch('/ciao.txt')
```

verrebbe **bloccata dal browser**.

Con **Live Server** invece:

```
http://127.0.0.1:5500/
```

il browser considera la pagina come servita da un server HTTP →  
le richieste `fetch()` funzionano correttamente.

---

### Struttura HTML

```html
<h1>es01 read file</h1>
<p>ecco cosa leggiamo nel file:</p>
<p id="here"></p>
```

Qui:
- `#here` è il **punto di inserimento**
- JavaScript non crea elementi nuovi, ma **modifica il DOM esistente**

Il file JS è caricato **alla fine del body**, quindi:
- il DOM è già pronto
- non serve `DOMContentLoaded`

---

### La chiamata fetch

```js
fetch('/ciao.txt')
```

Cosa fa:
- invia una **richiesta HTTP GET**
- ritorna una **Promise**
- la Promise si risolve con un oggetto `Response` 

Importante:
- `fetch` **non legge il contenuto subito**
- ottieni solo i metadati della risposta (status, headers, ecc.)

---

### La Response

```js
.then(response => response.text())
```

Qui succede una cosa cruciale:
- `response` è un oggetto `Response`
- `response.text()`:
    - legge il **body della risposta**
    - lo converte in **stringa**
    - ritorna **un’altra Promise**

Nota da MDN:
> il body può essere letto **una sola volta**

Alternative che potresti usare:
- `response.json()`
- `response.blob()`
- `response.arrayBuffer()`

---

### Uso del dato letto

```js
.then((data) => {
    console.log(data);
    let here = document.getElementById("here");
    here.innerHTML = data;
});
```

Qui:
- `data` è **il contenuto testuale di ciao.txt**
- lo stampi in console (debug)
- lo inserisci nel DOM con `innerHTML`

Nota:
- se il file contiene solo testo semplice → ok
- se contenesse HTML → verrebbe interpretato (attenzione sicurezza)

---

### Flusso completo (importantissimo)
1. browser carica HTML
2. esegue `app.js`
3. `fetch()` invia richiesta GET
4. il server (Live Server) risponde con `ciao.txt`
5. `response.text()` legge il body
6. il testo viene inserito nella pagina
Tutto questo **senza bloccare il browser**.

---

### Versione con async/await (equivalente)

Spesso i prof chiedono “riscrivilo con async/await”:

```js
async function loadFile() {
    const response = await fetch('/ciao.txt');
    const data = await response.text();
    
    document.getElementById("here").innerHTML = data;
}

loadFile();
```

Concettualmente è **identico**, solo più leggibile.