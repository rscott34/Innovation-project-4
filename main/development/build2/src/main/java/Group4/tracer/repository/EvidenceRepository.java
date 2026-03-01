package Group4.tracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.Evidence;
import jakarta.transaction.Transactional;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, String> {

    //SQL query that finds evidence for claims
    
        @Transactional
        @Modifying
        @Query(value = "UPDATE evidence " +
                "SET file_reference = :evidencePath " +
                "WHERE evidence_id = :evidenceId", nativeQuery = true)
                int updateEvidencePath(@Param("evidencePath") String evidencePath, @Param("evidenceId") String evidenceId);

        @Query(value = "SELECT Claims.claim_id, evidence.evidence_id, evidence.type, evidence.issuer, evidence.date, evidence.summary, evidence.file_reference " +
                "FROM evidence " +
                "JOIN Claims ON evidence.evidence_id = Claims.evidence_id " + 
                "WHERE Claims.product_id = :productId " +
                "ORDER BY Claims.claim_id ASC", nativeQuery = true)
        Object[] findEvidenceArray(@Param("productId") String productId);       


}