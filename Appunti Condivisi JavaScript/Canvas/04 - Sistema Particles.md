# Canvas - Sistema Particles

---
## Concetto Base

Un **particle system** gestisce un grande numero di piccoli oggetti (particelle) che seguono regole semplici ma creano effetti complessi insieme.

**Componenti fondamentali**:

1. **Array di particelle** - memorizza tutte le particelle attive
2. **Creazione** - genera nuove particelle con proprietà casuali
3. **Update** - aggiorna posizione e stato (fisica, vita)
4. **Rimozione** - elimina o ricicla particelle morte
5. **Rendering** - disegna tutte le particelle

## Struttura Particella Base

```js
class Particle {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.vx = (Math.random() - 0.5) * 4;  // velocità X
        this.vy = (Math.random() - 0.5) * 4;  // velocità Y
        this.radius = Math.random() * 3 + 1;
        this.color = `hsl(${Math.random() * 360}, 70%, 60%)`;
        this.life = 1.0;    // 1.0 = viva, 0.0 = morta
        this.decay = Math.random() * 0.01 + 0.005; // velocità morte
    }
    
    update(dt) {
        // Movimento
        this.x += this.vx;
        this.y += this.vy;
        
        // Gravità
        this.vy += 0.1;
        
        // Decadimento vita
        this.life -= this.decay;
        
        return this.life > 0; // true se ancora viva
    }
    
    draw(ctx) {
        ctx.save();
        ctx.globalAlpha = this.life; // trasparenza basata su vita
        ctx.beginPath();
        ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2);
        ctx.fillStyle = this.color;
        ctx.fill();
        ctx.restore();
    }
}
```

## Sistema Completo con Object Pooling

```js
class ParticleSystem {
    constructor(maxParticles = 1000) {
        this.particles = [];
        this.maxParticles = maxParticles;
        this.pool = []; // particelle riciclabili
    }
    
    // Ottieni particella da pool o creane una nuova
    createParticle(x, y) {
        let particle;
        
        if (this.pool.length > 0) {
            // Riusa particella dal pool
            particle = this.pool.pop();
            particle.reset(x, y);
        } else {
            // Crea nuova particella
            particle = new Particle(x, y);
        }
        
        this.particles.push(particle);
    }
    
    update(dt) {
        // Update e rimuovi particelle morte
        for (let i = this.particles.length - 1; i >= 0; i--) {
            const particle = this.particles[i];
            
            if (!particle.update(dt)) {
                // Particella morta - metti in pool per riuso
                this.pool.push(this.particles.splice(i, 1)[0]);
            }
        }
    }
    
    draw(ctx) {
        this.particles.forEach(p => p.draw(ctx));
    }
    
    emit(x, y, count = 10) {
        for (let i = 0; i < count; i++) {
            if (this.particles.length < this.maxParticles) {
                this.createParticle(x, y);
            }
        }
    }
}

// Uso
const system = new ParticleSystem(500);

canvas.addEventListener('click', (e) => {
    const pos = getCanvasCoordinates(canvas, e);
    system.emit(pos.x, pos.y, 30); // Emetti 30 particelle
});

function animate() {
    ctx.fillStyle = 'rgba(0, 0, 0, 0.1)';
    ctx.fillRect(0, 0, canvas.width, canvas.height); // trail effect
    
    system.update();
    system.draw(ctx);
    
    requestAnimationFrame(animate);
}
```

## Effetti Avanzati

### Neve

```js
class SnowParticle {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.vx = (Math.random() - 0.5) * 0.5;
        this.vy = Math.random() * 1 + 0.5;
        this.radius = Math.random() * 3 + 1;
        this.swing = Math.random() * 0.5; // oscillazione
        this.swingSpeed = Math.random() * 0.02;
        this.swingOffset = Math.random() * Math.PI * 2;
    }
    
    update(canvasHeight) {
        this.y += this.vy;
        this.x += Math.sin(this.y * this.swingSpeed + this.swingOffset) * this.swing;
        
        // Reset quando esce dal canvas
        if (this.y > canvasHeight) {
            this.y = -10;
            this.x = Math.random() * canvas.width;
        }
        
        return true;
    }
    
    draw(ctx) {
        ctx.beginPath();
        ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2);
        ctx.fillStyle = 'white';
        ctx.fill();
    }
}
```

### Fuochi d'Artificio

```js
class Firework {
    constructor(x, y) {
        this.particles = [];
        const particleCount = 50;
        
        for (let i = 0; i < particleCount; i++) {
            const angle = (Math.PI * 2 * i) / particleCount;
            const speed = Math.random() * 3 + 2;
            
            this.particles.push({
                x: x,
                y: y,
                vx: Math.cos(angle) * speed,
                vy: Math.sin(angle) * speed,
                life: 1.0,
                color: `hsl(${Math.random() * 60 + 30}, 100%, 50%)` // giallo/arancio
            });
        }
    }
    
    update() {
        let allDead = true;
        
        this.particles.forEach(p => {
            p.x += p.vx;
            p.y += p.vy;
            p.vy += 0.05; // gravità
            p.life -= 0.01;
            
            if (p.life > 0) allDead = false;
        });
        
        return !allDead; // false quando tutte morte
    }
    
    draw(ctx) {
        this.particles.forEach(p => {
            if (p.life <= 0) return;
            
            ctx.save();
            ctx.globalAlpha = p.life;
            ctx.beginPath();
            ctx.arc(p.x, p.y, 2, 0, Math.PI * 2);
            ctx.fillStyle = p.color;
            ctx.fill();
            ctx.restore();
        });
    }
}
```

---
