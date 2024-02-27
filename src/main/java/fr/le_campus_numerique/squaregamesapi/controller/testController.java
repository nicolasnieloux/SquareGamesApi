package fr.le_campus_numerique.squaregamesapi.controller;

import fr.le_campus_numerique.squaregamesapi.dao.SqlUserDao;
import fr.le_campus_numerique.squaregamesapi.dao.UserDAO;
import fr.le_campus_numerique.squaregamesapi.dto.UserCreationParams;
import fr.le_campus_numerique.squaregamesapi.dto.UserDTO;
import fr.le_campus_numerique.squaregamesapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@RestController
public class testController {

    @Autowired
    private UserDAO sqlUserDao;

    private UserDTO userToDto(User entry) {
        return new UserDTO(entry.getId(), entry.getName());
    }

    private List<UserDTO> DtoToListUser(Collection<User> users) {
        return users.stream()
                .map(this::userToDto)
                .toList();
    }

    @GetMapping("/test")
    public List<UserDTO> findAll() throws SQLException {
//
        return DtoToListUser(sqlUserDao.getAllUsers());
    }


    @PostMapping("/test")
    public UserDTO addUser(@RequestBody UserCreationParams params) throws SQLException {
        return userToDto(sqlUserDao.addUser(params));
    }

    @GetMapping("/test/{userId}")
    public UserDTO getUserById(@PathVariable String userId) {
        return userToDto(sqlUserDao.getUserById(userId));
    }
}
