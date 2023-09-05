package com.maddoxgraham.TimeToToast.Mappers;


import com.maddoxgraham.TimeToToast.DTOs.HiddenUserTaskDto;
import com.maddoxgraham.TimeToToast.Models.HiddenUserTask;
import com.maddoxgraham.TimeToToast.Models.HiddenUserTaskKey;
import com.maddoxgraham.TimeToToast.Models.Task;
import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Services.TaskService;
import com.maddoxgraham.TimeToToast.Services.UserService;
import org.springframework.stereotype.Component;

@Component
public class HiddenUserTaskMapper {

    public static HiddenUserTask toEntity(HiddenUserTaskDto dto, UserService userService, TaskService taskService){
        HiddenUserTaskKey key = new HiddenUserTaskKey(dto.getIdUser(), dto.getIdTask());
        User user = userService.findUserByIdUser(dto.getIdUser());
        Task task = taskService.findTaskByIdTask(dto.getIdTask());
        return new HiddenUserTask(key,user,task);
    }

    public static HiddenUserTaskDto toDto(HiddenUserTask entity) {
        return  new HiddenUserTaskDto(
                entity.getUser().getIdUser(),
                entity.getTask().getIdTask()
        );
    }
}
