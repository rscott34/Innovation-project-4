package Group4.tracer.controller;

import Group4.tracer.repository.ProductRepository;
import Group4.tracer.repository.StageRepository;
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
    
    @Autowired  // Here I have added StageRepository-> StageRepository for traceability data
    private StageRepository stageRepository;
    
    private final String[] STAGES = {"raw materials", "processing", "assembly", "transport", "retail"};
    private final Random random = new Random();

    @GetMapping("/generate")
    public String generateQuestion(Model model) {
        try {
            // The random product ID
            String randomProductId = productRepository.getRandomProductId();
            
            // Get product details using method
            Object[] productData = productRepository.findProductArray(randomProductId);
            
            if (productData != null && productData.length > 0) {
                Object[] product = (Object[]) productData[0];
                
                String productId = product[0].toString();
                String name = product[1].toString();
                
                //  Randomly select stage 
                String stage = STAGES[random.nextInt(STAGES.length)];
                
                //  Get traceability data - Adam/Wajih implemented the actual query ideally.
                Object[] stageData = stageRepository.findStageEvidence(productId, stage);
                
                String correctAnswer = "Information not available";
                String evidenceLink = "#";
                
                if (stageData != null && stageData.length > 0) {
                    // stageData[0] = stage_value, stageData[1] = evidence_link
                    correctAnswer = stageData[0].toString();
                    evidenceLink = stageData[1].toString();
                    
                    // Use this for debuggging , del later
                    System.out.println("Found stage data - Value: " + correctAnswer + ", Link: " + evidenceLink);
                } else {
                    // Likewise here , after debugging just del.
                    System.out.println("No stage data found for product: " + productId + ", stage: " + stage);
                    //IMPORTANT:T.D: Adam/Wajih need to check why there is no data for this product/stage combination
                }
                
                // Generated the question text
                String questionText = "What is the traceability timeline value for '" + stage + 
                                    "' in the product: " + name + " (Product ID: " + productId + ")?";
                
                // IMPORTANT: Add to model for George's UI
                model.addAttribute("questionGenerated", true);
                model.addAttribute("productId", productId);
                model.addAttribute("productName", name);
                model.addAttribute("stage", stage);  // Renamed from topic to stage
                model.addAttribute("questionText", questionText);
                model.addAttribute("correctAnswer", correctAnswer);
                model.addAttribute("evidenceLink", evidenceLink);
            }
        } catch (Exception e) {
            model.addAttribute("questionGenerated", false);
            model.addAttribute("errorMessage", "Failed to generate question: " + e.getMessage());
            e.printStackTrace(); // For debugging
        }
        
        return "index"; // Connoted for George's UI template
    }

    @PostMapping("/verify")
    public String verifyAnswer(
            @RequestParam String productId,
            @RequestParam String stage,  // This has been renamed from topic to stage
            @RequestParam String userAnswer,
            @RequestParam String correctAnswer,
            @RequestParam String evidenceLink,
            Model model) {
        
        boolean isCorrect = userAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
        
        // I've added the verification results to model for George's UI
        model.addAttribute("answerSubmitted", true);
        model.addAttribute("isCorrect", isCorrect);
        model.addAttribute("userAnswer", userAnswer);
        model.addAttribute("correctAnswer", correctAnswer);
        model.addAttribute("evidenceLink", evidenceLink);  // IMPORTANT : This is for George's UI link - Rowan said to show this when the answer is wrong
        model.addAttribute("productId", productId);
        model.addAttribute("stage", stage);
        
        if (isCorrect) {
            model.addAttribute("resultMessage", "✓ Correct!");
            model.addAttribute("feedback", "Your answer matches our traceability records.");
        } else {
            model.addAttribute("resultMessage", "✗ Incorrect");
            model.addAttribute("feedback", "The correct traceability value is shown below.");
            // evidenceLink is already in model - George can use this to create a clickable link as Rowan requested.
            // George I don't know if you already know so I am mentioning it anyways: <a th:href="@{${evidenceLink}}">View evidence for this stage</a>
            // This takes the user to the page with the correct answer information
        }
        
        return "index"; // I've structured it so that what George told me so it can be fed to the UI.
    }
}