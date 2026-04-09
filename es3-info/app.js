const tasks = []; // Array dei dati
const form = document.getElementById('task-form');
const container = document.getElementById('tasks-container');
const submitBtn = document.getElementById('submit-btn');

// --- 1. FUNZIONI DI VALIDAZIONE ---
function validateField(input, message) {
    const small = input.parentElement.querySelector('.error-msg');
    if (input.value.trim() === "") {
        input.classList.add('input-error');
        small.innerText = message;
        return false;
    } else {
        input.classList.remove('input-error');
        small.innerText = "";
        return true;
    }
}

// --- 2. MANIPOLAZIONE DOM: CREARE LA CARD ---
function renderTasks(dataToRender = tasks) {
    container.innerHTML = ""; // Svuotiamo il contenitore

    dataToRender.forEach(task => {
        // Creiamo il div della card
        const card = document.createElement('div');
        card.className = 'task-card';
        
        // Definiamo il contenuto usando Template Literals
        card.innerHTML = `
            <div>
                <span class="priority-tag ${task.priority.toLowerCase()}">${task.priority}</span>
                <h3 style="display:inline; margin-left:10px;">${task.title}</h3>
            </div>
            <div>
                <button data-id="${task.id}" data-action="edit">Modifica</button>
                <button data-id="${task.id}" data-action="delete">Elimina</button>
            </div>
        `;
        // Lo aggiungiamo al DOM
        container.appendChild(card);
    });
}


// --- 3. GESTIONE EVENTI (Delegation) ---
document.addEventListener('click', (e) => {
    const id = e.target.dataset.id;
    const action = e.target.dataset.action;

    if (action === 'delete') {
        if(confirm("Sei sicuro?")){
            // Usiamo filter per sovrascrivere l'array senza l'elemento rimosso
            // Nota: id è una stringa perché viene dal dataset
            const index = tasks.findIndex(t => t.id === id);
            if (index !== -1) {
                tasks.splice(index, 1);
                renderTasks();
            }
        }
    }

    if (action === 'edit') {
        const task = tasks.find(t => t.id === id);
        if (task) {
            document.getElementById('task-id').value = task.id;
            document.getElementById('title').value = task.title;
            
            const radios = document.getElementsByName('priority');
            radios.forEach(r => {
                r.checked = (r.value === task.priority); // CORRETTO: task.priority
            });
            
            submitBtn.innerText = "Aggiorna Task";
        }
    }
});

// --- 4. INVIO DEL FORM (SUBMIT) ---
form.addEventListener('submit', (e) => {
    e.preventDefault();

    const titleInput = document.getElementById('title');
    const priorityRadios = document.getElementsByName('priority');
    const hiddenIdField = document.getElementById('task-id'); // Recuperiamolo qui
    
    const isTitleValid = validateField(titleInput, "Titolo obbligatorio");
    
    let selectedPriority = "";
    priorityRadios.forEach(r => { if(r.checked) selectedPriority = r.value; });

    if (isTitleValid && selectedPriority !== "") {
        const taskId = hiddenIdField.value;

        if (taskId) {
            // LOGICA UPDATE
            const index = tasks.findIndex(t => t.id === taskId); // CORRETTO: taskId
            if (index !== -1) {
                tasks[index].title = titleInput.value;
                tasks[index].priority = selectedPriority;
            }
            submitBtn.innerText = "Aggiungi Task";
        } else {
            // LOGICA CREATE
            const newTask = {
                id: Date.now().toString(),
                title: titleInput.value,
                priority: selectedPriority
            };
            tasks.push(newTask);
        }

        renderTasks();
        form.reset();
        hiddenIdField.value = ""; // Fondamentale per tornare in modalità "Aggiungi"
    }
});