package com.maddoxgraham.TimeToToast.Mappers;


import com.maddoxgraham.TimeToToast.DTOs.TaskDto;
import com.maddoxgraham.TimeToToast.Models.Task;


public class TaskMapper {

    public static TaskDto toDto(Task task) {
        if (task == null){
            return null;
        }
        TaskDto dto = new TaskDto();
        dto.setIdTask(task.getIdTask());
        dto.setDateTask(task.getDateTask());
        dto.setEvent(task.getEvent().getIdEvent());
        dto.setCreator(task.getCreator().getIdPerson());
        dto.setAssignee(task.getAssignee().getIdPerson());
        dto.setUrgence(task.getUrgence());
        dto.setDescription(task.getDescription());
       return dto;
    }

    public static Task toEntity(TaskDto dto) {
        if (dto == null ){
            return null;
        }
        Task task = new Task();
        task.setIdTask(dto.getIdTask());
        task.setDateTask(dto.getDateTask());
        task.setEvent();
        task.setCreator(dto.getCreator().getIdPerson());
        task.setAssignee(dto.getAssignee().getIdPerson());
        task.setUrgence(dto.getUrgence());
        task.setDescription(dto.getDescription());

//           public static UserEventRole toEntity(UserEventRoleDto dto, PersonService personService, EventService eventService) {
//        UserEventKey key = new UserEventKey(dto.getIdPerson(), dto.getIdEvent());
//        Person person = personService.findPersonByIdPerson(dto.getIdPerson());
//        Event event = eventService.findEventByIdEvent(dto.getIdEvent());
//        return new UserEventRole(key, person, event, dto.getRole());
//    }
    }


}
