Se una funzione viene dichiarata sotto e richiamata qualcosa si può fare
js sposta all'inizio dello script tutte le dichiarazione anche se vengono fatte dopo

L’**hoisting** è il comportamento per cui JavaScript, durante la fase di compilazione, **sposta le dichiarazioni in cima al loro scope** (non le assegnazioni!).

let z= 12; //prima di questa riga so che c'è un nome y perchè è come se ci fosse una dichiarazione sopra
var y = 13;  //prima di questa riga del nome y non sappiamo nulla

questo è uguale a scrivere
```js
var y;
let z = 12;
y = 13;
```

Attenzione:  
👉 vengono “alzate” le **dichiarazioni**, non i valori.

---

# Hoisting con `var`

Con `var`:

- La **dichiarazione** viene spostata in alto
    
- L’**assegnazione** rimane dove si trova
    

Esempio:

`console.log(y); var y = 13;`

JavaScript lo interpreta come:
```js
var y;          // dichiarazione hoisted console.log(y); // undefined 
y = 13;         // assegnazione`
```
Quindi:

- Prima della riga `var y = 13`
    
- Il nome `y` esiste già
    
- Ma vale `undefined`
    

---

# 🔹 2️⃣ Hoisting con `let`

Con `let` è diverso.

La dichiarazione viene comunque registrata all’inizio dello scope,  
MA **non può essere usata prima della riga in cui è dichiarata**.

Esempio:

```js
console.log(z); let z = 12;```
```

❌ Questo genera errore:

`ReferenceError: Cannot access 'z' before initialization`

Perché?

Con `let` esiste la **Temporal Dead Zone (TDZ)**:

- La variabile è conosciuta dal motore JavaScript
    
- Ma non è ancora inizializzata
    
- Non può essere usata prima della sua dichiarazione
    

---

# 🔹 Correzione dei tuoi appunti

Tu hai scritto:

`let z = 12;  var y = 13;`

Hai detto:

> prima di questa riga so che c'è un nome y  
> prima di questa riga del nome y non sappiamo nulla

In realtà è il contrario:

- Con `var y`, il nome **esiste già** prima della riga (vale `undefined`)
    
- Con `let z`, il nome esiste ma è in **Temporal Dead Zone** e non è accessibile
    

---

# 🔹 Questo codice:

`let z = 12; var y = 13;`

È equivalente a:

`var y;        // hoisting let z;        // registrata ma in TDZ  z = 12; y = 13;`

⚠️ Però attenzione:  
`let` non è veramente “spostata” come `var`.  
Viene solo registrata nello scope, ma rimane bloccata fino alla riga di dichiarazione.

---

# 🔹 3️⃣ Hoisting delle funzioni

Le **function declaration** vengono completamente hoistate (nome + corpo).

Esempio:

`saluta();  function saluta() {     console.log("Ciao"); }`

Funziona perché è come se fosse:

`function saluta() {     console.log("Ciao"); }  saluta();`

---

# 🔹 4️⃣ Function expression (NON viene hoistata completamente)

`saluta(); // ❌ errore  var saluta = function() {     console.log("Ciao"); };`

Diventa:

`var saluta;     // hoisted saluta();       // undefined is not a function ❌ saluta = function() {     console.log("Ciao"); };`
