package Group4.tracer.controller;

import Group4.tracer.repository.ProductRepository;
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

    @GetMapping("/")
    public String showForm() {
        return "index"; // loads index.html
    }

    @PostMapping("/submit")
    public String handleInput(@RequestParam String userInput, Model model) { //takes input from search box
        if (userInput != null) {
            Object[] productData = productRepository.findProductArray(userInput);

            if (productData != null) { //checks if requested data exists
                String readableData = java.util.Arrays.deepToString((Object[]) productData);
                System.out.println(readableData);
                model.addAttribute("result", readableData);
            }
            else {
                model.addAttribute("result", "Product ID " + userInput + " not found.");
            }
        }
        else {
            model.addAttribute("result", "No input provided.");
        }
        return "index";
    }
}
