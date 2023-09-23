package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.UserTaskDto;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Models.Task;
import com.maddoxgraham.TimeToToast.Models.UserTask;
import com.maddoxgraham.TimeToToast.Models.UserTaskKey;
import com.maddoxgraham.TimeToToast.Services.PersonService;
import com.maddoxgraham.TimeToToast.Services.TaskService;

public class UserTaskMapper {

    // Convertir DTO en entité
    public static UserTask toEntity(UserTaskDto dto, PersonService personService, TaskService taskService) {
        UserTask userTask = new UserTask();
        UserTaskKey key = new UserTaskKey(dto.getIdPerson(), dto.getIdTask());
        userTask.setUserTaskKey(key);

        Person person = personService.findPersonByIdPerson(dto.getIdPerson());
        Task task = taskService.findTaskByIdTask(dto.getIdTask());

       userTask.setPerson(person);
       userTask.setTask(task);
       userTask.setIsInvisible(dto.getIsInvisible());

        return userTask;
    }

   //  Convertir entité en DTO
   public static UserTaskDto toDto(UserTask entity) {
       UserTaskDto dto = new UserTaskDto();

       // Déconstruire la clé composite et définir ses propriétés dans le DTO
       UserTaskKey key = entity.getUserTaskKey();
       dto.setIdPerson(key.getIdPerson());
       dto.setIdTask(key.getIdTask());

       dto.setIsInvisible(entity.getIsInvisible());

       return dto;
   }
}