package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //On veux une méthode qui retourne tous les employés donc on veux utiliser http c'est à cela que sert ResponseEntity
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{idUser}")
    //pathVariale prend l'idUser envoyé par le mapping puis on spésifie son type
    public ResponseEntity<User> getUserById(@PathVariable("idUser") Long idUser) {
        User user = userService.findUserByIdUser(idUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //add empoyee doit utiliser post car c'est un create
    @PostMapping("/add")
    // ici requestBody permet de récupérer l'employé créer
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.addUser(user);
        // on va cete fois ci retourne un created parce qu'on viens de créer quelque chose sur le serveur
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable("idUser") Long idUser){
        userService.deleteUser(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

