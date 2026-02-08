package Group4.tracer.controller;

import Group4.tracer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@RestController
@RequestMapping("/products")
public class ProductSearch {

    @Autowired //spring boot uses this to find the productRepository
    private ProductRepository productRepository;

    @GetMapping("/search")
    public Object[] searchProduct(@RequestParam String id) {
        //call SQL query from productRepository.java
        Object[] result = productRepository.findProductArray(id);

        if (result != null && result.length > 0) {
            System.out.println("ProductId: " + id);
            System.out.println(Arrays.toString(result));

            return result;
        } else {
            System.out.println("productId " + id + " not found in database.");
            return new Object[] {"Error: Product not found"};
        }
    }
}

//USE http://localhost:8080/products/search?id=P000 to search
