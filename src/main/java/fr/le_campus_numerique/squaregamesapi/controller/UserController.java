package fr.le_campus_numerique.squaregamesapi.controller;

import fr.le_campus_numerique.squaregamesapi.dao.MySQLUserDAO;
import fr.le_campus_numerique.squaregamesapi.dto.UserCreationParams;
import fr.le_campus_numerique.squaregamesapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    @Autowired
    MySQLUserDAO mySQLUserDAO;



    @GetMapping("/users")
    public List<User> getAllUsers(){
        return mySQLUserDAO.getAllUsers();
    }

    @PostMapping("/user")
    public User addUser(@RequestBody UserCreationParams params){
        return mySQLUserDAO.addUser(params);
    }
    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable int userId){
        return mySQLUserDAO.getUserById(userId);
    }
    @DeleteMapping("/users/{userId}")
    public String deleteGame(@PathVariable int userId){
       return mySQLUserDAO.deleteUser(userId);
    }

    @PutMapping("/user/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User user){
       return mySQLUserDAO.updateUser(user);
    }

}
