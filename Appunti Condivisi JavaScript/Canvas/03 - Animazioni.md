# Canvas - Animazioni

---
## requestAnimationFrame vs setInterval

### ❌ setInterval (sconsigliato per animazioni)

```js
// Problemi:
// - Non sincronizzato col refresh rate dello schermo
// - Continua anche se tab non visibile (spreco risorse)
// - Timing impreciso

setInterval(() => {
    // animazione
}, 16.67); // ~60 FPS
```

### ✅ requestAnimationFrame (consigliato)

```js
function animate(timestamp) {
    // Disegna frame
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    
    // ... logica animazione
    
    // Richiedi prossimo frame
    requestAnimationFrame(animate);
}

// Avvia animazione
requestAnimationFrame(animate);
```

**Vantaggi**:

- Sincronizzato col refresh rate (60 FPS su maggior parte monitor)
- Si pausa automaticamente quando tab non visibile
- Callback riceve timestamp preciso

## Delta Time per Movimento Indipendente da FPS

```js
let lastTime = 0;

function animate(timestamp) {
    // Calcola delta time (tempo passato dall'ultimo frame)
    const deltaTime = (timestamp - lastTime) / 1000; // in secondi
    lastTime = timestamp;
    
    // Usa deltaTime per movimento consistente
    // velocity è in unità/secondo
    ball.x += ball.velocityX * deltaTime;
    ball.y += ball.velocityY * deltaTime;
    
    // Disegna
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawBall(ball);
    
    requestAnimationFrame(animate);
}

requestAnimationFrame(animate);
```

## Pattern Animazione Completo

```js
class Animation {
    constructor(canvas) {
        this.canvas = canvas;
        this.ctx = canvas.getContext('2d');
        this.running = false;
        this.lastTime = 0;
        this.entities = []; // oggetti da animare
    }
    
    start() {
        if (!this.running) {
            this.running = true;
            this.lastTime = performance.now();
            this.loop(this.lastTime);
        }
    }
    
    stop() {
        this.running = false;
    }
    
    loop(timestamp) {
        if (!this.running) return;
        
        const deltaTime = (timestamp - this.lastTime) / 1000;
        this.lastTime = timestamp;
        
        // Update
        this.update(deltaTime);
        
        // Clear
        this.clear();
        
        // Draw
        this.draw();
        
        // Next frame
        requestAnimationFrame((t) => this.loop(t));
    }
    
    update(dt) {
        this.entities.forEach(entity => entity.update(dt));
    }
    
    clear() {
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
    }
    
    draw() {
        this.entities.forEach(entity => entity.draw(this.ctx));
    }
}

// Uso
const anim = new Animation(canvas);
anim.entities.push(new Ball(100, 100));
anim.start();
```

## Esempio: Palla che Rimbalza (Migliorato)

```js
class BouncingBall {
    constructor(x, y, radius, color, velocity) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.vx = velocity.x;
        this.vy = velocity.y;
    }
    
    update(dt, canvasWidth, canvasHeight) {
        // Movimento
        this.x += this.vx * dt * 60; // 60 per mantenere velocità simile a 60fps
        this.y += this.vy * dt * 60;
        
        // Rimbalzo sui bordi
        if (this.x - this.radius < 0 || this.x + this.radius > canvasWidth) {
            this.vx = -this.vx;
            // Correzione posizione per evitare che si "incastri"
            this.x = Math.max(this.radius, Math.min(canvasWidth - this.radius, this.x));
        }
        
        if (this.y - this.radius < 0 || this.y + this.radius > canvasHeight) {
            this.vy = -this.vy;
            this.y = Math.max(this.radius, Math.min(canvasHeight - this.radius, this.y));
        }
    }
    
    draw(ctx) {
        ctx.beginPath();
        ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2);
        ctx.fillStyle = this.color;
        ctx.fill();
        ctx.closePath();
    }
}

// Setup
const canvas = document.getElementById('canvas');
canvas.width = 400;
canvas.height = 300;
const ctx = canvas.getContext('2d');

// Crea palla con angolo casuale
const angle = Math.random() * Math.PI * 2;
const speed = 3;
const ball = new BouncingBall(
    canvas.width / 2,
    canvas.height / 2,
    20,
    '#eb6f6f',
    { x: Math.cos(angle) * speed, y: Math.sin(angle) * speed }
);

let lastTime = 0;
function animate(timestamp) {
    const dt = (timestamp - lastTime) / 1000;
    lastTime = timestamp;
    
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ball.update(dt, canvas.width, canvas.height);
    ball.draw(ctx);
    
    requestAnimationFrame(animate);
}

requestAnimationFrame(animate);
```

---
