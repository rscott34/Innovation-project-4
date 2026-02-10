package Group4.tracer.repository;

import Group4.tracer.model.Claims;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<Claims, String> {

    //Search for the claimId
    //This is the SQL query that is run with each productId search
    @Query(value = "SELECT * " +
            "FROM public.\"Claims\" " +
            "WHERE product_id = :productId " +
            "ORDER BY claim_id ASC", nativeQuery = true)

    //findClaimArray takes a productId string and returns an array of product information
    Object[] findClaimArray(@Param("claimId") String claimId);

}