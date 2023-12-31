package com.maddoxgraham.TimeToToast.Controllers;



import com.maddoxgraham.TimeToToast.DTOs.NewTaskDto;

import com.maddoxgraham.TimeToToast.DTOs.TaskDto;
import com.maddoxgraham.TimeToToast.Models.Task;
import com.maddoxgraham.TimeToToast.Models.UserTask;
import com.maddoxgraham.TimeToToast.Repository.UserTaskRepository;
import com.maddoxgraham.TimeToToast.Services.TaskService;
import com.maddoxgraham.TimeToToast.Services.UserTaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final UserTaskService userTaskService;



    @PostMapping("/add")
    public ResponseEntity<TaskDto> addTask(@RequestBody TaskDto taskDto){
        TaskDto newTaskDto = taskService.addTask(taskDto);
        return new ResponseEntity<>(newTaskDto, HttpStatus.CREATED);
    }

    @GetMapping("/all/{idEvent}")
    public ResponseEntity<List<NewTaskDto>> getAllTask(@PathVariable Long idEvent) {
        List<NewTaskDto> newTaskDtos = taskService.findAllTasks(idEvent);
        return new ResponseEntity<>(newTaskDtos, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TaskDto> addTask(@RequestBody NewTaskDto newTaskDto){
        Task task = taskService.addTask(newTaskDto);
        TaskDto taskDto = userTaskService.addUserTask(newTaskDto, task);

        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task){
        Task updateTask = taskService.updateTask(task);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @PostMapping("/addTaskAssignee/{idTask}")
    public ResponseEntity<?> addTaskAssignee(@PathVariable("idTask") Long idTask, @RequestBody Long idPerson){
        userTaskService.addTaskAssignee(idTask, idPerson);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/removeTaskAssignee/{idTask}")
    public ResponseEntity<?> removeTaskAssignee(@PathVariable("idTask") Long idTask, @RequestBody Long idPerson){
        userTaskService.removeTaskAssignee(idTask, idPerson);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask/{idTask}")
    public ResponseEntity<?> deleteTask(@PathVariable("idTask") Long idTask){
        taskService.deleteTask(idTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
