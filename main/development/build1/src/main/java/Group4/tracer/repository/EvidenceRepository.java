package Group4.tracer.repository;

import Group4.tracer.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvidenceRepository extends JpaRepository<Evidence, String> {

    //Search for the evidenceId
    //This is the SQL query that is run with each productId search
    @Query(value = "SELECT * " +
            "FROM public.\"evidence\" " +
            "WHERE product_id = :productId " +
            "ORDER BY evidence_id ASC", nativeQuery = true)

    //findClaimArray takes a productId string and returns an array of product information
    Object[] findEvidenceArray(@Param("productId") String productId);

}