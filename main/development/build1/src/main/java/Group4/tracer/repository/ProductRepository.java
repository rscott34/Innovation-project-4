package Group4.tracer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Group4.tracer.model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, String> {

    //Search for the productId
    //This is the SQL query that is run with each productId search
    @Query(value = "SELECT * " +
            "FROM public.\"products\" " +
            "WHERE product_id = :productId " +
            "ORDER BY product_id ASC", nativeQuery = true)

    //findProductArray takes a productId string and returns an array of product information
    Object[] findProductArray(@Param("productId") String productId);

    @Query(value = "SELECT product_id FROM public.\"products\" ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    String getRandomProductId();

    // Wajih or Adam need to get back to me in regards of if the table and the column names exist.

    @Query(value = "SELECT * FROM public.\"Traceability\" WHERE product_id = :productId", nativeQuery = true)
    Object[] findTraceabilityArray(@Param("productId") String productId);

}




// To proceed forward I need Wajih or Adam to tell me how to query the dataset since it cannot be hardcoded so that needs to be sorted.

