//package com.maddoxgraham.TimeToToast.Controllers;
//
//import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
//import com.maddoxgraham.TimeToToast.DTOs.GuestDto;
//import com.maddoxgraham.TimeToToast.Models.Guest;
//import com.maddoxgraham.TimeToToast.Services.GuestService;
//import jakarta.mail.MessagingException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/guest")
//public class GuestController {
//
//    private final GuestService guestService;
//    private final UserAuthProvider userAuthProvider;
//
//    public GuestController(GuestService guestService, UserAuthProvider userAuthProvider) {
//        this.guestService = guestService;
//        this.userAuthProvider = userAuthProvider;
//    }
//
//    @PostMapping("/verifyGuest")
//    public ResponseEntity<GuestDto> verifyGuest(@RequestBody String token){
//        String email = userAuthProvider.verifyGuest(token);
//        GuestDto guestDto = guestService.verifyGuest(email);
//        return new ResponseEntity<>(guestDto, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{idGuest}")
//    public ResponseEntity<?> deleteGuest(@PathVariable Long idGuest){
//        guestService.deleteGuest(idGuest);
//        String message = "Guest supprimé";
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }
//
//    @PutMapping("/addDetailsToGuest/{idGuest}")
//    public ResponseEntity<GuestDto> addDetailsToGuest(@PathVariable Long idGuest, @RequestBody GuestDto guestDetailsDto) {
//        GuestDto guestDto = guestService.addDetailsToGuest(idGuest, guestDetailsDto.getFirstName(), guestDetailsDto.getLastName());
//        return new ResponseEntity<>(guestDto, HttpStatus.OK);
//    }
//
//    @GetMapping("/getGuestByMail")
//    public ResponseEntity getGuestByMail(@RequestBody GuestDto guestDto) throws MessagingException {
//        GuestDto guest = guestService.findByEmail(guestDto.getEmail());
//        return new ResponseEntity<>(guest, HttpStatus.OK);
//    }
//
//    @GetMapping("getEventGuests/{idEvent}")
//    public  ResponseEntity<List<Guest>> getEventGuests(@PathVariable Long idEvent){
//    List<Guest> guests = guestService.findGuestByEvent(idEvent);
//    return new ResponseEntity<>(guests,HttpStatus.OK);
//    }
//
//}
