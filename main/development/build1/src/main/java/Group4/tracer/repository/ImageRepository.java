package Group4.tracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {

@Query(value = "SELECT image_id, product_id, file_location FROM public.\"images\" WHERE product_id = :productId LIMIT 1", nativeQuery = true)
Object[] findImageArray(@Param("productId") String productId);
}