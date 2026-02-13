package Group4.tracer.repository;

import Group4.tracer.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, String> {

    //SQL query that finds evidence for claims
    @Query(value = "SELECT evidence.* " +
            "FROM evidence " +
            "JOIN Claims ON evidence.linked_to = Claims.claim_id " + //filter only claims that link to each other
            "WHERE Claims.product_id = :productId " +
            "ORDER BY evidence.evidence_id ASC", nativeQuery = true)

    Object[] findEvidenceArray(@Param("productId") String productId);

}