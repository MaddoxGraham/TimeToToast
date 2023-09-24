package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
import com.maddoxgraham.TimeToToast.DTOs.PersonDto;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Repository.PersonRepository;
import com.maddoxgraham.TimeToToast.Services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final UserAuthProvider userAuthProvider;

    // PERSON Methods

    @GetMapping("/find/{idUser}")
    public ResponseEntity<Person> getPersonByIdPerson(@PathVariable("idUser") Long idUser){
        Person person = personService.findPersonByIdPerson(idUser);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    //delete by id
    @GetMapping("/delete/{idUser}")
    public ResponseEntity<?> deletePerson(@PathVariable("idUser") Long idUser){
    Person person = personService.deletePersonByIdPerson(idUser);
    return new ResponseEntity<>(person,HttpStatus.OK);
    }

//    @PostMapping("/update/{idUser}")
//    public ResponseEntity<PersonDto> updateUser(@PathVariable("idUser") Long idUser, @RequestBody PersonDto person){
//
//    }

    @GetMapping("getEventGuests/{idEvent}")
    public  ResponseEntity<List<PersonDto>> getEventGuests(@PathVariable Long idEvent){
    List<PersonDto> guests = personService.findGuestByEvent(idEvent);
    return new ResponseEntity<>(guests,HttpStatus.OK);
    }

    @PostMapping("/verifyGuest")
    public ResponseEntity<PersonDto> verifyGuest(@RequestBody String token){
        String email = userAuthProvider.verifyGuest(token);
        PersonDto guestDto = personService.verifyGuest(email);
        return new ResponseEntity<>(guestDto, HttpStatus.OK);
    }

    @PutMapping("/addDetailsToGuest/{idPerson}")
    public ResponseEntity<PersonDto> addDetailsToGuest(@PathVariable Long idPerson, @RequestBody PersonDto guestDetailsDto) {
        PersonDto personDto = personService.addDetailsToGuest(idPerson, guestDetailsDto.getFirstName(), guestDetailsDto.getLastName());
        return new ResponseEntity<>(personDto, HttpStatus.OK);
    }

    @PutMapping("guestToUser/{idPerson}")
    public ResponseEntity<PersonDto> guestToUser(@PathVariable Long idPerson, @RequestBody PersonDto personDto){
        PersonDto newPersonDto = personService.fromGuestToUser(idPerson, personDto);
        Map<String, String> tokens = userAuthProvider.createTokensForGuestToUser(newPersonDto);
        String refreshToken = tokens.get("refreshToken");
        newPersonDto = personService.addRefreshToken(idPerson, refreshToken);
        newPersonDto.setToken(tokens.get("accessToken"));

        return  new ResponseEntity<>(newPersonDto, HttpStatus.OK);
    }

//    @PutMapping("/update/{idUser}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable("idUser") Long idUser, @RequestBody UserUpdateDto user) {
//        UserDto updateUser = userService.updateUser(idUser, user);
//        return new ResponseEntity<>(updateUser, HttpStatus.OK);
//    }

    //update (attention au role guest et user


    // USER Methods
    // GUEST Methods

    //    @PostMapping("/verifyGuest")
//    public ResponseEntity<GuestDto> verifyGuest(@RequestBody String token){
//        String email = userAuthProvider.verifyGuest(token);
//        GuestDto guestDto = guestService.verifyGuest(email);
//        return new ResponseEntity<>(guestDto, HttpStatus.OK);
//    }

    //get by mail ?


}