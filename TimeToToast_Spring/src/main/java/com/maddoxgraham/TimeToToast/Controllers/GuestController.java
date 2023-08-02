package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Models.Guest;
import com.maddoxgraham.TimeToToast.Services.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Guest>> getAllGuest() {
        List<Guest> guests = guestService.findAllGuests();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }

    @GetMapping("/find/{idGuest}")
    public ResponseEntity<Guest> getGuestById(@PathVariable("idGuest") Long idGuest) {
        Guest guest = guestService.findGuestByIdGuest(idGuest);
        return new ResponseEntity<>(guest, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest){
        Guest newGuest = guestService.addGuest(guest);
        return new ResponseEntity<>(newGuest, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Guest> updateGuest(@RequestBody Guest guest){
        Guest updateGuest = guestService.updateGuest(guest);
        return new ResponseEntity<>(updateGuest, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idGuest}")
    public ResponseEntity<?> deleteGuest(@PathVariable("idGuest") Long idGuest){
        guestService.deleteGuest(idGuest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
