package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.CredentialsDto;
import com.maddoxgraham.TimeToToast.DTOs.UserDTo;
import com.maddoxgraham.TimeToToast.DTOs.SignUpDto;
import com.maddoxgraham.TimeToToast.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private  final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTo> login(@RequestBody CredentialsDto credentialsDto){
        UserDTo user = userService.login(credentialsDto);
       return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTo> register(@RequestBody SignUpDto signUpDto){
        UserDTo user = userService.register(signUpDto);
        return ResponseEntity.created(URI.create("/users/" + user.getIdUser())).body(user);
    }
}
