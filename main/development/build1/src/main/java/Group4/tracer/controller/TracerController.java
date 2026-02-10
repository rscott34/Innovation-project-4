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
            Object[] productData = productRepository.findProductArray(userInput); //stores array data
            //Object[] traceData = productRepository.
            //I had to use ToString() to output the array or else it would print an memory address instead
            if (productData != null && productData.length > 0) { //checks if requested data exists
                // String readableData = java.util.Arrays.deepToString((Object[]) productData); //converts array to string to be printed
                
                Object[] innerArray = (Object[]) productData[0]; // George - better way of accessing the elements in the list since obj returned is [[]] just get the 0th element which is the list 

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
I don't know why there are two sets of square brackets, I will fix that in the future


 */