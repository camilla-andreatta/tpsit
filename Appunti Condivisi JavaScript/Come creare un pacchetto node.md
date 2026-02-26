
DEV="/home/$USER"
export PASSTH="$PATH: $DEV/development/node/bin: $DEV/npm_global/bin"
$PATH per non perdere riferimento al vecchio valore di PATH
devo rendere il path accessibile sia al bin di node che al bin di npm_global

node-v da versione di node installata

come installare un pacchetto a livello globale
npm install -g 

## creare pacchetto node
da un posto dalla home si crea una cartella

mkdir my-first
cd my-first
npm init ./(lo crea nella directory corrente)
"type": "module" (uno dei moduli che utilizza node)

così è stato creato il file package.json

fastify (https://fastify.dev/)
npm install fastify
è stato creato node_modules
ora nel package.json sono state aggiunte dependencies, dicendo che dipende da altri pacchetti, se cancello node_modules quando do npm install controllerà il file package.json e se lo porterà dietro da solo

hx app.js (in cui scrivo codice che mi serve)
``` js
// Require the framework and instantiate it

// ESM
import Fastify from 'fastify'
const fastify = Fastify({
logger: true
})

// Declare a route
fastify.get('/', function (request, reply) {
reply.send({ hello: 'world' })
})

// Run the server!
fastify.listen({ port: 3000 }, function (err, address) {
if (err) {
fastify.log.error(err)
process.exit(1)
}

// Server is now listening on ${address}
})
```

ora posso fare una richiesta al server
curl -X GET localhost:3000