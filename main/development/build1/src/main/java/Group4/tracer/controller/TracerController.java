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
    public String handleInput(@RequestParam String userInput, Model model) {
        if (userInput != null){
            System.out.println(userInput); //need to implement some sort of error handling when we know what product id will be like
            model.addAttribute("result", userInput);
        }
        else {
            model.addAttribute("result", "No input provided.");
        }
        return "index";
    }
}
