package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.HiddenUserTaskDto;
import com.maddoxgraham.TimeToToast.Mappers.HiddenUserTaskMapper;
import com.maddoxgraham.TimeToToast.Models.*;
import com.maddoxgraham.TimeToToast.Repository.HiddenUserTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HiddenUserTaskService {

    private final HiddenUserTaskRepository hiddenUserTaskRepository;
    //private final UserService userService;
    private final TaskService taskService;
    //private final GuestService guestService;

    // Trouver tous les HiddenUserTasks par UserId
//    public List<HiddenUserTask> findTasksByUserId(Long idUser) {
//        return hiddenUserTaskRepository.findAllByUserIdUser(idUser);
//    }

    // Trouver tous les HiddenUserTasks par GuestId
//    public List<HiddenUserTask> findTasksByGuestId(Long idGuest) {
//        return hiddenUserTaskRepository.findAllByGuestIdGuest(idGuest);
//    }

    // Ajouter des HiddenUserTasks pour une tâche
//    public void addHiddenUsersForTask(List<HiddenUserTaskDto> hiddenUserTaskDtos) {
//        List<HiddenUserTask> hiddenUserTasks = hiddenUserTaskDtos.stream()
//                .map(dto -> HiddenUserTaskMapper.toEntity(dto, userService, taskService, guestService))
//                .collect(Collectors.toList());
//
//        hiddenUserTaskRepository.saveAll(hiddenUserTasks);
//    }

    // Supprimer une tâche de la table si elle n'est plus cachée pour personne
    public boolean deleteTaskIfNoMoreHidden(Long idTask) {
        List<HiddenUserTask> hiddenUserTasks = hiddenUserTaskRepository.findAllByTaskIdTask(idTask);
        if (hiddenUserTasks.isEmpty()) {
            hiddenUserTaskRepository.deleteByTaskIdTask(idTask);
            return true;
        }
        return false;
    }

    // Modifier pour qui cette tâche est cachée
//    public void updateHiddenUsersForTask(Long idTask, List<HiddenUserTaskDto> hiddenUserTaskDtos) {
//        // Supprimer tous les anciens enregistrements pour cette tâche
//        hiddenUserTaskRepository.deleteByTaskIdTask(idTask);
//
//        // Ajouter les nouveaux
//        List<HiddenUserTask> newHiddenUserTasks = hiddenUserTaskDtos.stream()
//                .filter(dto -> dto.getIdTask().equals(idTask)) // S'assurer que le ID de la tâche correspond
//                .map(dto -> HiddenUserTaskMapper.toEntity(dto, userService, taskService, guestService))
//                .collect(Collectors.toList());
//
//        if (!newHiddenUserTasks.isEmpty()) {
//            hiddenUserTaskRepository.saveAll(newHiddenUserTasks);
//        } else {
//            // Si la liste est vide, c'est peut-être une indication que la tâche ne doit pas être cachée pour personne.
//            // Vous pouvez gérer cela comme vous le souhaitez, peut-être en supprimant la tâche ?
//            deleteTaskIfNoMoreHidden(idTask);
//        }
//    }
}
