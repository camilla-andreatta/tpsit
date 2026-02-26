# Canvas - Setup e Rendering

---
## Setup Base

```js
// 1. Ottenere riferimento al canvas
const canvas = document.getElementById('myCanvas');

// 2. Impostare dimensioni
canvas.width = 800;
canvas.height = 600;

// 3. Ottenere il context (2d o webgl)
const ctx = canvas.getContext('2d');

// Nota: le dimensioni CSS possono essere diverse da width/height
// Per evitare distorsioni, assicurati che corrispondano:
canvas.style.width = canvas.width + 'px';
canvas.style.height = canvas.height + 'px';
```

## HiDPI / Retina Display

```js
function setupCanvas(canvas) {
    const dpr = window.devicePixelRatio || 1;
    const rect = canvas.getBoundingClientRect();
    
    // Dimensioni CSS
    canvas.style.width = rect.width + 'px';
    canvas.style.height = rect.height + 'px';
    
    // Dimensioni reali del canvas scalate per DPR
    canvas.width = rect.width * dpr;
    canvas.height = rect.height * dpr;
    
    // Scala il context per compensare
    const ctx = canvas.getContext('2d');
    ctx.scale(dpr, dpr);
    
    return ctx;
}
```

## Metodi di Disegno Base

### Forme

```js
// Rettangoli
ctx.fillStyle = '#ff0000';
ctx.fillRect(x, y, width, height);        // pieno
ctx.strokeStyle = '#00ff00';
ctx.strokeRect(x, y, width, height);      // bordo
ctx.clearRect(x, y, width, height);       // cancella area

// Percorsi (paths)
ctx.beginPath();
ctx.moveTo(x1, y1);    // sposta "penna" senza disegnare
ctx.lineTo(x2, y2);    // linea
ctx.arc(x, y, radius, startAngle, endAngle);
ctx.quadraticCurveTo(cpx, cpy, x, y);
ctx.bezierCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
ctx.closePath();       // chiude il percorso

// Riempimento o contorno
ctx.fill();
ctx.stroke();
```

### Cerchio Completo

```js
function drawCircle(x, y, radius, color) {
    ctx.beginPath();
    ctx.arc(x, y, radius, 0, Math.PI * 2);
    ctx.fillStyle = color;
    ctx.fill();
    ctx.closePath();
}
```

### Testo

```js
ctx.font = '20px Arial';
ctx.fillStyle = '#000000';
ctx.textAlign = 'center';      // left, center, right
ctx.textBaseline = 'middle';   // top, middle, bottom
ctx.fillText('Ciao!', x, y);
ctx.strokeText('Outline', x, y);

// Misurare testo
const metrics = ctx.measureText('Ciao!');
console.log(metrics.width);
```

## Stati e Trasformazioni

```js
// Salvare e ripristinare stato
ctx.save();    // salva stato corrente (colori, trasformazioni, etc)
ctx.fillStyle = 'red';
ctx.translate(100, 100);
ctx.rotate(Math.PI / 4);
ctx.restore(); // ripristina stato precedente

// Trasformazioni
ctx.translate(x, y);           // sposta origine
ctx.rotate(angle);             // ruota (radianti)
ctx.scale(sx, sy);             // scala
ctx.setTransform(a, b, c, d, e, f); // matrice completa
ctx.resetTransform();          // reset a identità
```

---
