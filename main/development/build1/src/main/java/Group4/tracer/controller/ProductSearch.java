package Group4.tracer.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Group4.tracer.repository.ProductRepository;

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
