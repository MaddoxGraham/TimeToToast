package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.CredentialsDto;
import com.maddoxgraham.TimeToToast.DTOs.UserDTo;
import com.maddoxgraham.TimeToToast.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private  final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTo> login(@RequestBody CredentialsDto credentialsDto){
        UserDTo user = userService.login(credentialsDto);
       return ResponseEntity.ok(user);
    }
}
