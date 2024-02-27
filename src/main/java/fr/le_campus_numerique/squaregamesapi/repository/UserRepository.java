package fr.le_campus_numerique.squaregamesapi.repository;

import fr.le_campus_numerique.squaregamesapi.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
}
