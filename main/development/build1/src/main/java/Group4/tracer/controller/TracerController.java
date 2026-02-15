package Group4.tracer.controller;

import Group4.tracer.model.ChangeLog;
import Group4.tracer.model.Claims;
import Group4.tracer.model.Evidence;
import Group4.tracer.model.Stages;
import Group4.tracer.model.Verifier;
import Group4.tracer.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class TracerController {

    //allows interaction with database
    @Autowired private ProductRepository productRepository; 
    @Autowired private StageRepository stageRepository;
    @Autowired private ClaimRepository claimRepository;
    @Autowired private EvidenceRepository evidenceRepository;
    @Autowired private ChangeLogRepository changeLogRepository;
    @Autowired private VerifierRepository verifierRepository; // Added Verifier Repository

    //get mappijg for login page 
    @GetMapping("/login")
    public String loginPage() { return "login"; }
    //post mapping for login page

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        // Find the user account in the database by username
        Optional<Verifier> verifierOpt = verifierRepository.findByUsername(username);
        
        // Verify that user exists and if password matches.
        if (verifierOpt.isPresent() && verifierOpt.get().getPassword().equals(password)) {
            session.setAttribute("role", "verifier");
            session.setAttribute("username", username); 
            return "redirect:/"; // successful login so user must be redirected to product search page.
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login"; // Login failed so display error error.
        }
    }
    //When user presses logout - redirect user to product search page and logout of accoiunt
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    
    @GetMapping("/")
    public String showForm(HttpSession session, Model model) {
    //get the role and username of the user
        String role = (String) session.getAttribute("role");
        //sets user to guest if not loggied in as verifier
        model.addAttribute("role", role != null ? role : "guest");
        return "index";
    }

    // traceability editing
    @GetMapping("/edit-stage")
    public String editStageForm(@RequestParam String stageId, Model model) {
    //sets stage id
        model.addAttribute("stageId", stageId);
        return "edit-stage";
    }
    //sets stage data ready for UI
    @PostMapping("/update-stage")
    public String updateStage(@RequestParam String stageId, @RequestParam String newLocation, HttpSession session) {
        //finds stageID
        Stages stage = stageRepository.findById(stageId).orElse(null);
        if (stage != null) {
        //gets new stage loctaion and sets this for the stage
            String oldLoc = stage.getLocation();
            stage.setLocation(newLocation);
            stageRepository.save(stage);

            // adds log of change to the change log table
            ChangeLog log = new ChangeLog();
            log.setLogId(UUID.randomUUID().toString());
            log.setEntityType("Stage");
            log.setEntityId(stageId);
            log.setChangedBy((String) session.getAttribute("username"));
            log.setTimestamp(LocalDateTime.now().toString());
            log.setChangeSummary("Updated location from " + oldLoc + " to " + newLocation);
            changeLogRepository.save(log);
        }
        return "redirect:/";
    }

    // attaching evidence to claims
    @GetMapping("/verify-claim")
    public String verifyClaimForm(@RequestParam String claimId, Model model) {
        model.addAttribute("claimId", claimId);
        
        // Fetch all actual evidence records from the PostgreSQL database
        Iterable<Evidence> evidenceFromDb = evidenceRepository.findAll();
        
        // Pass the database records to the HTML page
        model.addAttribute("evidenceList", evidenceFromDb);
        
        return "verify-claim";
    }

    @PostMapping("/submit-verification")
    public String submitVerification(@RequestParam String claimId, @RequestParam String evidenceFile, @RequestParam String explanation, HttpSession session) {
        Claims claim = claimRepository.findById(claimId).orElse(null);
        if (claim != null) {
            
            // Enforce Validation Rule
            if (evidenceFile == null || evidenceFile.equals("none") || evidenceFile.isEmpty()) {
                claim.setConfidenceLabelString("Unverified");
                claim.setRationale("No evidence attached. Explanation: " + explanation);
            } else {
                claim.setConfidenceLabelString("Verified");
                claim.setRationale("Evidence attached: " + evidenceFile + " | Explanation: " + explanation);
            }
            claimRepository.save(claim);

            // add setails to to ChangeLog
            ChangeLog log = new ChangeLog();
            log.setLogId(UUID.randomUUID().toString());
            log.setEntityType("Claim");
            log.setEntityId(claimId);
            log.setChangedBy((String) session.getAttribute("username"));
            log.setTimestamp(LocalDateTime.now().toString());
            log.setChangeSummary("Verified claim using file: " + evidenceFile);
            changeLogRepository.save(log);
        }
        return "redirect:/";
    }

    //get changelog data to display 
    @GetMapping("/history")
    public String viewHistory(Model model) {
        model.addAttribute("logs", changeLogRepository.findAll());
        return "history";
    }

    // add submit button for
    @PostMapping("/submit")
    public String handleInput(@RequestParam String userInput, HttpSession session, Model model) { 
        
        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role != null ? role : "guest");

        if (userInput != null) {

            Object[] productData = productRepository.findProductArray(userInput); 
            Object[] traceData = stageRepository.findStageArray(userInput);
            Object[] claimData = claimRepository.findClaimArray(userInput);
            Object[] evidenceData = evidenceRepository.findEvidenceArray(userInput);

            if (productData != null && productData.length > 0) { 
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
                
                if (evidenceData != null && evidenceData.length > 0) {
                    Claims claim = p.getClaimByIndex(0);
                    for (int j = 0; j < evidenceData.length; j++) {
                        Object[] ev = (Object[]) evidenceData[j];
                        claim.addEvidence(new Evidence(
                            ev[0].toString(), 
                            ev[2].toString(), // skip claim_id 
                            ev[3].toString(), 
                            ev[4].toString(), 
                            ev[5].toString(), 
                            ev[6].toString()));
                        System.out.println(ev[1].toString());
                    }
                    model.addAttribute("evidence", claim.getListOfEvidenceDetails());
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