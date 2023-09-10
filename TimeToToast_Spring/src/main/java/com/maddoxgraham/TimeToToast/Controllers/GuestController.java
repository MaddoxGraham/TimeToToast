package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
import com.maddoxgraham.TimeToToast.DTOs.GuestDto;
import com.maddoxgraham.TimeToToast.Services.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;
    private final UserAuthProvider userAuthProvider;

    public GuestController(GuestService guestService, UserAuthProvider userAuthProvider) {
        this.guestService = guestService;
        this.userAuthProvider = userAuthProvider;
    }


    @PostMapping("/verifyGuest")
    public ResponseEntity<GuestDto> verifyGuest(@RequestBody String token){
        String email = userAuthProvider.verifyGuest(token);
        GuestDto guestDto = guestService.verifyGuest(email);
        return new ResponseEntity<>(guestDto, HttpStatus.OK);
    }

}
