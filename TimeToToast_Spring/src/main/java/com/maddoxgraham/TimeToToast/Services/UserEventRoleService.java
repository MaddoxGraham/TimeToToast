package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.EventDto;
import com.maddoxgraham.TimeToToast.DTOs.UserDto;
import com.maddoxgraham.TimeToToast.DTOs.UserEventRoleDTO;
import com.maddoxgraham.TimeToToast.DTOs.UserEventsDto;
import com.maddoxgraham.TimeToToast.Mappers.EventMapper;
import com.maddoxgraham.TimeToToast.Mappers.UserEventRoleMapper;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.Repository.EventRepository;
import com.maddoxgraham.TimeToToast.TimeToToastApplication;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Repository.UserEventRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserEventRoleService {
    private final UserEventRoleRepository userEventRoleRepository;
    private  final UserService userService;
    private final EventService eventService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public UserEventRole addUserEventRole(UserEventRoleDTO userEventRoleDTO){
            UserEventRole userEventRole = UserEventRoleMapper.toEntity(userEventRoleDTO, userService, eventService);
            return userEventRoleRepository.save(userEventRole);
        }


    public List<UserEventsDto> findEventsByUserId(Long userId) {
        List<UserEventRole> allUserEventRoles = findAllUserEventRoles();
        List<UserEventRole> userEventRolesForUser = allUserEventRoles.stream()
                .filter(userEventRole -> userEventRole.getUserEventKey().getIdUser().equals(userId))
                .collect(Collectors.toList());

        Map<String, UserEventsDto> roleToUserEventsDtoMap = new HashMap<>();
        List<UserEventsDto> userEventsDtoList = new ArrayList<>();

        for (UserEventRole userEventRole: userEventRolesForUser){
            Optional<Event> eventOpt = eventRepository.findById(userEventRole.getEvent().getIdEvent());
            if(eventOpt.isPresent()){
                Event event = eventOpt.get();
                EventDto eventDto = eventMapper.toDto(event);

                UserEventsDto userEventsDto = roleToUserEventsDtoMap.get(userEventRole.getRole());
                if(userEventsDto == null) {
                    userEventsDto = UserEventsDto.builder()
                            .userId(userId)
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

    public UserEventRole findRoleByEventAndUser(Long idUser, Long idEvent) {
        UserEventKey key = new UserEventKey(idUser, idEvent);
        Optional<UserEventRole> optionalRole = userEventRoleRepository.findById(key);
        return optionalRole.orElse(null);
    }


    public List<UserEventRole> findAllUserEventRoles(){
        return userEventRoleRepository.findAll();
    }

    public UserEventRole updateUserEventRole(UserEventRole userEventRole){
        return userEventRoleRepository.save(userEventRole);
    }

    public UserEventRole findByUserEventKey(UserEventKey userEventKey){
        return userEventRoleRepository.findByUserEventKey(userEventKey)
                .orElseThrow(() -> new UserNotFoundException("UserEventRole with key " + userEventKey + " was not found"));
    }

    public boolean deleteUserEventRole(UserEventRoleDTO dto, UserService userService, EventService eventService) {
        UserEventRole userEventRole = UserEventRoleMapper.toEntity(dto, userService, eventService);
        UserEventKey key = userEventRole.getUserEventKey();
        if (userEventRoleRepository.existsById(key)) {
            userEventRoleRepository.deleteById(key);
            return true;
        } else {
            return false;
        }
    }

    public UserEventRole findUserEventRoleByIdUserEventRole(UserEventKey userEventKey) {
        Optional<UserEventRole> optionalUserEventRole = userEventRoleRepository.findById(userEventKey);
        if (optionalUserEventRole.isPresent()) {
            return optionalUserEventRole.get();
        } else {
            throw new UserNotFoundException("No relation between the Event and the user were found with the given ID");
        }
    }


}