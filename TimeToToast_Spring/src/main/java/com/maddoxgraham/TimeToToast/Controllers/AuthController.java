package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
import com.maddoxgraham.TimeToToast.DTOs.*;
import com.maddoxgraham.TimeToToast.Services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private  final PersonService personService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<PersonDto> login(@RequestBody CredentialsDto credentialsDto){
        PersonDto person = personService.login(credentialsDto);
        if(person.getRole().equals("USER")) {
            Map<String, String> tokens = userAuthProvider.createTokens(person);
            person.setToken(tokens.get("accessToken"));
        }
       return ResponseEntity.ok(person);
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDto> register(@RequestBody SignUpDto signUpDto){
        PersonDto personDto = personService.register(signUpDto);
        return ResponseEntity.created(URI.create("/users/" + personDto.getIdPerson())).body(personDto);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody RefreshTokenRequestDto request) {
        String refreshToken = request.getRefreshToken();
        if(refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Refresh token est manquant"));
        }

        String newAuthToken;
        try {
            newAuthToken = userAuthProvider.refreshAccessToken(refreshToken);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Refresh token invalid"));
        }

        Map<String, String> reponse = new HashMap<>();
        reponse.put("token", newAuthToken);
        return ResponseEntity.ok(reponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> tokens) {
        String refreshToken = tokens.get("refreshToken");
        PersonDto personDto = personService.findByRefreshToken(refreshToken);

        if(personDto != null) {
            personService.clearTokens(personDto);
            return ResponseEntity.ok().body("Admin déconnecté avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Invalid");
        }
    }
}
