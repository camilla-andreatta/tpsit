## Pattern Observer: idea teorica

Il **Pattern Observer** serve a risolvere questo problema:
> Come faccio a far reagire più parti del programma a un evento **senza** che queste parti si conoscano direttamente?

La risposta è: **notifica indiretta**.

Un oggetto:
- **emette** un evento
- non sa **chi** lo riceverà
- non sa **cosa** succederà dopo    

Altri oggetti:
- si **iscrivono**
- reagiscono quando vogliono    
- possono smettere di ascoltare

Questo riduce l’**accoppiamento**.

---

## I tre ruoli

### 1️. Subject
- è la sorgente
- decide _quando_ emettere un evento
- **non chiama direttamente nessuno**

### 2️. Observer
- ascolta il subject
- reagisce alla notifica
- può essere uno o molti

### 3️. Event
- è il messaggio
- contiene i dati    
- viaggia dal subject agli observer

📌 Questo schema è **indipendente dal linguaggio**: è teoria pura.

---

## 🔹 CustomEvent nel DOM: Observer “già pronto”

Il DOM implementa **nativamente** questo pattern.

Quando usi:

- `dispatchEvent`
    
- `addEventListener`
    
- `CustomEvent`
    

stai applicando l’Observer **senza scriverlo da zero**.

---

### Lettura concettuale del codice

```js
const myEvent = new CustomEvent('userLogin', {
    detail: {
        username: 'mario.rossi',
        timestamp: Date.now()
    },
    bubbles: true,
    cancelable: true
});
```

Qui stai creando:

- un **evento semantico** (`userLogin`)
    
- con un **payload** (`detail`)
    
- che può:
    
    - propagarsi (`bubbles`)
        
    - essere annullato (`cancelable`)
        

📌 `detail` è fondamentale:  
è **l’unico posto standard** dove passare dati custom.

---

```js
element.dispatchEvent(myEvent);
```

Concettualmente:

> “È successo qualcosa, chi è interessato reagisca.”

---

```js
element.addEventListener('userLogin', (e) => { ... });
```

Concettualmente:

> “Se succede, io reagisco.”

Il subject **non sa** chi ascolta.  
Questo è il cuore del pattern.

---

## 🔹 Notification System: Observer applicato bene

Qui il pattern è **esplicito**.

`NotificationManager` è un **subject**.

- emette notifiche
    
- non conosce gli observer
    
- non cambia se ne aggiungi altri
    

---

### notify()

```js
notify(message, type)
```

- crea un evento
    
- lo emette
    
- **non fa altro**
    

Questo è design corretto:  
una responsabilità sola.

---

### subscribe / unsubscribe

```js
subscribe(handler)
unsubscribe(handler)
```

Qui vedi chiaramente:

- iscrizione
    
- disiscrizione
    

Questo è **Observer pattern classico**, versione DOM.

---

### Più observer, stesso evento

```js
notifier.subscribe(...)
notifier.subscribe(...)
notifier.subscribe(...)
```

Un solo evento → **più reazioni indipendenti**.

Questo è il vero vantaggio teorico:

- aggiungi funzionalità
    
- **senza modificare il subject**
    

---

## 🔹 Highlight con delay: evento come “segnale”

In questo esempio l’evento non rappresenta un input utente, ma:

> “Una certa azione è terminata”

```js
element.dispatchEvent(event);
```

Qui l’evento è un **segnale temporale**.

Chi ascolta:

- aggiunge bordo
    
- suona audio
    
- logga
    

La funzione `highlight` **non cambia mai**.  
Cambia solo chi ascolta.

📌 Questo è Observer usato bene.

---

## 🔹 Event Bus: Observer senza DOM

Qui il prof sta facendo un salto concettuale importante.

L’`EventBus`:

- **replica lo stesso pattern**
    
- ma senza browser
    
- senza DOM
    
- solo JavaScript
    

---

### Mappa mentale

|Metodo|Ruolo|
|---|---|
|`on`|subscribe|
|`off`|unsubscribe|
|`emit`|notify|

Il concetto è **identico** a `addEventListener` / `dispatchEvent`.

---

### Perché è importante (teoria)

Serve quando:

- lavori a moduli
    
- non vuoi dipendenze dirette
    
- vuoi comunicazione globale
    
- il DOM non c’entra
    

È un pattern architetturale, non un trucco.

---

## 🔹 Collegamento da esame

- CustomEvent → Observer nel DOM
    
- EventBus → Observer generico
    
- In entrambi:
    
    - disaccoppiamento
        
    - più listener
        
    - comunicazione indiretta
        

---

## 🔹 Riassunto dei concetti chiave

**Cosa il prof vuole che tu sappia**

- Observer = pattern di notifica
    
- Subject emette, observer ascoltano
    
- Nessun accoppiamento diretto
    
- `CustomEvent` è Observer nel DOM
    
- `detail` trasporta i dati
    
- `dispatchEvent` = notify
    
- `addEventListener` = subscribe
    
- EventBus replica lo stesso pattern senza DOM
    
- Pattern usato per codice scalabile e mantenibile
    

Se vuoi, prossimo passo possibile:

- confronto **callback vs observer** (domanda tipica)
    
- oppure “spiegamelo come se fossi interrogata” 😄