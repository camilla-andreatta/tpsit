1. **\[1+2 punti\]** Il codice
	```js
	console.log(`maria name: ${maria["name"]}`);
	console.log(`maria age: ${maria["age"]}`);
	```
	una volta interpretato mostra
	```js
	maria name: maria
	maria age: 23
	```
	Scrivere il codice per inizializzare la costante `maria` e motivare l'esito dell'interpretazione JS.


2. **\[2+2 punti\]** Creando oggetti con la funzione `Person()` si dà alla sua istanza `jane` la possibilità di usare il metodo `getName()` che permette di recuperare il valore della *property* `name` cioè \"Jane\". Si scriva per esteso la funzione `Person()`. Cosa possiamo dire delle *property* `prototype` di `Person` in relazione all'oggetto `jane`?


3. **\[3+3+2 punti\]** La funzione `tabellina` ha un parametro intero, `n`, e ritorna due funzioni: `start()` che permette di far ripartire la tabellina da `n` e mostra a terminale `n`, e `next()` che incrementa il valore della tabellina mostrandolo a terminale. Quale particolarità semantica/sintattica si usa del linguaggio JS?


4. **\[2 punti\]** Commentare la seguente affermazione: in JS i tipi di dato non esistono in quanto in fase di dichiarazione di costante o variabile non esplicitiamo il tipo di dato.


5. **\[3 punti\]** Quale differenza esiste fra Java e JS per quanto riguarda la possibilità di dichiarare variabili nel contesto globale?


6. **\[2 punti\]**
	```js
	const lista = [2, 4, 7]
	```
	Si elenchino le *property* enumerabili di `lista`. Quale funzione ci permette di visualizzare la lista di tali *property*?


7. **\[2 punti\]** In JS l'*overloading* non è presente in quanto $\ldots$ (completare la frase).


8. **\[1+1+1+1 punti\]** Cosa fa la seguente istruzione?
	```js
	curl -X PATCH -H "Content-Type: application/json" -d '{"prop": 123}' http://myresttype/objects
	```