# 4. Task: Task Management System

## Task Description

Objective: The objective is to create a to-do list management system using Java and MySQL

## Run the code

For simplicity a docker installation has been provided in order to install the MySQL database and application in a container.
The exposed ports may be modified in the ./.env file.

> docker-compose up -d

Open the browser to http://localhost:8081

## Environment Configuration

A MySQL database is initialised in the docker container.

The back-end project is a Spring Boot application.

The front-end project is a JavaScript project. For simplicity, I have included the JavaScript project inside the src/resources/static folder.
In a more complex project I would have created a separate project using a framework such as Angular.

## Backend (Java)

The main src code is under the package ajmac.interview.java.taskmanager.

### Data Model

The data model entity can found under "model/Task.java" and the status is a enumerator in "model/TaskStatus.java".
The database schema is automatically created by Spring when the application starts up. The configuration is in the application.properties file.

```
spring.jpa.hibernate.ddl-auto=update
```

### CRUD operations

The CRUD operations are provided through a RestController ("controller/TaskManagerController.java") for the REST API and a JpaRepository interface ("repository/TaskRepository.java") for requests to the database.

### Business Logics

First load of the application:
![First load of application](./examples/TaskManager_NoTasks.png)

#### Tasks can only be created during weekdays

Tasks whose Due Data is weekend will be prevented by an error message:
![Weekdays only error](./examples/TaskManager_WeekdaysOnly.png)

#### Tasks can only be updated or deleted if in status pending

Tasks can be modified by clicking on a row:
![Modify task modal](./examples/TaskManager_ModifyTask.png)

Tasks whose status is no longer "PENDING" can not be modified (except status):
![Modify pending only error](./examples/TaskManager_ModifyTask_PendingOnly.png)

Tasks whose status is no longer "PENDING" can not be deleted:
![Delete pending only error](./examples/TaskManager_Delete_PendingOnly.png)

#### Tasks can only be deleted if its creation date is older than 5 days ago

Tasks which were created less than 5 days ago cannot be deleted:
![Delete too recent error](./examples/TaskManager_DeleteTooRecent.png)

## Frontend (HTML/CSS/JavaScript)

The front-end project is a JavaScript project. For simplicity, I have included the JavaScript project inside the "src/resources/static" folder.
In a more complex project I would have created a separate project using a framework such as Angular.

### Simple User Interface

#### View task list

![View task list](./examples/TaskManager_Pagination1.png)

#### Add a new task to the list when the user submits the form

![Create new task](./examples/TaskManager_CreateTask.png)

#### Allow users to mark a task as in progress or completed by clicking on it

![Change task status](./examples/TaskManager_ChangeStatus.png)
![Change task status, view in list](./examples/TaskManager_ChangeStatus2.png)

#### Allow users to delete a task

![Delete task](./examples/TaskManager_Delete.png)

## Front-End and Back-End Integration

In the front-end JavaScript file, "todo-list.js", Fetch API calls have been used to make requests to the back-end API.

## Extra Functionalities (Nice to have)

### Implement task searching and filtering

A filter text box has been added to allow a free text search over title and description. The filter is ran on the server side.
This could be improved by including filters per column and adding sorting.
![Filter tasks](./examples/TaskManager_Filters.png)

### Add pagination to the task list

A simple pagination is ran making use of the Spring PagingAndSortingRepository functionality.
The pagination buttons have been added below the task list.
![Page 1](./examples/TaskManager_Pagination1.png)
![Page 2](./examples/TaskManager_Pagination2.png)

### Improve the user interface with animations and transitions

A very simple transition has been added to when the user hovers over a row in the task list so that the colour fades in.
This has been added to the end of the following CSS:

```
.task-row {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #f0f9ff;
    padding: 0.5rem;
    transition: 0.3s;
}
```

### Implement an e-mail notification feature for reminders of pending tasks

I have not implemented this in this case.
However, this could be accomplished by adding a timed function (or e.g. a crontab) that calls an endpoint in the back-end API to determine which tasks are close to their due date and send an email to the registered user accordingly.
