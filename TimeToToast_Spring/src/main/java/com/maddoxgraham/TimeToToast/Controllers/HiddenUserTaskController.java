package com.maddoxgraham.TimeToToast.Controllers;

//import com.maddoxgraham.TimeToToast.DTOs.HiddenUserTaskDto;
//import com.maddoxgraham.TimeToToast.Models.*;
//import com.maddoxgraham.TimeToToast.Services.HiddenUserTaskService;
//import com.maddoxgraham.TimeToToast.Services.TaskService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/hiddenUserTask")
@AllArgsConstructor
public class HiddenUserTaskController {

    //private final HiddenUserTaskService hiddenUserTaskService;

    // Trouver toutes les tâches cachées pour un idUser ou idGuest donné
//    @GetMapping("/findTasks")
//    public ResponseEntity<List<HiddenUserTask>> findTasks(
//            @RequestParam("id") Long id,
//            @RequestParam("type") String type) {  // type = "user" ou "guest"
//
//        List<HiddenUserTask> hiddenTasks = "user".equals(type) ?
//                hiddenUserTaskService.findTasksByUserId(id) :
//                hiddenUserTaskService.findTasksByGuestId(id);
//
//        return hiddenTasks.isEmpty() ?
//                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
//                new ResponseEntity<>(hiddenTasks, HttpStatus.OK);
//    }

    // Ajouter un ou plusieurs users/guests pour une tâche donnée
//    @PostMapping("/add")
//    public ResponseEntity<Void> addHiddenUsers(
//            @RequestBody List<HiddenUserTaskDto> hiddenUserTaskDtos) {
//
//        hiddenUserTaskService.addHiddenUsersForTask(hiddenUserTaskDtos);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    // Supprimer une tâche de la table si elle n'est plus cachée pour personne
//    @DeleteMapping("/deleteTask/{idTask}")
//    public ResponseEntity<Void> deleteTaskIfNoMoreHidden(@PathVariable Long idTask) {
//        boolean deleted = hiddenUserTaskService.deleteTaskIfNoMoreHidden(idTask);
//        return deleted ?
//                ResponseEntity.noContent().build() :
//                ResponseEntity.notFound().build();
//    }

    // Modifier pour qui cette tâche est cachée
//    @PutMapping("/update/{idTask}")
//    public ResponseEntity<Void> updateHiddenUsers(
//            @PathVariable Long idTask,
//            @RequestBody List<HiddenUserTaskDto> hiddenUserTaskDtos) {
//
//        hiddenUserTaskService.updateHiddenUsersForTask(idTask, hiddenUserTaskDtos);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}

