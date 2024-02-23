package fr.le_campus_numerique.squaregamesapi.dao;


import fr.le_campus_numerique.squaregamesapi.dto.UserCreationParams;
import fr.le_campus_numerique.squaregamesapi.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MySQLUserDAO implements UserDAO {

    private List<User> userList = new ArrayList<>();

    @Override
    public List<User> getAllUsers() {

        return userList;
    }

    @Override
    public User getUserById(int id) {
        for (User u : userList) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    @Override
    public User addUser(UserCreationParams params) {
        User newUser = new User(params.getId(), params.getName());
        userList.add(newUser);
        return newUser;
    }

    @Override
    public User updateUser(User user) {
        for (User u : userList) {
            if (u.getId() == user.getId()) {
                userList.set(userList.indexOf(u), user);

                return user;
            }

        }return null;
    }

    @Override
    public String deleteUser(int id) {
        for (User u : userList) {
            if (u.getId() == id) {
                userList.remove(u);
                return "L'utilisateur a été supprimé";
            }
        }
        return null;
    }
}
