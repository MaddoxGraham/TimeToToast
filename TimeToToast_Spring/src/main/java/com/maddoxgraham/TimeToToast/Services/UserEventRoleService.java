package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.TimeToToastApplication;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Repository.UserEventRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEventRoleService {
    private final UserEventRoleRepository userEventRoleRepository;

    @Autowired
    public UserEventRoleService(UserEventRoleRepository userEventRoleRepository) {
        this.userEventRoleRepository = userEventRoleRepository;
    }

    public UserEventRole addUserEventRole(UserEventRole userEventRole){
        return userEventRoleRepository.save(userEventRole);
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

    public void deleteUser(UserEventKey userEventKey){
        Optional<UserEventRole> optionalUserEventRole = userEventRoleRepository.findById(userEventKey);

        if(optionalUserEventRole.isPresent()){
            userEventRoleRepository.deleteById(userEventKey);
        } else {
            throw new RuntimeException("This user and Event does not exists. : " + userEventKey);
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