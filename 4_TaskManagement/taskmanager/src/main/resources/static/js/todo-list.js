/**
 * Task Class
 */
class Task {
    id;
    title;
    description;
    status;
    creationDate;
    executionDate;

    constructor(id, title, description, status, creationDate, executionDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.creationDate = creationDate;
        this.executionDate = executionDate;
    }

    taskDetailsHTML() {
        return `
            <span class="task-cell">${this.title}</span> 
            <span class="task-cell">${this.description}</span> 
            <span class="task-cell">${this.status}</span> 
            <span class="task-cell">${this.creationDate}</span> 
            <span class="task-cell">${this.executionDate}</span>
            <button class="btn btn-danger delete-task" title="Delete task" onclick=deleteTask(${this.id})>x</button>`;
    }
}

// Global variables
currentPage = 1;
totalPages = 1;
pageSize = 5;

/**
 * Date utility for formatting into format yyyy-MM-dd
 * @param {*} date 
 * @returns 
 */
function formatDate(date) {
    return `${date.getFullYear()}-${('0'+(date.getMonth()+1)).slice(-2)}-${('0'+date.getDate()).slice(-2)}`;
}

/**
 * GET list of tasks from server
 * @returns 
 */
async function retrieveTasks() {

    const taskFilter = document.getElementById('filterTasks');
    const filterText = taskFilter.value;

    const response = await fetch(`/tasks?filter=${filterText}&page=${currentPage - 1}&size=${pageSize}`).then(res => {
        if (res.ok) {
            return res.json();
        }

        return Promise.reject(res);
    }).catch((err) => {
        showError(err);
    });
    const tasks = response.content;
    totalPages = response.totalPages;
    return tasks;
}

/**
 * GET list of tasks from server
 * @returns 
 */
async function retrieveTaskById(taskId) {
    const response = await fetch('/tasks/' + taskId).then(res => {
        if (res.ok) {
            return res.json();
        }

        return Promise.reject(res);
    }).catch((err) => {
        showError(err);
    });
    return await response;    
}

/**
 * POST new task to server
 */
async function addNewTask() {
    
    const newTaskTitle = document.getElementById('newTaskTitle').value;
    const newTaskDescription = document.getElementById('newTaskDescription').value;
    const newTaskExecutionDate = new Date(document.getElementById('newTaskExecutionDate').valueAsNumber);

    const newTask = new Task(
        undefined, // id is generated automatically by server
        newTaskTitle,
        newTaskDescription,
        'PENDING',
        formatDate(new Date()),
        formatDate(newTaskExecutionDate)
    );

    const response = await fetch('/tasks', {
        method: 'POST',
        body: JSON.stringify(newTask),
        headers: new Headers({ 'content-type': 'application/json' })
    }).then(res => {
        if (res.ok) {
            return res.json();
        }

        return Promise.reject(res);
    }).catch((err) => {
        showError(err);
    });

    await refreshTaskList();
}

/**
 * PUT task change to server
 */
async function updateTask(task) {

     await fetch('/tasks/' + task.id, {
        method: 'PUT',
        body: JSON.stringify(task),
        headers: new Headers({ 'content-type': 'application/json' })
    }).then(res => {
        if (res.ok) {
            return res.json();
        }

        return Promise.reject(res);
    }).catch((err) => {
        showError(err);
    });

    await refreshTaskList();
}

/**
 * DELETE task from server
 */
async function deleteTask(taskId) {   

    await fetch('/tasks/'+taskId, {
        method: 'DELETE',
        headers: new Headers({ 'content-type': 'application/json' })
    }).then(res => {
        if (res.ok) {
            return res.text();
        }

        return Promise.reject(res);
    }).catch((err) => {
        showError(err);
    });

    await refreshTaskList();
}

/**
 * Refresh the task list displayed
 */
