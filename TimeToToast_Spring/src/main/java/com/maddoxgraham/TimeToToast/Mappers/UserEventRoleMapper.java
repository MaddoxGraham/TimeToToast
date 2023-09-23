package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.UserEventRoleDto;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Services.EventService;
import com.maddoxgraham.TimeToToast.Services.PersonService;
import org.springframework.stereotype.Component;

@Component
public class UserEventRoleMapper {

    public static UserEventRole toEntity(UserEventRoleDto dto, PersonService personService, EventService eventService) {
        UserEventKey key = new UserEventKey(dto.getIdPerson(), dto.getIdEvent());
        Person person = personService.findPersonByIdPerson(dto.getIdPerson());
        Event event = eventService.findEventByIdEvent(dto.getIdEvent());
        return new UserEventRole(key, person, event, dto.getRole());
    }

    public static UserEventRoleDto toDTO(UserEventRole entity) {
        return new UserEventRoleDto(
                entity.getPerson().getIdPerson(),
                entity.getEvent().getIdEvent(),
                entity.getRole()
        );
    }

}
