package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.EventDto;
import com.maddoxgraham.TimeToToast.DTOs.UserEventsDto;
import com.maddoxgraham.TimeToToast.DTOs.UserTaskDto;
import com.maddoxgraham.TimeToToast.Mappers.UserTaskMapper;
import com.maddoxgraham.TimeToToast.Models.*;
import com.maddoxgraham.TimeToToast.Repository.TaskRepository;
import com.maddoxgraham.TimeToToast.Repository.UserTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserTaskService {

    private final UserTaskRepository userTaskRepository;
    private final PersonService personService;
    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    // Trouver les tâches associées à une personne
    public List<UserTask> getTasksByIdPerson(Long idPerson) {
        List<UserTask> userTasksbyPerson = findAllUserTask().stream()
                .filter(userEventRole -> userEventRole.getUserTaskKey().getIdPerson().equals(idPerson))
                .collect(Collectors.toList());

        Map<String, UserEventsDto> roleToUserEventsDtoMap = new HashMap<>();
        List<UserEventsDto> userEventsDtoList = new ArrayList<>();

        for (UserTask userTask: userTasksbyPerson){
            Optional<Task> taskOpt = taskRepository.findById(userTask.getTask().getIdTask());
            if(taskOpt.isPresent()){
                Task task = taskOpt.get();
                EventDto eventDto = taskMapper.toDto(task);

                UserEventsDto userEventsDto = roleToUserEventsDtoMap.get(userEventRole.getRole());
                if(userEventsDto == null) {
                    userEventsDto = UserEventsDto.builder()
                            .idPerson(idPerson)
                            .role(userEventRole.getRole())
                            .events((new EventDto[]{eventDto}))
                            .build();
                    roleToUserEventsDtoMap.put(userEventsDto.getRole(), userEventsDto);
                    userEventsDtoList.add(userEventsDto);
                } else {
                    EventDto[] existingEvents = userEventsDto.getEvents();
                    EventDto[] updatedEvents = Arrays.copyOf(existingEvents, existingEvents.length + 1);
                    updatedEvents[existingEvents.length] = eventDto;
                    userEventsDto.setEvents(updatedEvents);
                }
            }
        }
        return userEventsDtoList;
    }

    //retourne la liste de toutes les entrées dans UserTask
    private List<UserTask> findAllUserTask() {
        return userTaskRepository.findAll();
    }

    // Trouver les personnes associées à une tâche
    public List<Person>getPersonsByIdTask(Long idTask) { }

    // Ajouter des HiddenUserTasks pour une tâche
    public void addHiddenUsersForTask(List<HiddenUserTaskDto> hiddenUserTaskDtos) {
        List<HiddenUserTask> hiddenUserTasks = hiddenUserTaskDtos.stream()
                .map(dto -> HiddenUserTaskMapper.toEntity(dto, userService, taskService, guestService))
                .collect(Collectors.toList());

        hiddenUserTaskRepository.saveAll(hiddenUserTasks);
    }

    // Supprimer une tâche de la table si elle n'est plus cachée pour personne
    public boolean deleteTaskIfNoMoreHidden(Long idTask) {
        List<UserTask> hiddenUserTasks = userTaskRepository.findAllByTaskIdTask(idTask);
        if (hiddenUserTasks.isEmpty()) {
            userTaskRepository.deleteByTaskIdTask(idTask);
            return true;
        }
        return false;
    }

    // Modifier pour qui cette tâche est cachée
    public void updateHiddenUsersForTask(Long idTask, List<HiddenUserTaskDto> hiddenUserTaskDtos) {
       // Supprimer tous les anciens enregistrements pour cette tâche
       hiddenUserTaskRepository.deleteByTaskIdTask(idTask);

       // Ajouter les nouveaux
       List<HiddenUserTask> newHiddenUserTasks = hiddenUserTaskDtos.stream()
                .filter(dto -> dto.getIdTask().equals(idTask)) // S'assurer que le ID de la tâche correspond
               .map(dto -> HiddenUserTaskMapper.toEntity(dto, userService, taskService, guestService))
               .collect(Collectors.toList());

        if (!newHiddenUserTasks.isEmpty()) {
           hiddenUserTaskRepository.saveAll(newHiddenUserTasks);
       } else {
           // Si la liste est vide, c'est peut-être une indication que la tâche ne doit pas être cachée pour personne.
            // Vous pouvez gérer cela comme vous le souhaitez, peut-être en supprimant la tâche ?
           deleteTaskIfNoMoreHidden(idTask);
       }
   }
}
