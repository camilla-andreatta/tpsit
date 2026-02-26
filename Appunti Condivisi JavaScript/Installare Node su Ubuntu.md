03-02-2026
## Installare Node su macchina Linux (Ubuntu) a scuola

1. https://nodejs.org/en/download
2. scaricare Stadalone Binary (.xz)
3. estrarre il contenuto del file zippato in Home/development
4. rinominare la cartella in "node"
5. terminale
	1. `cd`
	2. `nano .bashrc`
	3. Aggiungere alla fine del file:
		```bash
		# modifica effettuata il 03/02/2026
		DEV="/home/$USER/development"
		export PATH="$PATH:$DEV/node/bin"
		```
6. Aprire un altro terminale
	1. `cd`
	2. `node -v`
	3. `npm config list`
	4. `npm config get prefix`
	5. `cd /development`
	6. `mkdir npm_global`
	7. `npm config set prefix="/home/[^1]studente/development/npm_global"`
	8. `nano .bashrc`
	9. sostituire l'ultima riga (passaggio 5.3) con:
		```bash
		export PATH="$PATH:$DEV/node/bin:$DEV/npm_global/bin"
		```
	10. `npm install -g json-server`
7. Creare un file json
	1. Aprire un altro terminale
	2. `nano db.json`
	3. copiare l'esempio di https://www.npmjs.com/package/json-server
	4. `json-server db.json`
	5. `ctrl + C` per fermare
	6. Aprire su browser (Firefox) l'URL [http://localhost:3000](http://localhost:3000)
	7. Aprire su browser (Firefox) l'URL [http://localhost:3000:posts](http://localhost:3000:posts)
	8. Aprire su browser (Firefox) l'URL [http://localhost:3000:posts/1](http://localhost:3000:posts/1)

[^1]: da sostituire con eventuale nome utente diverso
