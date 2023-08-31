package com.maddoxgraham.TimeToToast.Controllers;


import com.maddoxgraham.TimeToToast.DTOs.UserEventRoleDTO;
import com.maddoxgraham.TimeToToast.DTOs.UserEventsDto;
import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Models.UserEventKey;
import com.maddoxgraham.TimeToToast.Models.UserEventRole;
import com.maddoxgraham.TimeToToast.Services.EventService;
import com.maddoxgraham.TimeToToast.Services.UserEventRoleService;
import com.maddoxgraham.TimeToToast.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userEventRole")
@AllArgsConstructor
public class UserEventRoleController {

    private final UserEventRoleService userEventRoleService;
    private final UserService userService;
    private final EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<List<UserEventRole>> getAllUserEventRole() {
        List<UserEventRole> userEventRoles = userEventRoleService.findAllUserEventRoles();
        return new ResponseEntity<>(userEventRoles, HttpStatus.OK);
    }

    @GetMapping("/find/{idUser}")
    public ResponseEntity<List<UserEventsDto>> findEventsByUserId(@PathVariable("idUser") Long idUser) {
        List<UserEventsDto> UserEventsDtoList = userEventRoleService.findEventsByUserId(idUser);
        return new ResponseEntity<>(UserEventsDtoList, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<UserEventRole> addUserEventRole(@RequestBody UserEventRoleDTO userEventRoleDTO){
        UserEventRole newUserEventRole = userEventRoleService.addUserEventRole(userEventRoleDTO);
        return new ResponseEntity<>(newUserEventRole, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<UserEventRole> updateUserEventRole(@RequestBody UserEventRole userEventRole){
        UserEventRole updateUserEventRole = userEventRoleService.updateUserEventRole(userEventRole);
        return new ResponseEntity<>(updateUserEventRole, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUserEventRole(@RequestBody UserEventRoleDTO dto) {
        if (userEventRoleService.deleteUserEventRole(dto, userService, eventService)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}