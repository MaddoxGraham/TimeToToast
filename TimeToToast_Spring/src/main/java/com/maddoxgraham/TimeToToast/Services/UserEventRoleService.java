package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.DTOs.UserDto;
import com.maddoxgraham.TimeToToast.DTOs.UserEventRoleDTO;
import com.maddoxgraham.TimeToToast.Mappers.UserEventRoleMapper;
import com.maddoxgraham.TimeToToast.Models.Event;
import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.TimeToToastApplication;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Repository.UserEventRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserEventRoleService {
    private final UserEventRoleRepository userEventRoleRepository;
    private  final UserService userService;
    private final EventService eventService;

    @Autowired
    public UserEventRoleService(EventService eventService,UserService userService,UserEventRoleRepository userEventRoleRepository) {
        this.userEventRoleRepository = userEventRoleRepository;
        this.userService = userService;
        this.eventService=eventService;
    }

    public UserEventRole addUserEventRole(UserEventRoleDTO userEventRoleDTO){
            UserEventRole userEventRole = UserEventRoleMapper.toEntity(userEventRoleDTO, userService, eventService);
            return userEventRoleRepository.save(userEventRole);
        }


    public List<UserEventRole> findEventsByUserId(Long userId) {
        List<UserEventRole> allUserEventRoles = findAllUserEventRoles();

        // Filtrer les UserEventRole en fonction de l'ID de l'utilisateur
        List<UserEventRole> userEventRolesForUser = allUserEventRoles.stream()
                .filter(userEventRole -> userEventRole.getUserEventKey().getIdUser().equals(userId))
                .collect(Collectors.toList());
        return userEventRolesForUser;
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