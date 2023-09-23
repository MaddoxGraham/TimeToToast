package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.EventDto;
import com.maddoxgraham.TimeToToast.DTOs.TaskDto;
import com.maddoxgraham.TimeToToast.DTOs.UserEventsDto;
import com.maddoxgraham.TimeToToast.DTOs.UserTaskDto;
import com.maddoxgraham.TimeToToast.Mappers.TaskMapper;
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

    public List<TaskDto> getTasksByIdPerson(Long idPerson) {
        List<UserTask> userTasksByPerson = userTaskRepository.findByIdPerson(idPerson);
        List<TaskDto> taskDtoList = new ArrayList<>();
        for (UserTask userTask : userTasksByPerson) {
            Optional<Task> taskOpt = taskRepository.findById(userTask.getUserTaskKey().getIdTask());
            if (taskOpt.isPresent()) {
                Task task = taskOpt.get();
                TaskDto taskDto = taskMapper.toDto(task);
                taskDtoList.add(taskDto);
            }
        }

        return taskDtoList;
    }


    //retourne la liste de toutes les entrées dans UserTask
    private List<UserTask> findAllUserTask() {
        return userTaskRepository.findAll();
    }

    public List<Person> getPersonsByIdTask(Long idTask) {
        List<UserTask> userTasks = userTaskRepository.findByIdTask(idTask);
        List<Person> persons = new ArrayList<>();
        for (UserTask userTask : userTasks) {
            persons.add(userTask.getPerson());
        }
        return persons;
    }


    // Ajouter des HiddenUserTasks pour une tâche
    public void addHiddenUsersForTask(List<UserTaskDto> hiddenUserTaskDtos) {
        List<UserTask> hiddenUserTasks = hiddenUserTaskDtos.stream()
                .map(dto -> UserTaskMapper.toEntity(dto, personService, taskService))
                .collect(Collectors.toList());

        userTaskRepository.saveAll(hiddenUserTasks);
    }

    // Modifier pour qui cette tâche est cachée
    public void updateHiddenUsersForTask(Long idTask, List<UserTaskDto> hiddenUserTaskDtos) {
       // Supprimer tous les anciens enregistrements pour cette tâche
       userTaskRepository.deleteByIdTask(idTask);

       // Ajouter les nouveaux
       List<UserTask> newHiddenUserTasks = hiddenUserTaskDtos.stream()
                .filter(dto -> dto.getIdTask().equals(idTask))
               .map(dto -> UserTaskMapper.toEntity(dto, personService, taskService))
               .collect(Collectors.toList());

        if (!newHiddenUserTasks.isEmpty()) {
           userTaskRepository.saveAll(newHiddenUserTasks);
       }
   }
}