async function refreshTaskList() {
    const tasks = await retrieveTasks();

    const todoList = document.getElementById('todoList');
    todoList.innerHTML = '';

    // If no tasks, write message 
    if (tasks.length === 0) {
        todoList.innerHTML = `<div class="no-tasks-msg"><p>Create a task in the form above to get started!</p></div>`;
    } else {

        // Else display tasks
        for (let i = 0; i < tasks.length; i++) {
            const task = new Task(
                tasks[i].id,
                tasks[i].title,
                tasks[i].description,
                tasks[i].status,
                tasks[i].creationDate,
                tasks[i].executionDate
            );

            const taskItem = document.createElement('li');
            taskItem.className = 'task-row';
            taskItem.innerHTML = task.taskDetailsHTML();
            todoList.appendChild(taskItem);

            const taskCells = taskItem.getElementsByClassName('task-cell');
            for (let c = 0; c < taskCells.length; c++) {
                taskCells.item(c).onclick = function () { showModifyTaskModal(task) };
            }
        }
    }

    // Update pagination
    const currentPageEl = document.getElementById('currentPage');
    currentPageEl.innerText = currentPage;
    const totalPagesEl = document.getElementById('totalPages');
    totalPagesEl.innerText = totalPages;

    const prevPageBtn = document.getElementById('prevPageBtn');
    prevPageBtn.setAttribute('disabled', currentPage <= 1 ? 'true' : 'false');
    if (currentPage <= 1) {
        prevPageBtn.setAttribute('disabled', '');
    } else {
        prevPageBtn.removeAttribute('disabled');
    }
    const nextPageBtn = document.getElementById('nextPageBtn');
    if (currentPage >= totalPages) {
        nextPageBtn.setAttribute('disabled', '');
    } else {
        nextPageBtn.removeAttribute('disabled');
    }
}

function filterTasks(inputEvent) {
    currentPage = 1;
    refreshTaskList();
}

/**************************
 * TASK MODIFICATION MODAL FUNCTIONS
 *************************/

async function showModifyTaskModal(task) {

    const main = document.querySelector('main');

    const statusModalTemplate = document.querySelector('#modifyTaskTemplate');
    const statusModalClone = statusModalTemplate.content.cloneNode(true);

    main.appendChild(statusModalClone);

    const modifyTitle = document.getElementById('modifyTaskTitle');
    modifyTitle.value = task.title;
    const modifyDescription = document.getElementById('modifyTaskDescription');
    modifyDescription.value = task.description;
    const modifyDueDate = document.getElementById('modifyTaskExecutionDate');
    modifyDueDate.value = task.executionDate;
    const modifyStatusSelector = document.getElementById('modifyStatusSelector');
    modifyStatusSelector.value = task.status;

    const modifyTaskBtn = document.getElementById('modifyTaskBtn');
    modifyTaskBtn.onclick = async function () {
        const modifiedTask = new Task(task.id, modifyTitle.value, modifyDescription.value, modifyStatusSelector.value,
            task.creationDate, formatDate(new Date(modifyDueDate.valueAsNumber)));

        await updateTask(modifiedTask);
        hideModifyTaskModal();
        refreshTaskList();
    }
}

function hideModifyTaskModal() {
    const main = document.querySelector('main');
    
    const statusModal = document.querySelector('#modifyTaskModal');

    if (statusModal) {
        main.removeChild(statusModal);
    }
}

/**************************
 * END STATUS MODAL FUNCTIONS
 *************************/

/**************************
 * PAGINATION FUNCTIONS
 *************************/
function previousPage() {
    if (currentPage > 1) {
        currentPage--;

        refreshTaskList();
    }
}

function nextPage() {
    if (currentPage < totalPages) {
        currentPage++;

        refreshTaskList();
    }
}

/**************************
 * END PAGINATION FUNCTIONS
 *************************/

function showError(errorResponse) {
    errorResponse.text().then((json) => {
        const main = document.querySelector("main");

        const errorTemplate = document.querySelector("#errormessagetemplate");
        const errorClone = errorTemplate.content.cloneNode(true);

        const errorModal = main.appendChild(errorClone);

        const errorMessageElement = document.getElementById('errorMessageText');
        errorMessageElement.innerText = json;
    });
}

function hideError() {
    const main = document.querySelector('main');
    
    const errorModal = document.querySelector('#errorMessage');

    if (errorModal) {
        main.removeChild(errorModal);
    }
}

function initialiseApp() {
    const newTaskExecutionDate = document.getElementById('newTaskExecutionDate');
    newTaskExecutionDate.value = formatDate(new Date());
}

// Initialise form
initialiseApp();
// Refresh list on page load
refreshTaskList();
