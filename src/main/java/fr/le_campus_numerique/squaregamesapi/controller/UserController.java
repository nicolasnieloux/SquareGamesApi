package fr.le_campus_numerique.squaregamesapi.controller;

import fr.le_campus_numerique.squaregamesapi.dao.MySQLUserDAO;
import fr.le_campus_numerique.squaregamesapi.dao.UserDAO;
import fr.le_campus_numerique.squaregamesapi.dto.UserCreationParams;
import fr.le_campus_numerique.squaregamesapi.dto.UserDTO;
import fr.le_campus_numerique.squaregamesapi.repository.UserRepository;
import fr.le_campus_numerique.squaregamesapi.user.User;
import fr.le_campus_numerique.squaregamesapi.user.User2;
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

//    @Autowired
//    private UserDAO userDAO;

    @Autowired
    private UserRepository userRepository;


    private UserDTO userToDto(User entry) {
        return entry !=null?new UserDTO(entry.getId(), entry.getName()):null;
    }

    private List<UserDTO> DtoToListUser(Collection<User> users) {
        return users.stream()
                .map(this::userToDto)
                .toList();
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {

        List<User> userList = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            userList.add(user);
        }
        return DtoToListUser(userList);
    }

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody UserDTO addNewUser(@RequestParam String name
            , @RequestParam String id) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setId(id);

        return userToDto(userRepository.save(n));
    }

//    @PostMapping("/user")
//    public UserDTO addUser(@RequestBody UserCreationParams params) throws SQLException {
//        return userToDto(userDAO.addUser(params));
//    }


    @GetMapping("/user/{userId}")
    public UserDTO getUserById (@PathVariable int userId){

        return userToDto(userRepository.findById(userId).orElse(null));
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


    }