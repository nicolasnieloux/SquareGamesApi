package fr.le_campus_numerique.squaregamesapi.dao;

import fr.le_campus_numerique.squaregamesapi.database.DbAccess;
import fr.le_campus_numerique.squaregamesapi.dto.UserCreationParams;
import fr.le_campus_numerique.squaregamesapi.user.User;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class SqlUserDao implements UserDAO {

    @Autowired
    private DbAccess dbAccess;
    private Connection connection;

    @Override
    public List<User> getAllUsers() throws SQLException {


        try {
            connection = this.dbAccess.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM user");

            List<User> userList = new ArrayList<>();

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");

                User user = new User(id, name);
                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;

    }

    @Override
    public User getUserById(String id) {

        try {
            String query = "SELECT name FROM user WHERE id = ? ";
            connection = this.dbAccess.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String userName = result.getString("name");
                return new User(id, userName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public User addUser(UserCreationParams params) throws SQLException {
        try {
            String query = "INSERT INTO user (`id`, `name`) VALUES (?,?)";
            connection = this.dbAccess.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);


            statement.setString(1, params.getId());
            statement.setString(2, params.getName());

            statement.executeUpdate();

            return new User(params.getId(), params.getName());

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return null;
    }

    @Override
    public User updateUser(String id, User user) {
        try {
            String query = "UPDATE user SET name = ? WHERE id = ?";
            connection = this.dbAccess.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, id);
            statement.setString(2, user.getName());

            statement.executeUpdate();

            return new User(id, user.getName());

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return null;
    }

    @Override
    public String deleteUser(String id) {
        return null;
    }
}



