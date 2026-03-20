package Group4.tracer.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Group4.tracer.repository.StageRepository;

@RestController
@RequestMapping("/stages")
public class StageSearch {

    @Autowired
    private StageRepository stageRepository;

    @GetMapping("/search")
    public Object searchStage(@RequestParam String id) {
        List<Object[]> traceabilityResult = stageRepository.findStageArray(id);

        if (traceabilityResult != null && !traceabilityResult.isEmpty()) {
            System.out.println("productId: " + id);
            System.out.println(Arrays.deepToString(traceabilityResult.toArray()));
            return traceabilityResult;
        } else {
            System.out.println("productId " + id + " not found in database.");
            return new Object[] { "Error: Product not found" };
        }
    }
}