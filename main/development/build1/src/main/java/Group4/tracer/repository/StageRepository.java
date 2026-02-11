package Group4.tracer.repository;

import Group4.tracer.model.Products;
import Group4.tracer.model.Stages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stages, String> {

    //Search for the stageId
    //This is the SQL query that is run with each productId search
    @Query(value = "SELECT * " +
            "FROM public.\"Stages\" " +
            "WHERE product_id = :productId " +
            "ORDER BY stage_id ASC", nativeQuery = true)

    //findStageArray takes a productId string and returns an array of product information
    Object[] findStageArray(@Param("productId") String productId);

}