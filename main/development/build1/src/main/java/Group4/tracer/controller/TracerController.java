package Group4.tracer.controller;

import Group4.tracer.repository.ClaimRepository;
import Group4.tracer.repository.ProductRepository;
import Group4.tracer.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TracerController {

    //allows interaction with database
    @Autowired
    private ProductRepository productRepository; //dependency Injection,
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private ClaimRepository claimRepository;

    @GetMapping("/")
    public String showForm() {
        return "index"; // loads index.html
    }

    @PostMapping("/submit")
    public String handleInput(@RequestParam String userInput, Model model) { //takes input from search box
        if (userInput != null) {
            Object[] productData = productRepository.findProductArray(userInput); //stores array data
            //Object[] traceData = stageRepository.findStageArray(userInput);
            Object[] claimData = claimRepository.findClaimArray(userInput);
            
            //I had to use ToString() to output the array or else it would print an memory address instead

            if (productData != null && productData.length > 0) { //checks if requested data exists
                Object[] innerArray = (Object[]) productData[0];

                String productId = innerArray[0].toString();
                String name = innerArray[1].toString();
                String category = innerArray[2].toString();
                String brand = innerArray[3].toString();
                String description = innerArray[4].toString();    

                model.addAttribute("productFound", true); 
                model.addAttribute("productId", productId);
                model.addAttribute("name", name);
                model.addAttribute("category", category);
                model.addAttribute("brand", brand);
                model.addAttribute("description", description);

                System.out.println("Product found");

                System.out.println(claimData);
            }
            else {
                model.addAttribute("productFound", false);
                model.addAttribute("errorMessage", "Product ID " + userInput + " not found.");
            }
        }
        else {
            model.addAttribute("productFound", false);
            model.addAttribute("errorMessage", "No input provided.");
        }
        return "index";
    }
}

/*IMPORTANT NOTES FOR BACKEND: - from Waj ;)

The array output is stored in the variable productData
this is in the format       [["P100","T-shirt","CLOTHING","Next","T-shirt from Spain"]]

 */