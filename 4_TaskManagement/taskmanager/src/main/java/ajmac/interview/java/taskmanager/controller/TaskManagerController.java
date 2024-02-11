package ajmac.interview.java.taskmanager.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ajmac.interview.java.taskmanager.exceptions.TaskModificationException;
import ajmac.interview.java.taskmanager.model.Task;
import ajmac.interview.java.taskmanager.model.TaskStatus;
import ajmac.interview.java.taskmanager.repository.TaskRepository;

@RestController
@RequestMapping("tasks")
public class TaskManagerController {

    private final TaskRepository taskRepository;

    public TaskManagerController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("")
    Page<Task> all(@RequestParam(name = "filter", required = false) String filter,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "5") int size) {

        if (filter == null || filter.isEmpty()) {
            return taskRepository.findAll(PageRequest.of(page, size));
        }

        return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(filter, filter,
                PageRequest.of(page, size));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Task> createNewTask(@RequestBody Task newTask) {

        if (newTask == null) {
            throw new TaskModificationException(HttpStatus.BAD_REQUEST, "The provided task may not be null.");
        }

        // Only allow tasks to be created during weekdays
        // (assuming this means the date on which task should be executed/fulfilled)
        if (newTask.getExecutionDate() == null || newTask.getExecutionDate().getDayOfWeek().getValue() >= 6) {
            throw new TaskModificationException(HttpStatus.BAD_REQUEST, "Tasks may only be created during weekdays.");
        }

        return new ResponseEntity<>(taskRepository.save(newTask), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id) {

        if (id == null) {
            throw new TaskModificationException(HttpStatus.BAD_REQUEST, "The provided task ID may not be null.");
        }

        return new ResponseEntity<>(taskRepository.findById(id)
                .orElseThrow(() -> new TaskModificationException(HttpStatus.NOT_FOUND, "Task not found for ID: " + id)),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@RequestBody Task updatedTask, @PathVariable Long id) {

        if (id == null || updatedTask == null) {
            throw new TaskModificationException(HttpStatus.BAD_REQUEST,
                    "The provided task or task ID may not be null.");
        }

        return new ResponseEntity<>(taskRepository.findById(id)
                .filter(task -> !updatedTask.getStatus().equals(task.getStatus()) || TaskStatus.PENDING.equals(task
                        .getStatus()))
                .map(task -> {
                    task.setStatus(updatedTask.getStatus());

                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setCreationDate(updatedTask.getCreationDate());

                    if (updatedTask.getExecutionDate().getDayOfWeek().getValue() >= 6) {
                        throw new TaskModificationException(HttpStatus.BAD_REQUEST,
                                "Tasks may only be created during weekdays.");
                    } else {
                        task.setExecutionDate(updatedTask.getExecutionDate());
                    }
                    return taskRepository.save(task);
                })
                .orElseThrow(() -> new TaskModificationException(HttpStatus.BAD_REQUEST,
                        "Tasks may not be updated as its status is no longer PENDING. Task ID: " + id)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteTask(@PathVariable Long id) {

        if (id == null) {
            throw new TaskModificationException(HttpStatus.NOT_FOUND, "The provided task ID may not be null.");
        }

        Task taskToDelete = taskRepository.findById(id)
                .orElseThrow(() -> new TaskModificationException(HttpStatus.NOT_FOUND, "No task found for ID: " + id));

        if (!TaskStatus.PENDING.equals(taskToDelete.getStatus())) {
            throw new TaskModificationException(HttpStatus.BAD_REQUEST,
                    "Task may not be deleted as its status is no longer PENDING. Task ID: " + id);
        }

        if (taskToDelete.getCreationDate().plusDays(5).isAfter(LocalDate.now())) {
            throw new TaskModificationException(
                    HttpStatus.BAD_REQUEST,
                    "Task may not be deleted as its creation date is less than 5 days ago.");
        }
        taskRepository.deleteById(id);

        return new ResponseEntity<>("Task Deleted", HttpStatus.OK);
    }
}
