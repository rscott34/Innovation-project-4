package Group4.tracer.repository;

import Group4.tracer.model.Verifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VerifierRepository extends CrudRepository<Verifier, String> {
    // Custom method to easily find a verifier by their username
    Optional<Verifier> findByUsername(String username);
}