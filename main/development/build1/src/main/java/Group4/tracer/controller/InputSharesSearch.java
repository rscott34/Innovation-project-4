package Group4.tracer.controller;

import java.util.List;

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
    public List<Object[]> searchInputShares(@RequestParam String id) {
        // This now returns a list of all ingredient rows for that product
        List<Object[]> inputSharesResult = inputSharesRepository.findInputSharesArray(id);

        if (inputSharesResult != null && !inputSharesResult.isEmpty()) {
            System.out.println("Product ID: " + id + " found. Rows: " + inputSharesResult.size());
            return inputSharesResult;
        } else {
            System.out.println("Product ID " + id + " not found.");
            return null; 
        }
    }
}
