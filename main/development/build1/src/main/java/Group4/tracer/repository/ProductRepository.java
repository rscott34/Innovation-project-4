package Group4.tracer.repository;

import Group4.tracer.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, String> {

    //Search for the productId
    //This is the SQL query that is run with each productID search
    @Query(value = "SELECT * " +
            "FROM public.\"Products\" " +
            "WHERE product_id = :productId " +
            "ORDER BY product_id ASC", nativeQuery = true)

    //findProductArray takes a productId string and returns an array of product information
    Object[] findProductArray(@Param("productId") String productId);
}