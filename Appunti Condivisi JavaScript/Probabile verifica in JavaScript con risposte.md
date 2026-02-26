#### **1. Inizializzazione di una costante e interpretazione**

##### **Domanda:**
Il codice
```js
console.log(`maria name: ${maria["name"]}`);
console.log(`maria age: ${maria["age"]}`);
```

una volta interpretato mostra:

```
maria name: maria
maria age: 23
```

Scrivere il codice per inizializzare la costante `maria` e motivare l'esito dell'interpretazione JS.

**Soluzione:** Il codice per inizializzare la costante è il seguente:

```
const maria = {
    name: "maria",
    age: 23
};
```

**Motivazione:**

- **Inizializzazione:** È stato creato un **oggetto** JavaScript, ovvero una struttura dati che raggruppa valori correlati tramite coppie **chiave-valore**. In questo caso, le chiavi sono "name" e "age".
- **Accesso alle proprietà:** Il codice utilizza la **bracket notation** (`maria["name"]`) per accedere ai valori associati alle chiavi. Questo metodo è equivalente alla dot notation (`maria.name`).
- **Template Literals:** L'output è generato tramite **stringhe template** (delimitate da backtick `), che permettono di inserire variabili o espressioni direttamente nel testo usando la sintassi `${}`.

---

#### **2. Funzione costruttrice e Prototipi**

**Domanda:** Creando oggetti con la funzione `Person()` si dà alla sua istanza `jane` la possibilità di usare il metodo `getName()` che permette di recuperare il valore della _property_ `name` cioè "Jane". Si scriva per esteso la funzione `Person()`. Cosa possiamo dire della _property_ `prototype` di `Person` in relazione all'oggetto `jane`?

**Soluzione:** La funzione costruttrice può essere scritta in questo modo (approccio standard pre-ES6):

```
function Person(name) {
    this.name = name;
}

Person.prototype.getName = function() {
    return this.name;
};

const jane = new Person("Jane");
```

**Relazione tra `prototype` e `jane`:** In JavaScript, ogni funzione ha una proprietà speciale chiamata `prototype`, che non è il prototipo della funzione stessa, ma l'oggetto che verrà assegnato come **prototipo a tutte le istanze** create con l'operatore `new`. Pertanto, l'oggetto `jane` eredita il metodo `getName()` perché il suo prototipo interno punta direttamente a `Person.prototype`.

---

#### **3. Funzioni che ritornano funzioni e Closure**

**Domanda:** La funzione `tabellina` ha un parametro intero, `n`, e ritorna due funzioni: `start()` che permette di far ripartire la tabellina da `n` e mostra a terminale `n`, e `next()` che incrementa il valore della tabellina mostrandolo a terminale. Quale particolarità semantica/sintattica si usa del linguaggio JS?

**Soluzione:** La particolarità semantica utilizzata è la **closure** (o chiusura). Una closure si verifica quando una funzione viene definita all'interno di un'altra funzione e "ricorda" lo **scope** (contesto) in cui è nata. Anche dopo che la funzione esterna `tabellina` ha terminato l'esecuzione, le funzioni `start` e `next` mantengono l'accesso alla variabile locale `n`, permettendo di leggerla e modificarla nel tempo.

---

#### **4. Tipi di dato in JavaScript**

**Domanda:** Commentare la seguente affermazione: in JS i tipi di dato non esistono in quanto in fase di dichiarazione di costante o variabile non esplicitiamo il tipo di dato.

**Soluzione:** L'affermazione è **errata**. In JavaScript i tipi di dato esistono (come stringhe, numeri, booleani, oggetti, ecc.), ma JavaScript è un linguaggio **dinamico**. Ciò significa che il tipo non è associato alla variabile o alla costante in fase di dichiarazione, ma è associato al **valore** che la variabile contiene in un dato momento.

---

#### **5. Variabili Globali: Java vs JavaScript**

**Domanda:** Quale differenza esiste fra Java e JS per quanto riguarda la possibilità di dichiarare variabili nel contesto globale?

**Soluzione:**

- In **Java**, ogni variabile deve necessariamente appartenere a una classe; non esiste il concetto di variabile "globale" nel senso puro del termine al di fuori di una struttura di classe.
- In **JavaScript**, una variabile dichiarata fuori da qualsiasi funzione o blocco appartiene allo **scope globale** ed è visibile e utilizzabile ovunque nel codice. Inoltre, in JS esiste un **oggetto globale** (come `window` nei browser) a cui le variabili globali possono essere collegate.

---

#### **6. Proprietà enumerabili di un Array**

**Domanda:**

```
const lista =
```

Si elenchino le _property_ enumerabili di `lista`. Quale funzione ci permette di visualizzare la lista di tali _property_?

**Soluzione:** Le proprietà enumerabili di `lista` sono i suoi **indici**, ovvero: `"0"`, `"1"`, `"2"`. La proprietà `length` non compare in questo elenco perché non è considerata _enumerable_. La funzione che permette di visualizzare la lista di tali proprietà è **`Object.keys(lista)`**.

---

#### **7. Overloading in JavaScript**

**Domanda:** In JS l'_overloading_ non è presente in quanto... (completare la frase).

**Soluzione:** In JS l'overloading non è presente in quanto **le funzioni sono oggetti e, se si definisce una nuova funzione con lo stesso nome di una esistente, la seconda sovrascrive la prima**. Inoltre, JavaScript gestisce nativamente liste di parametri variabili, rendendo superfluo il meccanismo classico di overloading presente in linguaggi come Java.

---

#### **8. Analisi comando `curl`**

**Domanda:** Cosa fa la seguente istruzione?

```
curl -X PATCH -H "Content-Type: application/json" -d '{"prop": 123}' http://myresttype/objects
```

**Soluzione:** L'istruzione esegue una richiesta HTTP al server situato all'URL specificato:

- **`-X PATCH`**: Indica che si vuole effettuare un **aggiornamento parziale** di una risorsa esistente sul server.
- **`-H "Content-Type: application/json"`**: Imposta un'intestazione (header) per comunicare al server che i dati inviati sono in formato **JSON**.
- **`-d '{"prop": 123}'`**: Rappresenta il **payload** (body), ovvero il dato effettivo (l'oggetto JSON) da inviare per la modifica.
- **`http://myresttype/objects`**: È l'endpoint della **REST API** a cui è indirizzata la richiesta.

---

_Nota: Questa risposta è basata esclusivamente sulle fonti fornite._