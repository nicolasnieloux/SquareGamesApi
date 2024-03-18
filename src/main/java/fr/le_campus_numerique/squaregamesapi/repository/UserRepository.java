package fr.le_campus_numerique.squaregamesapi.repository;

import fr.le_campus_numerique.squaregamesapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String username);
}
