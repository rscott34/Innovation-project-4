package Group4.tracer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    // Custom method to easily find a user by their username
    Optional<User> findByUsername(String username);

    //Check if username exists in system
    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.score >= :floor AND u.score < :ceil ORDER BY u.score DESC LIMIT 20")
    List<User> findTop20InRange(@Param("floor") int floor, @Param("ceil") int ceil);

    // Legendary has no upper bound
    @Query("SELECT u FROM User u WHERE u.score >= :floor ORDER BY u.score DESC LIMIT 20")
    List<User> findTop20Legendary(@Param("floor") int floor);
}