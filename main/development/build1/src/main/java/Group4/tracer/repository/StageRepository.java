package Group4.tracer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.Stages;

@Repository
public interface StageRepository extends JpaRepository<Stages, String> {

    @Query(value = "SELECT * FROM stages WHERE product_id = :productId ORDER BY stage_id ASC", nativeQuery = true)
    List<Object[]> findStageArray(@Param("productId") String productId);

    List<Stages> findByProductIdOrderByStageIdAsc(String productId);
}