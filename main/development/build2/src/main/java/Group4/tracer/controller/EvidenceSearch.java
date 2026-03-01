package Group4.tracer.controller;

import Group4.tracer.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/evidence")
public class EvidenceSearch {

    @Autowired //spring boot uses this to find the productRepository
    private EvidenceRepository evidenceRepository;

    @GetMapping("/search")
    public Object[] searchEvidence(@RequestParam String id) {
        //call SQL query from productRepository.java
        Object[] evidenceResult = evidenceRepository.findEvidenceArray(id);

        if (evidenceResult != null && evidenceResult.length > 0) {
            System.out.println("productId: " + id);
            System.out.println(Arrays.toString(evidenceResult));

            return evidenceResult;
        } else {
            System.out.println("productId " + id + " not found in database.");
            return new Object[] {"Error: Product not found"};
        }
    }
}
