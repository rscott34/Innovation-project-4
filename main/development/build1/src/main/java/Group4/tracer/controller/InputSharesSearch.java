package Group4.tracer.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Group4.tracer.repository.InputSharesRepository;

@RestController
@RequestMapping("/input-shares")
public class InputSharesSearch {

    @Autowired //spring boot uses this to find the productRepository
    private InputSharesRepository inputSharesRepository;

    @GetMapping("/search")
    public Object[] searchInputShares(@RequestParam String id) {
        //call SQL query from productRepository.java
        Object[] inputSharesResult = inputSharesRepository.findInputSharesArray(id);

        if (inputSharesResult != null && inputSharesResult.length > 0) {
            System.out.println("inputSharesId: " + id);
            System.out.println(Arrays.toString(inputSharesResult));

            return inputSharesResult;
        } else {
            System.out.println("productId " + id + " not found in database.");
            return new Object[] {"Error: Product not found"};
        }
    }
}
