package Group4.tracer.repository;

import Group4.tracer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

//This interface inherits SQL operations by extending JpaRepository
//Meaning manual SQL queries do not need to be written
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    //Search for the productId
    //Optional is used to handle null values and avoid errors
    Optional<Product> findByProductId(String productId);
}

