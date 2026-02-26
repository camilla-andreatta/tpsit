# Canvas - Sistema di Coordinate ed Eventi

---
## Sistema di Coordinate Canvas

```
(0,0) ────────────► X
  │
  │
  │
  ▼
  Y

// Origine in alto a sinistra!
// X cresce verso destra
// Y cresce verso il BASSO (diverso da matematica)
```

## Conversione Coordinate Mouse → Canvas

```js
function getCanvasCoordinates(canvas, event) {
    const rect = canvas.getBoundingClientRect();
    
    // Coordinate relative al canvas
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    
    // Se hai applicato scaling CSS, normalizza:
    const scaleX = canvas.width / rect.width;
    const scaleY = canvas.height / rect.height;
    
    return {
        x: x * scaleX,
        y: y * scaleY
    };
}

// Uso
canvas.addEventListener('mousemove', (e) => {
    const pos = getCanvasCoordinates(canvas, e);
    console.log(`Mouse a (${pos.x}, ${pos.y})`);
});
```

## Eventi Mouse su Canvas

```js
const mouse = { x: 0, y: 0, down: false };

canvas.addEventListener('mousedown', (e) => {
    const pos = getCanvasCoordinates(canvas, e);
    mouse.x = pos.x;
    mouse.y = pos.y;
    mouse.down = true;
    
    // Controlla se hai cliccato su un oggetto
    objects.forEach(obj => {
        if (isPointInCircle(pos.x, pos.y, obj)) {
            obj.onClick();
        }
    });
});

canvas.addEventListener('mousemove', (e) => {
    const pos = getCanvasCoordinates(canvas, e);
    mouse.x = pos.x;
    mouse.y = pos.y;
});

canvas.addEventListener('mouseup', () => {
    mouse.down = false;
});

// Collision detection punto-cerchio
function isPointInCircle(px, py, circle) {
    const dx = px - circle.x;
    const dy = py - circle.y;
    return dx * dx + dy * dy <= circle.radius * circle.radius;
}
```

## Esempio Interattivo: Disegno Libero

```js
const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
let isDrawing = false;
let lastX = 0;
let lastY = 0;

canvas.addEventListener('mousedown', (e) => {
    isDrawing = true;
    const pos = getCanvasCoordinates(canvas, e);
    [lastX, lastY] = [pos.x, pos.y];
});

canvas.addEventListener('mousemove', (e) => {
    if (!isDrawing) return;
    
    const pos = getCanvasCoordinates(canvas, e);
    
    ctx.beginPath();
    ctx.moveTo(lastX, lastY);
    ctx.lineTo(pos.x, pos.y);
    ctx.strokeStyle = '#000';
    ctx.lineWidth = 2;
    ctx.lineCap = 'round';
    ctx.stroke();
    
    [lastX, lastY] = [pos.x, pos.y];
});

canvas.addEventListener('mouseup', () => isDrawing = false);
canvas.addEventListener('mouseout', () => isDrawing = false);
```

---
