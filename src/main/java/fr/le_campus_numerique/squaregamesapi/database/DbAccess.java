package fr.le_campus_numerique.squaregamesapi.database;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class DbAccess {

    private static DbAccess instance;
    private String url;
    private String user;
    private String password;
    private Connection con;

    public static DbAccess getInstance() {
        if (instance == null) instance = new DbAccess();
        return instance;
    }

    private DbAccess() {
        //TODO generate everything you need for db connection
        url = "jdbc:mysql://localhost:6603/squareGames";
        user = "root";
        password = "helloworld";
    }

    public Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(url, user, password);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
}

