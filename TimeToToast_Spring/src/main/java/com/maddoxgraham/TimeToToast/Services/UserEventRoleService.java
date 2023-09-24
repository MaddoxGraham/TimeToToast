package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.EventDto;
import com.maddoxgraham.TimeToToast.DTOs.PersonDto;
import com.maddoxgraham.TimeToToast.DTOs.UserEventRoleDto;
import com.maddoxgraham.TimeToToast.DTOs.UserEventsDto;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Mappers.EventMapper;
import com.maddoxgraham.TimeToToast.Mappers.PersonMapper;
import com.maddoxgraham.TimeToToast.Mappers.UserEventRoleMapper;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Repository.UserEventRoleRepository;
import com.maddoxgraham.TimeToToast.Repository.EventRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserEventRoleService {

    private final UserEventRoleRepository userEventRoleRepository;
    private final EventService eventService;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final PersonService personService;
    private final PersonMapper personMapper;


    //Add Relation between persons and Event
    public UserEventRole addUserEventRole(UserEventRoleDto userEventRoleDto){
        UserEventRole userEventRole = UserEventRoleMapper.toEntity(userEventRoleDto, personService, eventService);
            return userEventRoleRepository.save(userEventRole);
    }

    // Find event by person id.
    public List<UserEventsDto> findEventsByPersonId(Long idPerson) {
        List<UserEventRole> allUserEventRoles = findAllUserEventRoles();
        List<UserEventRole> userEventRolesForUser = allUserEventRoles.stream()
                .filter(userEventRole -> userEventRole.getUserEventKey().getIdPerson().equals(idPerson))
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
                            .idPerson(idPerson)
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

    // find Persons's List by idEvent
    public List<UserEventsDto> findUsersByEventId(Long eventId) {
        List<UserEventRole> allUserEventRoles = findAllUserEventRoles();
        List<UserEventRole> userEventRolesForEvent = allUserEventRoles.stream()
                .filter(userEventRole -> userEventRole.getUserEventKey().getIdEvent().equals(eventId))
                .collect(Collectors.toList());

        Map<String, UserEventsDto> roleToUserEventsDtoMap = new HashMap<>();
        List<UserEventsDto> userEventsDtoList = new ArrayList<>();

        for (UserEventRole userEventRole : userEventRolesForEvent) {
            Optional<Person> personOpt = Optional.ofNullable(personService.findPersonByIdPerson(userEventRole.getUserEventKey().getIdPerson()));
            if (personOpt.isPresent()) {
                Person person = personOpt.get();
                PersonDto personDto = personMapper.toPersonDto(person); // Assurez-vous que userMapper est injecté ou accessible

                UserEventsDto userEventsDto = roleToUserEventsDtoMap.get(userEventRole.getRole());
                if (userEventsDto == null) {
                    userEventsDto = UserEventsDto.builder()
                            .idPerson(person.getIdPerson())
                            .role(userEventRole.getRole())
                            .persons(new PersonDto[]{personDto})
                            .build();
                    roleToUserEventsDtoMap.put(userEventRole.getRole(), userEventsDto);
                    userEventsDtoList.add(userEventsDto);
                } else {
                    PersonDto[] existingUsers = userEventsDto.getPersons();
                    PersonDto[] updatedUsers = Arrays.copyOf(existingUsers, existingUsers.length + 1);
                    updatedUsers[existingUsers.length] = personDto;
                    userEventsDto.setPersons(updatedUsers);
                }
            }
        }
        return userEventsDtoList;
    }

    // get role
    public UserEventRole findRoleByEventAndUser(Long idPerson, Long idEvent) {
        UserEventKey key = new UserEventKey(idPerson, idEvent);
        Optional<UserEventRole> optionalRole = userEventRoleRepository.findById(key);
        return optionalRole.orElse(null);
    }

    // Liste complète des UserEventRole
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

    public boolean deleteUserEventRole(UserEventRoleDto dto, PersonService personService, EventService eventService) {
        UserEventRole userEventRole = UserEventRoleMapper.toEntity(dto, personService, eventService);
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