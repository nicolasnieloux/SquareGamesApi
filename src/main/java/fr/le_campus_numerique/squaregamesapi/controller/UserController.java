package fr.le_campus_numerique.squaregamesapi.controller;

import fr.le_campus_numerique.squaregamesapi.dao.MySQLUserDAO;
import fr.le_campus_numerique.squaregamesapi.dto.UserCreationParams;
import fr.le_campus_numerique.squaregamesapi.dto.UserDTO;
import fr.le_campus_numerique.squaregamesapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    MySQLUserDAO mySQLUserDAO;

//    @Autowired
//    private List<User> userList;

    private UserDTO userToDto(User entry) {
        return new UserDTO(entry.getId(), entry.getName());
    }

    private List<UserDTO> DtoToListUser(Collection<User> users) {
        return users.stream()
                .map(this::userToDto)
                .toList();
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return DtoToListUser(mySQLUserDAO.getAllUsers());
    }

    @PostMapping("/user")
    public UserDTO addUser(@RequestBody UserCreationParams params) {
        return userToDto(mySQLUserDAO.addUser(params));
    }

    @GetMapping("/user/{userId}")
    public UserDTO getUserById(@PathVariable String userId) {
        return userToDto(mySQLUserDAO.getUserById(userId));
    }

    @DeleteMapping("/users/{userId}")
    public String deleteGame(@PathVariable String userId) {
        return mySQLUserDAO.deleteUser(userId);
    }

    @PutMapping("/user/{userId}")
    public UserDTO updateUser(@PathVariable String userId, @RequestBody User user) {
        return userToDto(mySQLUserDAO.updateUser(userId, user));
    }
}