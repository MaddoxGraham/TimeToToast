package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.HiddenUserTaskDto;
import com.maddoxgraham.TimeToToast.Models.*;
import com.maddoxgraham.TimeToToast.Services.HiddenUserTaskService;
import com.maddoxgraham.TimeToToast.Services.TaskService;
import com.maddoxgraham.TimeToToast.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hiddenUserTask")
@AllArgsConstructor
public class HiddenUserTaskController {

    private final HiddenUserTaskService hiddenUserTaskService;
    private final UserService userService;
    private final TaskService taskService;




    @GetMapping("/find/{idUser}")
    public ResponseEntity<List<HiddenUserTaskDto>> findTasksByUserId(@PathVariable("idUser") Long idUser) {
        // Utilisation du service pour trouver la liste des HiddenUserTasks par idUser
        List<HiddenUserTaskDto> hiddenUserTaskDtos = hiddenUserTaskService.findTasksByUserId(idUser);

        // Vérification si la liste est vide ou non
        if (!hiddenUserTaskDtos.isEmpty()) {
            return new ResponseEntity<>(hiddenUserTaskDtos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/findTask/{idUser}/{idTask}")
    public ResponseEntity<HiddenUserTask> getHiddenUserTask(@PathVariable("idUser") Long idUser,@PathVariable("idTask") Long idTask) {
        HiddenUserTaskKey hiddenUserTaskKey = new HiddenUserTaskKey(idUser, idTask);
        Optional<HiddenUserTask> optionalHiddenUserTask = hiddenUserTaskService.findByHiddenUserTaskKey(hiddenUserTaskKey);

        // Vérification si l'objet existe
        if (optionalHiddenUserTask.isPresent()) {
            return new ResponseEntity<>(optionalHiddenUserTask.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<HiddenUserTask> addHiddenUserTask(@RequestBody HiddenUserTaskDto hiddenUserTaskDto){
        HiddenUserTask newHiddenUserTask = hiddenUserTaskService.addHiddenUserTask(hiddenUserTaskDto);
        return new ResponseEntity<>(newHiddenUserTask, HttpStatus.CREATED);
    }

    // Mettre à jour une tâche cachée
//    @PutMapping("/update")
//    public ResponseEntity<HiddenUserTask> updateHiddenUserTask(@RequestBody HiddenUserTask hiddenUserTask) {
//        HiddenUserTask updatedHiddenUserTask = hiddenUserTaskService.updateHiddenUserTask(hiddenUserTask);
//        return new ResponseEntity<>(updatedHiddenUserTask, HttpStatus.OK);
//    }



    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteHiddenUserTask(@RequestBody HiddenUserTaskDto dto) {
        if (hiddenUserTaskService.deleteByHiddenUserTaskKey(dto, userService, taskService)) {  // Removed semicolon
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
