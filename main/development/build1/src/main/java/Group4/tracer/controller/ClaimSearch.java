package Group4.tracer.controller;

import Group4.tracer.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@RestController
@RequestMapping("/claims")
public class ClaimSearch {

    @Autowired //spring boot uses this to find the productRepository
    private ClaimRepository claimRepository;

    @GetMapping("/search")
    public Object[] searchStage(@RequestParam String id) {
        //call SQL query from productRepository.java
        Object[] claimResult = claimRepository.findClaimArray(id);

        if (claimResult != null && claimResult.length > 0) {
            System.out.println("productId: " + id);
            System.out.println(Arrays.toString(claimResult));

            return claimResult;
        } else {
            System.out.println("productId " + id + " not found in database.");
            return new Object[] {"Error: Product not found"};
        }
    }
}
