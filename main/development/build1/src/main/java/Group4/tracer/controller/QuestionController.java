package Group4.tracer.controller;

import Group4.tracer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Random;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private ProductRepository productRepository;
    
    private final String[] TOPICS = {"raw materials", "processing", "assembly", "transport", "retail"};
    private final Random random = new Random();

    @GetMapping("/generate")
    public String generateQuestion(Model model) {
        try {
            // The random product ID
            String randomProductId = productRepository.getRandomProductId();
            
            // Get product details using  method
            Object[] productData = productRepository.findProductArray(randomProductId);
            
            if (productData != null && productData.length > 0) {
                Object[] product = (Object[]) productData[0];
                
                String productId = product[0].toString();
                String name = product[1].toString();
                
                // 3. Randomly select topic
                String topic = TOPICS[random.nextInt(TOPICS.length)];
                
                // 4. Get traceability data - To do: Adam/Wajih need to implement the actual query I assume?
                // For now, I'll use a temp placeholder until traceability table is ready
                String correctAnswer = "Information available soon";
                String evidenceLink = "https://traceability.example.com/" + productId;
                
                // Generated the question text
                String questionText = "What is the traceability timeline value for '" + topic + 
                                    "' in the product: " + name + " (Product ID: " + productId + ")?";
                
                
                model.addAttribute("questionGenerated", true);
                model.addAttribute("productId", productId);
                model.addAttribute("productName", name);
                model.addAttribute("topic", topic);
                model.addAttribute("questionText", questionText);
                model.addAttribute("correctAnswer", correctAnswer);
                model.addAttribute("evidenceLink", evidenceLink);
            }
        } catch (Exception e) {
            model.addAttribute("questionGenerated", false);
            model.addAttribute("errorMessage", "Failed to generate question: " + e.getMessage());
        }
        
        return "index"; //
    }

    @PostMapping("/verify")
    public String verifyAnswer(
            @RequestParam String productId,
            @RequestParam String topic,
            @RequestParam String userAnswer,
            @RequestParam String correctAnswer,
            @RequestParam String evidenceLink,
            Model model) {
        
        boolean isCorrect = userAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
        
        // I've added the verification results to model for the UI
        model.addAttribute("answerSubmitted", true);
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("userAnswer", userAnswer);
        model.addAttribute("correctAnswer", correctAnswer);
        model.addAttribute("evidenceLink", evidenceLink);
        
        if (isCorrect) {
            model.addAttribute("resultMessage", "✓ Correct!");
            model.addAttribute("feedback", "Your answer matches our traceability records.");
        } else {
            model.addAttribute("resultMessage", "✗ Incorrect");
            model.addAttribute("feedback", "The correct traceability value is shown below.");
        }
        
        return "index"; // I've structured it so that what George told me so it can be fed to the UI.
    }
}