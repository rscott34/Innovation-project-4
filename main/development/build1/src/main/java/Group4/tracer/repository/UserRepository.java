package Group4.tracer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    // Custom method to easily find a user by their username
    Optional<User> findByUsername(String username);
}