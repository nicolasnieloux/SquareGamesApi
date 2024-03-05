package fr.le_campus_numerique.squaregamesapi.user;

import jakarta.persistence.*;

@Entity
@Table(name = "Player")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id_db;
    private String id;

    private String name;




    public Integer getId_db() {
        return id_db;
    }

    public void setId_db(Integer id_db) {
        this.id_db = id_db;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public User() {

    }
    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(Integer id_db, String id, String name) {
        this.id_db = id_db;
        this.id = id;
        this.name = name;
    }

}
