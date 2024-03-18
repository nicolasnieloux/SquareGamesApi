package fr.le_campus_numerique.squaregamesapi.controller;

import fr.le_campus_numerique.squaregamesapi.SquareGamesApiApplication;
import fr.le_campus_numerique.squaregamesapi.dao.MySQLUserDAO;
import fr.le_campus_numerique.squaregamesapi.dao.UserDAO;
import fr.le_campus_numerique.squaregamesapi.dto.UserCreationParams;
import fr.le_campus_numerique.squaregamesapi.dto.UserDTO;
import fr.le_campus_numerique.squaregamesapi.repository.UserRepository;
import fr.le_campus_numerique.squaregamesapi.user.User;
import fr.le_campus_numerique.squaregamesapi.user.User2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;

    Logger log = LoggerFactory.getLogger(getClass());
    private UserDTO userToDto(User entry) {
        return entry !=null?new UserDTO(entry.getId(), entry.getName()):null;
    }

    private List<UserDTO> DtoToListUser(Collection<User> users) {
        return users.stream()
                .map(this::userToDto)
                .toList();
    }

    @GetMapping("/user")
    public List<UserDTO> getUsers() {

        List<User> userList = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            userList.add(user);
        }
        return DtoToListUser(userList);
    }

    @PostMapping(path = "/user/add")
    public @ResponseBody UserDTO addNewUser(@RequestParam String name, @RequestParam String id) {
        // Utilisez le logger pour enregistrer des informations ou des erreurs
        log.info("Tentative d'ajout d'un nouvel utilisateur.");
        try {
            // Créez un nouvel utilisateur
            User newUser = new User();
            newUser.setName(name);
            newUser.setId(id);

            // Enregistrez le nouvel utilisateur dans la base de données
            User savedUser = userRepository.save(newUser);

            // Retournez le DTO de l'utilisateur enregistré
            return userToDto(savedUser);
        } catch (Exception e) {
            // S'il y a une exception, enregistrez-la dans le journal des erreurs
            log.error("Erreur lors de l'ajout d'un nouvel utilisateur : {}", e.getMessage());
            // Vous pouvez également renvoyer un message d'erreur ou une réponse appropriée
            throw new RuntimeException("Impossible d'ajouter un nouvel utilisateur. Veuillez réessayer plus tard.");
        }
    }

//    @PostMapping("/user")
//    public UserDTO addUser(@RequestBody UserCreationParams params) throws SQLException {
//        return userToDto(userDAO.addUser(params));
//    }


    @GetMapping("/user/{userId}")
    public UserDTO getUserById(@PathVariable int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            return userToDto(optionalUser.get());
        } else {
            log.error("L'identifiant " + userId + " n'existe pas dans la base de données.", userId);
            return null; // Ou renvoyez un message d'erreur approprié selon vos besoins
        }
    }


//    @GetMapping("/user/{userId}")
//    public UserDTO getUserById(@PathVariable String userId) {
//        return userToDto(userDAO.getUserById(userId));
//    }
//
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser (@PathVariable int userId){
       if (userRepository.findById(userId).isPresent()){
           userRepository.deleteById(userId);
           return ResponseEntity.ok("used " + userId + " is deleted") ;
       }
       return ResponseEntity.ok("no user for this " + userId);
    }

//    @DeleteMapping("/users/{userId}")
//    public String deleteGame(@PathVariable String userId) {
//        return userDAO.deleteUser(userId);
//    }
//

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int userId, @RequestBody User user){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User newUser = userOptional.get();
        newUser.setName(user.getName());
        newUser.setId(user.getId());
        userRepository.save(newUser);
        return ResponseEntity.ok(userToDto(newUser));
    }

//    @PutMapping("/user/{userId}")
//    public UserDTO updateUser(@PathVariable String userId, @RequestBody User user) {
//        return userToDto(userDAO.updateUser(userId, user));
//    }

    @GetMapping("/user/{name}")
    public UserDTO findByUsername(@PathVariable String username){

        return null;
    }

    @GetMapping("/login")
    public String loginEndpoint() {
        return "Login!";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Admin!";
    }

    }