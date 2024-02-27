package fr.le_campus_numerique.squaregamesapi.dao;


import fr.le_campus_numerique.squaregamesapi.dto.UserCreationParams;
import fr.le_campus_numerique.squaregamesapi.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers() throws SQLException;
    public User getUserById(String id);
    public User addUser(UserCreationParams params) throws SQLException;
    public User updateUser(String id, User user);
    public String deleteUser(String id);

}
