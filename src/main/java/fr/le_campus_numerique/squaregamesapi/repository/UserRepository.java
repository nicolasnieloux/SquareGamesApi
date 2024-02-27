package fr.le_campus_numerique.squaregamesapi.repository;

import fr.le_campus_numerique.squaregamesapi.user.User2;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User2, Integer> {
}
