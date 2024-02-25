package fr.le_campus_numerique.squaregamesapi.dao;


import fr.le_campus_numerique.squaregamesapi.dto.UserCreationParams;
import fr.le_campus_numerique.squaregamesapi.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MySQLUserDAO implements UserDAO {

    private List<User> userList = new ArrayList<>();

    private Map<String, User> userMap = new HashMap<>();

    @Override
    public List<User> getAllUsers() {

        return userList;
    }

    @Override
    public User getUserById(String id) {

        if (Integer.parseInt(id) <= userList.size()) {
            for (User u : userList) {
                if (u.getId().equals(id)) {
                    return u;
                }
            }
        }
        return null;
    }

    @Override
    public User addUser(UserCreationParams params) {

        String id = generateUniqueId();
        User newUser = new User(id, params.getName());
        userList.add(newUser);
        return newUser;
    }

    @Override
    public User updateUser(User user) {
        for (User u : userList) {
            if (u.getId().equals(user.getId())) {
                userList.set(userList.indexOf(u), user);
                return user;
            }
        }
        return null;
    }


    public String deleteUser(String id) {
        for (User u : userList) {
            if (u.getId().equals(id)) {
                userList.remove(u);
                return "L'utilisateur a été supprimé";
            }
        }
        return null;
    }

    private String generateUniqueId() {
        if (!userList.isEmpty()) {
            int id = Integer.parseInt(userList.getLast().getId())+1;
            return String.valueOf(id);
        }
        return "1";
    }
}
