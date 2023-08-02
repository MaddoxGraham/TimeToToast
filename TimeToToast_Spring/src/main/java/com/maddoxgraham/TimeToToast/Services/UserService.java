package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.TimeToToastApplication;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Annoter la classe avec @Service indique que c'est une classe de service Spring.
@Service
public class UserService {

    // UserRepository est une interface qui gère la connexion avec la base de données.
    private final UserRepository userRepository;

    // L'annotation @Autowired permet d'injecter UserRepository dans cette classe.
    // C'est une technique appelée Injection de Dépendance (DI).
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Cette méthode ajoute un nouvel utilisateur à la base de données.
    // Elle reçoit un objet utilisateur et utilise UserRepository pour l'enregistrer.
    public User addUser(User user){
        // Avec un set, je pourrais manuellement ajouter des informations de mon utilisateur ici.
        return userRepository.save(user);
    }

    // Cette méthode retourne une liste de tous les utilisateurs de la base de données.
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    // Cette méthode met à jour les informations d'un utilisateur existant.
    // Elle reçoit un objet utilisateur et utilise UserRepository pour mettre à jour ses données.
    public User updateUser(User user){
        return userRepository.save(user);
    }

    // Cette méthode trouve un utilisateur dans la base de données en utilisant son id.
    // Si l'utilisateur n'est pas trouvé, une exception UserNotFoundException est levée.
    public User findUserByIdUser(Long idUser){
        // Ici, comme on retourne un optional dans UserRepository, on va devoir lui préciser :
        // retourne un utilisateur par son idUser si tu le trouves sinon retourne l'exception UserNotFoundException.
        return userRepository.findUserByIdUser(idUser).orElseThrow(() -> new UserNotFoundException("User n° " + idUser + " was not found"));
    }

    // Cette méthode supprime un utilisateur de la base de données en utilisant son id.
    public void deleteUser(Long idUser){
        userRepository.deleteUserByIdUser(idUser);
    }
}
