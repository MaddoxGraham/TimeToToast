package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.DTOs.PersonDto;
import com.maddoxgraham.TimeToToast.Models.Person;
import com.maddoxgraham.TimeToToast.Services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

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

    @PostMapping("/update/{idUser}")
    public ResponseEntity<PersonDto> updateUser(@PathVariable("idUser") Long idUser, @RequestBody PersonDto person){

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
