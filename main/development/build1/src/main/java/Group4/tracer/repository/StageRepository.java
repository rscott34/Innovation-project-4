package Group4.tracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.Stages;

@Repository
public interface StageRepository extends JpaRepository<Stages, String> {

    //Searching for all stages by product ID
    @Query(value = "SELECT * " +
            "FROM public.\"stages\" " +
            "WHERE product_id = :productId " +
            "ORDER BY stage_id ASC", nativeQuery = true)
    Object[] findStageArray(@Param("productId") String productId);
    // Returns all stages for a product ordered by stage_id
    java.util.List<Stages> findByProductIdOrderByStageIdAsc(String productId);

    // Get specific stage value and evidence link for a product and stage name
    @Query(value = "SELECT stage_Id, evidence_link " +
            "FROM public.\"stages\" " +
            "WHERE product_id = :productId AND stage_name = :stageName", nativeQuery = true)
    Object[] findStageEvidence(@Param("productId") String productId, @Param("stageName") String stageName);
    
    // Get all stage names for a product . This is to verify which stages exist.
    @Query(value = "SELECT stage_name FROM public.\"stages\" WHERE product_id = :productId", nativeQuery = true)
    String[] findStageNamesByProduct(@Param("productId") String productId);
}