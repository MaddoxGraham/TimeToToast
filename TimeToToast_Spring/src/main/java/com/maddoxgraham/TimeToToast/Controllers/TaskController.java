package com.maddoxgraham.TimeToToast.Controllers;


import com.maddoxgraham.TimeToToast.Models.Task;
import com.maddoxgraham.TimeToToast.Services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTask() {
        List<Task> tasks = taskService.findAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/find/{idTask}")
    public ResponseEntity<Task> getTaskById(@PathVariable("idTask") Long idTask) {
        Task task = taskService.findTaskByIdTask(idTask);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        Task newTask = taskService.addTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        Task updateTask = taskService.updateTask(task);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idTask}")
    public ResponseEntity<?> deleteTask(@PathVariable("idTask") Long idTask){
        taskService.deleteTask(idTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
