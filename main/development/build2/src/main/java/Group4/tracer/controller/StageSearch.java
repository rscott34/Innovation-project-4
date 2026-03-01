package Group4.tracer.controller;

import Group4.tracer.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@RestController
@RequestMapping("/stages")
public class StageSearch {

    @Autowired //spring boot uses this to find the productRepository
    private StageRepository stageRepository;

    @GetMapping("/search")
    public Object[] searchStage(@RequestParam String id) {
        //call SQL query from productRepository.java
        Object[] traceabilityResult = stageRepository.findStageArray(id);

        if (traceabilityResult != null && traceabilityResult.length > 0) {
            System.out.println("productId: " + id);
            System.out.println(Arrays.toString(traceabilityResult));

            return traceabilityResult;
        } else {
            System.out.println("productId " + id + " not found in database.");
            return new Object[] {"Error: Product not found"};
        }
    }
}
