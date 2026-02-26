1-  
```js
const maria = {
      "name" : maria;
      "age" : 23
};
```

JS interpreta in quel modo il codice perche l'utilizzo della sintassi ${} in una stringa preannucia che quello che segue sarà sostituito da una variabile

2-
```js
function Person(name){
	this.name = name;
	this.getName = function(){
		return this.name
		}
}
```

Il prototipo dell'oggetto jane (creato utilizzando la sintassi new Person("Jane")) corrisponderà alla proprietà prototype della funzione Person

3-
Si in questo caso si utilizza la proprietà semantica del linguaggio JS closure: fenomeno che si verifica quando abbiamo una funzione che ritorna un'altra funzione e un utilizzo nello scope della funzione interna di una variabile dichiarata nello scope di quella esterna. La funziona interna ricorda lo scope in cui è stata creata, portandosi dietro il contesto, e finché c'è un riferimento allo scope interno la variabile continua ad essere visibile, nonostante la function esterna sia finita.
```js
function tabellina(){
  let n = 0;
  let contatore = 0;
  function start(){
    console.log(n*contatore);
    contatore += 1;
  }
  function next(){
    contatore = 0;
    n += 1;
    console.log(n);
  }
  return{
    start: start,
    next: next
  };
}
```

4- 
Le property enumerabili sono le proprietà con l'attributo enumerable : true, che sono visibili utilizzando il metodo Object.keys(lista) o iterando le propreità dell'oggetto con un for.
In questo caso sono "1" "2" "3"

5-
L'hoisting è la proprietà di js per cui le dichiarazioni delle variabili, ma NON delle assegnazioni vine portata all'inizio del codice e del loro scope
```js
console.log(x);
let x = 10;
```

questo codice scriverà a console un errore di tipo logico, poiche la dichiarazione è stata spostata all'inizio dello scope, ma non gli è stato assegnato un valore, assegnato invece dopo il console.log()

se al posto di let ci fosse stata var il risultato sarebbe stato undefined perche js dopo aver fatto l'hoisting di var, inizializza la variabile ad undefined

funziona anche se viene fatto con una funzione, ma non se la funzione è stata assegnata ad una variabile

6-
L'affermazione non è del tutto corretta in quanto in JS i tipi di dato esistono, anche se è un linguaggio debolmente tipizzato, poiché il tipo di dato viene autoassegnato ad una variabile quando gli si assegna un valore, quindi in runtime e non nel momento della dichiarazione.
Infatti possiamo assegnare tipi di dato diversi alla stessa variabile, cambiandoli in runtime.
I tipi in JS esistono e questo è dimostrabile usando la funzione typeof che ci ritornerà il tipo dei nostri oggetti.

7- 
La differenza tra Java e JS per quanto riguarda la dichiarazione di variabili globali è che, nel primo non esistono vere e proprie variabili globali, poichè una variabile non può essere dichiarata all'esterno di una classe; in js invece esistono vere e proprie variabili globali, poiché una variabile non deve essere dichiarata per forza all'interno di un blocco o di una funzione, quindi può essere legata effettivamente al contesto più esterno e globale.

8- 
In JS l'overloading non è presente in quanto le variabili non hanno un tipo assegnato se non in runtime (dinamicamente tipizzato), per cui non accetta due funzioni con lo stesso nome non riuscendo a distinguerle neanche attraverso i parametri, e nel momento della chiamata non saprebbe quale scegliere.

9- 
L'istruzione curl PATCH invia una richiesta al server per modificare parzialmente una sua risorsa: in questo caso tenta di modificare la proprietà "prop". Il problema è che nell'url non è presente l'indice della risorsa che deve andare a modificare, per questo la richiesta molto probabilmente genererà un errore, a meno che non sia stata gestita una modifica massiva per quanto riguarda il server