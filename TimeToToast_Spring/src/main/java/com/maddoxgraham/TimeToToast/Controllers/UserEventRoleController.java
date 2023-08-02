package com.maddoxgraham.TimeToToast.Controllers;


import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.Services.UserEventRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userEventRole")
public class UserEventRoleController {

    private final UserEventRoleService userEventRoleService;

    public UserEventRoleController(UserEventRoleService userEventRoleService) {
        this.userEventRoleService = userEventRoleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEventRole>> getAllUserEventRole() {
        List<UserEventRole> userEventRoles = userEventRoleService.findAllUserEventRoles();
        return new ResponseEntity<>(userEventRoles, HttpStatus.OK);
    }

    @GetMapping("/find/{idUser}/{idEvent}")
    public ResponseEntity<UserEventRole> getUserEventRoleById(@PathVariable("idUser") Long idUser, @PathVariable("idEvent") Long idEvent) {
        UserEventKey userEventKey = new UserEventKey(idUser, idEvent);
        UserEventRole userEventRole = userEventRoleService.findUserEventRoleByIdUserEventRole(userEventKey);
        return new ResponseEntity<>(userEventRole, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<UserEventRole> addUserEventRole(@RequestBody UserEventRole userEventRole){
        UserEventRole newUserEventRole = userEventRoleService.addUserEventRole(userEventRole);
        return new ResponseEntity<>(newUserEventRole, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserEventRole> updateUserEventRole(@RequestBody UserEventRole userEventRole){
        UserEventRole updateUserEventRole = userEventRoleService.updateUserEventRole(userEventRole);
        return new ResponseEntity<>(updateUserEventRole, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idUser}/{idEvent}")
    public ResponseEntity<?> deleteUserEventRole(@PathVariable("idUser") Long idUser, @PathVariable("idEvent") Long idEvent){
        UserEventKey userEventKey = new UserEventKey(idUser, idEvent);
        userEventRoleService.deleteUser(userEventKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}