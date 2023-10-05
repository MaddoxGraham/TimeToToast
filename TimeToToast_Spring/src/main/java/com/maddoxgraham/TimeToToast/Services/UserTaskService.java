package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.*;
import com.maddoxgraham.TimeToToast.Mappers.TaskMapper;
import com.maddoxgraham.TimeToToast.Mappers.UserTaskMapper;
import com.maddoxgraham.TimeToToast.Models.*;
import com.maddoxgraham.TimeToToast.Repository.PersonRepository;
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
    private final PersonRepository personRepository;
    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserTaskMapper userTaskMapper;

    public List<TaskDto> getTasksByIdPerson(Long idPerson) {
        List<UserTask> userTasksByPerson = userTaskRepository.findByUserTaskKey_IdPerson(idPerson);
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
        List<UserTask> userTasks = userTaskRepository.findByUserTaskKey_IdTask(idTask);
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
       userTaskRepository.deleteByUserTaskKey_IdTask(idTask);

       // Ajouter les nouveaux
       List<UserTask> newHiddenUserTasks = hiddenUserTaskDtos.stream()
                .filter(dto -> dto.getIdTask().equals(idTask))
               .map(dto -> UserTaskMapper.toEntity(dto, personService, taskService))
               .collect(Collectors.toList());

        if (!newHiddenUserTasks.isEmpty()) {
           userTaskRepository.saveAll(newHiddenUserTasks);
       }
   }

    public TaskDto addUserTask(NewTaskDto newTaskDto, Task task) {
        for(Long assignee: newTaskDto.getAssignee()){
            UserTaskDto userTaskDto = new UserTaskDto();
            userTaskDto.setIdTask(task.getIdTask());
            userTaskDto.setIdPerson(assignee);
            userTaskDto.setIsInvisible(false);
            UserTask userTask = userTaskMapper.toEntity(userTaskDto, personService, taskService);
            userTaskRepository.save(userTask);
        }
        for(Long invisibleTo: newTaskDto.getInvisibleTo()){
            UserTaskDto userTaskDto = new UserTaskDto();
            userTaskDto.setIdTask(task.getIdTask());
            userTaskDto.setIdPerson(invisibleTo);
            userTaskDto.setIsInvisible(true);
            UserTask userTask = userTaskMapper.toEntity(userTaskDto, personService, taskService);
            userTaskRepository.save(userTask);
        }
        return taskMapper.toDto(task);
    }

    public void addTaskAssignee(Long idTask, Long idPerson) {
        UserTaskDto userTaskDto = new UserTaskDto();
        userTaskDto.setIdTask(idTask);
        userTaskDto.setIdPerson(idPerson);
        userTaskDto.setIsInvisible(false);
        UserTask userTask = userTaskMapper.toEntity(userTaskDto, personService, taskService);
        userTaskRepository.save(userTask);

    }

    public void removeTaskAssignee(Long idTask, Long idPerson) {
        Optional<UserTask> userTaskOpt = userTaskRepository.findByUserTaskKey_IdTaskAndUserTaskKey_IdPerson(idTask, idPerson);
        if(userTaskOpt.isPresent()){
            UserTask userTask = userTaskOpt.get();
            userTaskRepository.delete(userTask);
        }
    }

}
