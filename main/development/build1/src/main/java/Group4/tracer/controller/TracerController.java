package Group4.tracer.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Group4.tracer.model.Claims;
import Group4.tracer.model.Products;
import Group4.tracer.model.Stages;
import Group4.tracer.repository.ClaimRepository;
import Group4.tracer.repository.EvidenceRepository;
import Group4.tracer.repository.ProductRepository;
import Group4.tracer.repository.StageRepository;

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
            Object[] evidenceData = evidenceRepository.findEvidenceArray(userInput);

            if (productData != null && productData.length > 0) { //checks if requested data exists in Products
                System.out.println("Product found");
                System.out.println(java.util.Arrays.deepToString(evidenceData));

                Object[] innerProductData = (Object[]) productData[0];
                Products p = new Products(
                    innerProductData[0].toString(), 
                    innerProductData[1].toString(), 
                    innerProductData[2].toString(), 
                    innerProductData[3].toString(), 
                    innerProductData[4].toString()); 

                model.addAttribute("productFound", true);

                model.addAttribute("productId", p.getProductId());
                model.addAttribute("name", p.getName());
                model.addAttribute("category", p.getCategoryText());
                model.addAttribute("brand", p.getBrand());
                model.addAttribute("description", p.getDescription());

                if (traceData != null && traceData.length > 0) { //if there are stages 
                    for (int i = 0; i < traceData.length; i++) {
                        Object[] stage = (Object[]) traceData[i];
                        p.addStage(new Stages(
                            stage[0].toString(), 
                            stage[2].toString(), 
                            stage[3].toString(), 
                            stage[4].toString(), 
                            "", 
                            stage[6].toString()));
                    }
                    model.addAttribute("hasStages", true);
                }
                else {
                    model.addAttribute("hasStages", false);
                    System.out.println("No stages found for this product");
                }

                model.addAttribute("stages", p.getListOfStagesDetails());

                if (claimData != null && claimData.length > 0) { //if there are claims
                    for (int i = 0; i < claimData.length; i++) {
                        Object[] claim = (Object[]) claimData[i];
                        p.addClaim(new Claims(
                            claim[0].toString(), 
                            claim[2].toString(), 
                            claim[3].toString(), 
                            claim[4].toString(), 
                            claim[5].toString()));
                    }
                    model.addAttribute("hasClaims", true);     
                    model.addAttribute("claims", p.getListOfClaimsDetails());
                }
                else {
                    model.addAttribute("hasClaims", false);
                }

                List<String[]> evidenceList = new ArrayList<>();
                if (evidenceData != null && evidenceData.length > 0) {
                    for (int j = 0; j < evidenceData.length; j++) {
                        Object[] ev = (Object[]) evidenceData[j];
                        evidenceList.add(new String[]{ 
                            ev[0].toString(), 
                            ev[2].toString(), // skip claim_id 
                            ev[3].toString(), 
                            ev[4].toString(), 
                            ev[5].toString(), 
                            ev[6].toString() });
                    }
                    model.addAttribute("evidence", evidenceList);
                } else {
                    model.addAttribute("evidence", null);
                    System.out.println("No evidence found for this product");
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