package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.UserEventRoleDTO;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Services.EventService;
import com.maddoxgraham.TimeToToast.Services.UserService;

public class UserEventRoleMapper {

    public static UserEventRole toEntity(UserEventRoleDTO dto, UserService userService, EventService eventService) {
        UserEventKey key = new UserEventKey(dto.getIdUser(), dto.getIdEvent());
        User user = userService.findUserByIdUser(dto.getIdUser());
        Event event = eventService.findEventByIdEvent(dto.getIdEvent());
        return new UserEventRole(key, user, event, dto.getRole());
    }

    public static UserEventRoleDTO toDTO(UserEventRole entity) {
        return new UserEventRoleDTO(
                entity.getUser().getIdUser(),
                entity.getEvent().getIdEvent(),
                entity.getRole()
        );
    }

}
