package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.TaskDto;
import com.maddoxgraham.TimeToToast.DTOs.UserTaskDto;
import com.maddoxgraham.TimeToToast.Services.UserTaskService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hiddenUserTask")
@AllArgsConstructor
public class UserTaskController {

    private final UserTaskService userTaskService;

    // Trouver toutes les tâches cachées pour un idUser ou idGuest donné
    @GetMapping("/findTasks")
    public ResponseEntity<List<TaskDto>> findTasks(
            @RequestParam("id") Long id,
            @RequestParam("type") String type) {  // type = "user" ou "guest"
        List<TaskDto> hiddenTasks = userTaskService.getTasksByIdPerson(id);
        return hiddenTasks.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(hiddenTasks, HttpStatus.OK);
    }

    // Ajouter un ou plusieurs users/guests pour une tâche donnée
    @PostMapping("/add")
    public ResponseEntity<Void> addHiddenUsers(
            @RequestBody List<UserTaskDto> userTaskDtos) {
        userTaskService.addHiddenUsersForTask(userTaskDtos);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Modifier pour qui cette tâche est cachée
    @PutMapping("/update/{idTask}")
    public ResponseEntity<Void> updateHiddenUsers(
            @PathVariable Long idTask,
            @RequestBody List<UserTaskDto> userTaskDtos) {
        userTaskService.updateHiddenUsersForTask(idTask, userTaskDtos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

