package com.maddoxgraham.TimeToToast.Mappers;


import com.maddoxgraham.TimeToToast.DTOs.TaskDto;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Models.Task;
import com.maddoxgraham.TimeToToast.Repository.EventRepository;
import com.maddoxgraham.TimeToToast.Repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
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

    public static Task toEntity(TaskDto dto, Person person, Event event) {
        if (dto == null) {
            return null;
        }
        Task task = new Task();
        task.setIdTask(dto.getIdTask());
        task.setDateTask(dto.getDateTask());
        task.setUrgence(dto.getUrgence());
        task.setDescription(dto.getDescription());
        task.setCreator(person);
        task.setEvent(event);
        return task;
    }


}
