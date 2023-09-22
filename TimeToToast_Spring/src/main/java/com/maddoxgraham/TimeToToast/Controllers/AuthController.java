package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
import com.maddoxgraham.TimeToToast.DTOs.CredentialsDto;
import com.maddoxgraham.TimeToToast.DTOs.RefreshTokenRequestDto;
import com.maddoxgraham.TimeToToast.DTOs.UserDto;
import com.maddoxgraham.TimeToToast.DTOs.SignUpDto;
//import com.maddoxgraham.TimeToToast.Services.UserService;
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

//    private  final UserService userService;
//    private final UserAuthProvider userAuthProvider;
//
//    @PostMapping("/login")
//    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
//        UserDto user = userService.login(credentialsDto);
//        Map<String, String> tokens = userAuthProvider.createTokens(user);
//        user.setToken(tokens.get("accessToken"));
//       return ResponseEntity.ok(user);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){
//        UserDto user = userService.register(signUpDto);
//        return ResponseEntity.created(URI.create("/users/" + user.getIdUser())).body(user);
//    }
//
//    @PostMapping("/refresh-token")
//    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody RefreshTokenRequestDto request) {
//        String refreshToken = request.getRefreshToken();
//        if(refreshToken == null || refreshToken.isEmpty()) {
//            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Refresh token est manquant"));
//        }
//
//        String newAuthToken;
//        try {
//            newAuthToken = userAuthProvider.refreshAccessToken(refreshToken);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Refresh token invalid"));
//        }
//
//        Map<String, String> reponse = new HashMap<>();
//        reponse.put("token", newAuthToken);
//        return ResponseEntity.ok(reponse);
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestBody Map<String, String> tokens) {
//        String refreshToken = tokens.get("refreshToken");
//        UserDto user = userService.findByRefreshToken(refreshToken);
//
//        if(user != null) {
//            userService.clearTokens(user);
//            return ResponseEntity.ok().body("Admin déconnecté avec succès");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Invalid");
//        }
//
//    }
}
