package Group4.tracer.controller;

import Group4.tracer.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import Group4.tracer.repository.ProductRepository;
import Group4.tracer.repository.StageRepository;
import Group4.tracer.repository.ClaimRepository;
import Group4.tracer.repository.EvidenceRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TracerController {

    //allows interaction with database
    @Autowired
    private ProductRepository productRepository; //dependency Injection,

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private EvidenceRepository  evidenceRepository;

    @GetMapping("/")
    public String showForm() {
        return "index"; // loads index.html
    }

    @PostMapping("/submit")
    public String handleInput(@RequestParam String userInput, Model model) { //takes input from search box
        if (userInput != null) {

            Object[] productData = productRepository.findProductArray(userInput); //stores array data
            Object[] traceData = stageRepository.findStageArray(userInput);
            Object[] claimData = claimRepository.findClaimArray(userInput);

            if (productData != null && productData.length > 0) { //checks if requested data exists in Products
                System.out.println("Product found");

                Object[] innerProductData = (Object[]) productData[0];

                String productId = innerProductData[0].toString();
                String name = innerProductData[1].toString();
                String category = innerProductData[2].toString();
                String brand = innerProductData[3].toString();
                String description = innerProductData[4].toString();    

                model.addAttribute("productFound", true); 

                model.addAttribute("productId", productId);
                model.addAttribute("name", name);
                model.addAttribute("category", category);
                model.addAttribute("brand", brand);
                model.addAttribute("description", description);

                List<Map<String, String>> stagesList = new ArrayList<>();

                for (int i = 0; i < traceData.length; i++) {
                    Object[] stage = (Object[]) traceData[i];

                    Map<String, String> stageMap = new HashMap<>();
                    stageMap.put("stageId", stage[0].toString());
                    stageMap.put("productId", stage[1].toString());
                    stageMap.put("stageType", stage[2].toString());
                    stageMap.put("location", stage[3].toString());
                    stageMap.put("startDate", stage[4].toString());
                    //stageMap.put("endDate", stage[5].toString()); -- add values for end date in db are NULL this causes an error when trying to display the stage information
                    stageMap.put("description", stage[6].toString());
                    stagesList.add(stageMap);
                }


                model.addAttribute("stages", stagesList);
             
                System.out.println(claimData);


                if (claimData != null && claimData.length > 0) {
                    List<Map<String, String>> claimsList = new ArrayList<>();
                    
                    for (int i = 0; i < claimData.length; i++) { //go through claims
                        Object[] claim = (Object[]) claimData[i]; //store claim 

                        String claimId = claim[0].toString();
                        String claimProductId = claim[1].toString();
                        String claimType = claim[2].toString();
                        String claimText = claim[3].toString();
                        String confidenceLabel = claim[4].toString();
                        String rationale = claim[5].toString();

                        Map<String, String> claimMap = new HashMap<>(); //create map to store claim information
                        claimMap.put("claimId", claimId);
                        claimMap.put("productId", claimProductId);
                        claimMap.put("claimType", claimType);
                        claimMap.put("claimText", claimText);         
                        claimMap.put("confidence_label", confidenceLabel);  
                        claimMap.put("rationale", rationale);
                        
                        claimsList.add(claimMap);
                    }

                    model.addAttribute("hasClaims", true);     
                    model.addAttribute("claims", claimsList);

                }
                else {
                    model.addAttribute("hasClaims", false);
                    System.out.println("No claims found for this product");
                }

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