package com.maddoxgraham.TimeToToast.Mappers;

//import com.maddoxgraham.TimeToToast.DTOs.HiddenUserTaskDto;
//import com.maddoxgraham.TimeToToast.Models.*;
//import com.maddoxgraham.TimeToToast.Services.GuestService;
//import com.maddoxgraham.TimeToToast.Services.TaskService;
//import com.maddoxgraham.TimeToToast.Services.UserService;
public class UserTaskMapper {

    // Convertir DTO en entité
//    public static HiddenUserTask toEntity(HiddenUserTaskDto dto, UserService userService, TaskService taskService, GuestService guestService) {
//        HiddenUserTask hiddenUserTask = new HiddenUserTask();
//
//        hiddenUserTask.setHiddenUserTaskKey(dto.getHiddenUserKey());
//
//        // Récupération des objets User, Task et Guest en utilisant les services
//        User user = userService.findUserByIdUser(dto.getIdUser());
//        Task task = taskService.findTaskByIdTask(dto.getIdTask());
//        Guest guest = guestService.findGuestByIdGuest(dto.getIdGuest());
//
//        hiddenUserTask.setUser(user);
//        hiddenUserTask.setTask(task);
//        hiddenUserTask.setGuest(guest);
//
//        return hiddenUserTask;
//    }

    // Convertir entité en DTO
//    public static HiddenUserTaskDto toDto(HiddenUserTask entity) {
//        HiddenUserTaskDto dto = new HiddenUserTaskDto();
//
//        dto.setHiddenUserKey(entity.getHiddenUserTaskKey());
//
//        if (entity.getUser() != null) {
//            dto.setIdUser(entity.getUser().getIdUser());
//        }
//
//        if (entity.getTask() != null) {
//            dto.setIdTask(entity.getTask().getIdTask());
//        }
//
//        if (entity.getGuest() != null) {
//            dto.setIdGuest(entity.getGuest().getIdGuest());
//        }
//
//        return dto;
//    }
}