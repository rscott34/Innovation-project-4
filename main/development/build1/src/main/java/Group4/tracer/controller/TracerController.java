package Group4.tracer.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Group4.tracer.enums.Rank;
import Group4.tracer.enums.StageType;
import Group4.tracer.enums.UserType;
import Group4.tracer.model.ChangeLog;
import Group4.tracer.model.Claims;
import Group4.tracer.model.Evidence;
import Group4.tracer.model.Mission;
import Group4.tracer.model.Products;
import Group4.tracer.model.Stages;
import Group4.tracer.model.User;
import Group4.tracer.model.issueReport;
import Group4.tracer.repository.ChangeLogRepository;
import Group4.tracer.repository.ClaimRepository;
import Group4.tracer.repository.EvidenceRepository;
import Group4.tracer.repository.InputSharesRepository;
import Group4.tracer.repository.IssueRepository;
import Group4.tracer.repository.ProductRepository;
import Group4.tracer.repository.StageRepository;
import Group4.tracer.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class TracerController {

    //allows interaction with database
    @Autowired private ProductRepository productRepository; 
    @Autowired private StageRepository stageRepository;
    @Autowired private ClaimRepository claimRepository;
    @Autowired private EvidenceRepository evidenceRepository;
    @Autowired private ChangeLogRepository changeLogRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private InputSharesRepository inputSharesRepository;
    @Autowired private IssueRepository issueRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    //get mapping for login page  
    @GetMapping("/login")
    public String loginPage() { return "login"; }

    //post mapping for login page
    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        // Find the user account in the database by username
        Optional<User> userOpt = userRepository.findByUsername(username);
        
        // Verify that user exists and if password matches.
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            User user = userOpt.get();
            user.checkFields();
            if (user.getUserType().equals(UserType.Consumer)) {
                session.setAttribute("role", "consumer");
            } else if (user.getUserType().equals(UserType.Verifier)) {
                System.out.println("Verifier");
                session.setAttribute("role", "verifier");
            }

            session.setAttribute("points", user.getScore());
            
            session.setAttribute("user", user); 
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
        model.addAttribute("questionGenerated", false);
        String role = (String) session.getAttribute("role");
        //sets user to guest if not loggied in as verifier
        session.setAttribute("role", role != null ? role : "guest");
        User user = (User) session.getAttribute("user");
        session.setAttribute("user", user != null ? user : new User());
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match.");
            return "register";
        }
        if (userRepository.existsByUsername(username)) {
            model.addAttribute("errorMessage", "Username already taken.");
            return "register";
        }

        User user = new User();

        int i = 0;
        while (userRepository.existsById(String.format("U%03d", i))) {
            i++;
        }
        user.setUserId(String.format("U%03d", i));

        user.setUserName(username);

        String hashed = passwordEncoder.encode(password);
        user.setPassword(hashed); //hashes using bitEncoder
        
        //System.out.println("Plaintext was " + password + " and Hash is " + hashed); //used for confirming hash
        
        user.setUserTypeString("consumer"); //might be changed to CONSUMER
        userRepository.save(user);

        return "redirect:/login";
    }

    //Profile section
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        Rank rank = user.getRank();
        Rank[] ranks = Rank.values();
        Rank nextRank = null;
        if (rank.ordinal() + 1 < ranks.length) {
            nextRank = ranks[rank.ordinal() + 1];
        }
        model.addAttribute("rank", rank.name());
        if (nextRank == null) {
            model.addAttribute("nextRank", null);
        } else {
            model.addAttribute("nextRank", nextRank.name());
        }

        int min = user.rankMin.get(rank);
        int max = user.rankMax.get(rank);
        float progress = ((float) (user.getScore() - min)) / ((float) (max-min)) * 100;
        System.out.println(progress);
        model.addAttribute("progress", progress);
        model.addAttribute("pointsLeft", user.rankMax.get(rank) - user.getScore());

        //logic for getting top users
        List<User> top;
        if (rank == Rank.Legendary) {
            top = userRepository.findTop20Legendary(min);
        } else {
            top = userRepository.findTop20InRange(min, max);
        }

        model.addAttribute("leaderboard", top);

        
        return "profile";
    }

    // traceability editing
    @GetMapping("/edit-stage")
    public String editStage(@RequestParam String stageId, Model model, HttpSession session) {
        //checks if admin is editing product info
        if ("GENERAL".equalsIgnoreCase(stageId)) {
            String productId = (String) session.getAttribute("currentProductId");             
            
            if (productId == null) {
                        return "redirect:/";
                    }
                    
                    return "redirect:/edit-product?productId=" + productId;
                }

                // Existing logic for stages
                Stages stage = stageRepository.findById(stageId).orElse(null);
                model.addAttribute("stage", stage);
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
            log.setChangedBy(((User) session.getAttribute("user")).getUserName());
            log.setTimestamp(LocalDateTime.now().toString());
            log.setChangeSummary("Updated location from " + oldLoc + " to " + newLocation);
            changeLogRepository.save(log);
        }
        return "redirect:/";
    }
    
    @PostMapping("/update-product")
    public String updateProductDetails(@RequestParam String productId, @RequestParam String newName, @RequestParam String newCategory, @RequestParam String newBrand, @RequestParam String newDescription, HttpSession session) {

    Products product = productRepository.findById(productId).orElse(null);
    if (product != null) {
        // Capture old values for the log
        String oldName = product.getName();
        String oldBrand = product.getBrand();
        String oldCat = product.getCategoryText();

        // Update the product
        product.setName(newName);
        product.setBrand(newBrand);
        product.setDescription(newDescription);
        product.setCategoryString(newCategory);
        productRepository.save(product);

        // Create the ChangeLog entry
        ChangeLog log = new ChangeLog();
        log.setLogId(UUID.randomUUID().toString());
        log.setEntityType("Product");
        log.setEntityId(productId);
        log.setChangedBy((String) session.getAttribute("username"));
        log.setTimestamp(LocalDateTime.now().toString());

        // Match the Stage format: "Updated [Fields] from [Old] to [New]"
        String summary = String.format("Updated product info from [%s, %s, %s] to [%s, %s, %s]", oldName, oldBrand, oldCat, newName, newBrand, newCategory);
        log.setChangeSummary(summary);
        
        changeLogRepository.save(log);
    }

        return "redirect:/"; 
    }

    @GetMapping("/edit-product")
    public String editProductForm(@RequestParam String productId, HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (!"verifier".equals(role)) {
            return "redirect:/"; 
        }

        // 2. Fetch the product so the form knows what it's editing
        Products product = productRepository.findById(productId).orElse(null);
        
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("categories", Group4.tracer.enums.ProductType.values());
            return "edit-product"; // This must match your HTML filename (edit-product.html)
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
public String submitVerification(
    @RequestParam String claimId, 
    @RequestParam String evidenceId, 
    @RequestParam String evidenceFile, 
    HttpSession session) {
    
        System.out.println("Evidence ID: " + evidenceId);
        System.out.println("New Filepath: " + evidenceFile);
    //Pass empty string or default value for summary in repository call
    evidenceRepository.updateEvidencePath(evidenceFile, evidenceId);
    
    Claims claim = claimRepository.findById(claimId).orElse(null);
    if (claim != null) {
        if (evidenceFile.equals("none")) {
            claim.setConfidenceLabelString("Unverified");
        } else {
            claim.setConfidenceLabelString("Verified");
        }
        claimRepository.save(claim);
    }
    return "redirect:/";
}

@GetMapping("/report-issue")
public String showReportPage(@RequestParam String productId, Model model) {
    model.addAttribute("productId", productId);
    
    // Using your specific repository method
    // Note: Since findStageArray returns Object[], we pass it directly to the model
    Object[] productStages = stageRepository.findStageArray(productId);
    model.addAttribute("allStages", productStages);
    
    return "report-issue";
}

@PostMapping("/submit-issue")
public String processReport(@RequestParam String productId,
                            @RequestParam String stageId,
                            @RequestParam String issueType,
                            @RequestParam String description,
                            RedirectAttributes redirectAttributes) {
    
    issueReport report = new issueReport();
    report.setProductId(productId);
    report.setStageId(stageId);
    report.setIssueType(issueType);
    report.setUserDescription(description);

    issueRepository.save(report);

    redirectAttributes.addFlashAttribute("message", "Your report for " + productId + " has been submitted.");
    
    return "redirect:/";
}

//Shows reports to verifiers
@GetMapping("/verifier/inbox")
public String showVerifierInbox(Model model) {
    // Fetch all reports from the database
    List<issueReport> reports = issueRepository.findAll();
    model.addAttribute("reports", reports);
    return "verifier-inbox";
}

@PostMapping("/verifier/resolve")
public String resolveIssue(@RequestParam Long reportId) {
    issueReport report = issueRepository.findById(reportId).orElseThrow();
    report.setStatus("RESOLVED");
    issueRepository.save(report);
    return "redirect:/verifier/inbox";
}

    //get changelog data to display 
    @GetMapping("/history")
    public String viewHistory(Model model) {
        model.addAttribute("logs", changeLogRepository.findAll());
        return "history";
    }

    //FR2 product origin
    /*
    @GetMapping("/origin-breakdown")
    public String showOriginBreakdown(@RequestParam String productId, Model model, HttpSession session) {
        List<Object[]> shares = inputSharesRepository.findInputSharesArray(productId);
        model.addAttribute("shares", inputSharesRepository.findInputSharesArray(productId));
        model.addAttribute("productId", productId);
        
        //maintain role
        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role != null ? role : "guest"); //makes unlogged in users become guests
        return "breakdown"; 
    }
    */

    // add submit button for
    @PostMapping("/submit")
    public String handleInput(@RequestParam boolean questionGenerated, 
        @RequestParam String userAnswer, 
        @RequestParam String userInput,
        @RequestParam(required = false) String anchor,
        HttpSession session, 
        Model model) {

        Mission mission = (Mission) session.getAttribute("mission");
        String role = (String) session.getAttribute("role");
        session.setAttribute("role", role != null ? role : "guest");
        
        if (userInput != null && !userInput.isEmpty()) {

            Object[] productData = productRepository.findProductArray(userInput); 
            Object[] traceData = stageRepository.findStageArray(userInput);
            Object[] claimData = claimRepository.findClaimArray(userInput);
            Object[] evidenceData = evidenceRepository.findEvidenceArray(userInput);

            List<Object[]> shares = inputSharesRepository.findInputSharesArray(userInput);
            model.addAttribute("shares", shares);

            if (productData != null && productData.length > 0) { 
                System.out.println("Product found");
                //System.out.println(java.util.Arrays.deepToString(traceData));

                Object[] innerProductData = (Object[]) productData[0];
                Products p = new Products(
                    innerProductData[0].toString(), 
                    innerProductData[1].toString(), 
                    innerProductData[2].toString(), 
                    innerProductData[3].toString(), 
                    innerProductData[4].toString()); 

                session.setAttribute("currentProductId", p.getProductId());
                
                model.addAttribute("product", p);
                model.addAttribute("productFound", true);
                model.addAttribute("p", p);

                if (traceData != null && traceData.length > 0) { //if there are stages 
                    for (int i = 0; i < traceData.length; i++) {
                        Object[] stage = (Object[]) traceData[i];
                        p.addStage(new Stages(
                            stage[0].toString(), // stageId
                            StageType.fromString(stage[2].toString()).name(), // stageType
                            stage[3].toString(), // stageName
                            stage[4].toString(),  // location
                            //stage[4].toString(), // startDate
                            "", // endDate is not currently used in the database so set to empty string
                            stage[6].toString() // description
                        ));
                    }
                    model.addAttribute("hasStages", true);
                }
                else {
                    model.addAttribute("hasStages", false);
                    System.out.println("No stages found for this product");
                }

                model.addAttribute("stages", p.getListOfStagesDetails());

                if (claimData != null && claimData.length > 0) { // if there are claims
                    for (int i = 0; i < claimData.length; i++) {
                        Object[] claimRow = (Object[]) claimData[i];
                        
                        String currentClaimId = claimRow[0].toString();
                        
                        Claims c = new Claims(
                            claimRow[0].toString(), // claimId
                            claimRow[3].toString(), // type
                            claimRow[4].toString(), // text
                            claimRow[5].toString(), // confidence
                            claimRow[6].toString()  // rationale
                        );

                        if (evidenceData != null) {
                            for (int j = 0; j < evidenceData.length; j++) {
                                Object[] evRow = (Object[]) evidenceData[j];
                                
                                String claimIdFromEvidence = evRow[0].toString();

                                if (claimIdFromEvidence.equals(currentClaimId)) {
                                    c.addEvidence(new Evidence(
                                        evRow[1].toString(), // id
                                        evRow[2].toString(), // type
                                        evRow[3].toString(), // issuer
                                        evRow[4].toString(), // date/data
                                        evRow[5].toString(), // summary
                                        evRow[6].toString()  // fileReference
                                    ));
                                }
                            }
                        }
                        p.addClaim(c);
                    }
                    model.addAttribute("hasClaims", true);     
                    model.addAttribute("claimsList", p.getClaims());                }
                else {
                    model.addAttribute("hasClaims", false);
                }
            } else {
                model.addAttribute("productFound", false);
                System.out.println("No product found with ID: " + userInput);
                model.addAttribute("errorMessage", "Invalid Product ID. Please try again with a valid ID.");
            }
        }
        else {
            model.addAttribute("productFound", false);
            model.addAttribute("errorMessage", "No input provided.");
        }

        model.addAttribute("questionGenerated", questionGenerated);
        model.addAttribute("userAnswer", userAnswer);
        model.addAttribute("mission", mission);
        model.addAttribute("userInput", userInput);
        model.addAttribute("anchor", anchor);
        
        return "index";
    }

    //button for switching to compare view
    @GetMapping("/compare")
    public String compareView(HttpSession session, Model model) {
        //get the role and username of the user
        String role = (String) session.getAttribute("role");
        //sets user to guest if not loggied in as verifier
        session.setAttribute("role", role != null ? role : "guest");
        return "compare";
    }

    @PostMapping("/compare/submit")
    public String compareViewInput (@RequestParam String userInput,
        @RequestParam String userInput2,
        HttpSession session,
        Model model) {
        
        Mission mission = (Mission) session.getAttribute("mission");
        String role = (String) session.getAttribute("role");
        session.setAttribute("role", role != null ? role : "guest");
                
        if (userInput != null && !userInput.isEmpty()) {

            Object[] productData = productRepository.findProductArray(userInput); 
            Object[] traceData = stageRepository.findStageArray(userInput);
            Object[] claimData = claimRepository.findClaimArray(userInput);
            Object[] evidenceData = evidenceRepository.findEvidenceArray(userInput);

            List<Object[]> shares1 = inputSharesRepository.findInputSharesArray(userInput);
            model.addAttribute("shares1", shares1);

            if (productData != null && productData.length > 0) { 

                System.out.println("Product found");
                //System.out.println(java.util.Arrays.deepToString(traceData));

                Object[] innerProductData = (Object[]) productData[0];
                Products p = new Products(
                    innerProductData[0].toString(), 
                    innerProductData[1].toString(), 
                    innerProductData[2].toString(), 
                    innerProductData[3].toString(), 
                    innerProductData[4].toString()); 

                model.addAttribute("productFound", true);
                model.addAttribute("p", p);

                if (traceData != null && traceData.length > 0) { //if there are stages 
                    for (int i = 0; i < traceData.length; i++) {
                        Object[] stage = (Object[]) traceData[i];
                        p.addStage(new Stages(
                            stage[0].toString(), // stageId
                            StageType.fromString(stage[2].toString()).name(), // stageType
                            stage[3].toString(), // stageName
                            stage[4].toString(),  // location
                            //stage[4].toString(), // startDate
                            "", // endDate is not currently used in the database so set to empty string
                            stage[6].toString() // description
                        ));
                    }
                    model.addAttribute("hasStages", true);
                }
                else {
                    model.addAttribute("hasStages", false);
                    System.out.println("No stages found for this product");
                }

                model.addAttribute("stages", p.getListOfStagesDetails());

                if (claimData != null && claimData.length > 0) { // if there are claims
                    for (int i = 0; i < claimData.length; i++) {
                        Object[] claimRow = (Object[]) claimData[i];
                        
                        String currentClaimId = claimRow[0].toString();
                        
                        Claims c = new Claims(
                            claimRow[0].toString(), // claimId
                            claimRow[3].toString(), // type
                            claimRow[4].toString(), // text
                            claimRow[5].toString(), // confidence
                            claimRow[6].toString()  // rationale
                        );

                        if (evidenceData != null) {
                            for (int j = 0; j < evidenceData.length; j++) {
                                Object[] evRow = (Object[]) evidenceData[j];
                                
                                String claimIdFromEvidence = evRow[0].toString();

                                if (claimIdFromEvidence.equals(currentClaimId)) {
                                    c.addEvidence(new Evidence(
                                        evRow[1].toString(), // id
                                        evRow[2].toString(), // type
                                        evRow[3].toString(), // issuer
                                        evRow[4].toString(), // date/data
                                        evRow[5].toString(), // summary
                                        evRow[6].toString()  // fileReference
                                    ));
                                }
                            }
                        }
                        p.addClaim(c);
                    }
                    model.addAttribute("hasClaims", true);     
                    model.addAttribute("claimsList", p.getClaims());                }
                else {
                    model.addAttribute("hasClaims", false);
                }
            } else {
                model.addAttribute("productFound", false);
                System.out.println("No product found with ID: " + userInput);
                model.addAttribute("errorMessage", "Invalid Product ID. Please try again with a valid ID.");
            }
        }
        else {
            model.addAttribute("productFound", false);
            model.addAttribute("errorMessage", "No input provided.");
        }
        if (userInput2 != null && !userInput2.isEmpty()) {

            Object[] productData = productRepository.findProductArray(userInput2); 
            Object[] traceData = stageRepository.findStageArray(userInput2);
            Object[] claimData = claimRepository.findClaimArray(userInput2);
            Object[] evidenceData = evidenceRepository.findEvidenceArray(userInput2);


            List<Object[]> shares2 = inputSharesRepository.findInputSharesArray(userInput2);
            model.addAttribute("shares2", shares2);

            if (productData != null && productData.length > 0) { 
                System.out.println("Product found");
                //System.out.println(java.util.Arrays.deepToString(traceData));

                Object[] innerProductData = (Object[]) productData[0];
                Products p = new Products(
                    innerProductData[0].toString(), 
                    innerProductData[1].toString(), 
                    innerProductData[2].toString(), 
                    innerProductData[3].toString(), 
                    innerProductData[4].toString()); 

                model.addAttribute("productFound2", true);
                model.addAttribute("p2", p);

                if (traceData != null && traceData.length > 0) { //if there are stages 
                    for (int i = 0; i < traceData.length; i++) {
                        Object[] stage = (Object[]) traceData[i];
                        p.addStage(new Stages(
                            stage[0].toString(), // stageId
                            StageType.fromString(stage[2].toString()).name(), // stageType
                            stage[3].toString(), // stageName
                            stage[4].toString(),  // location
                            //stage[4].toString(), // startDate
                            "", // endDate is not currently used in the database so set to empty string
                            stage[6].toString() // description
                        ));
                    }
                    model.addAttribute("hasStages2", true);
                }
                else {
                    model.addAttribute("hasStages2", false);
                    System.out.println("No stages found for this product");
                }

                model.addAttribute("stages2", p.getListOfStagesDetails());

                if (claimData != null && claimData.length > 0) { // if there are claims
                    for (int i = 0; i < claimData.length; i++) {
                        Object[] claimRow = (Object[]) claimData[i];
                        
                        String currentClaimId = claimRow[0].toString();
                        
                        Claims c = new Claims(
                            claimRow[0].toString(), // claimId
                            claimRow[3].toString(), // type
                            claimRow[4].toString(), // text
                            claimRow[5].toString(), // confidence
                            claimRow[6].toString()  // rationale
                        );

                        if (evidenceData != null) {
                            for (int j = 0; j < evidenceData.length; j++) {
                                Object[] evRow = (Object[]) evidenceData[j];
                                
                                String claimIdFromEvidence = evRow[0].toString();

                                if (claimIdFromEvidence.equals(currentClaimId)) {
                                    c.addEvidence(new Evidence(
                                        evRow[1].toString(), // id
                                        evRow[2].toString(), // type
                                        evRow[3].toString(), // issuer
                                        evRow[4].toString(), // date/data
                                        evRow[5].toString(), // summary
                                        evRow[6].toString()  // fileReference
                                    ));
                                }
                            }
                        }
                        p.addClaim(c);
                    }
                    model.addAttribute("hasClaims2", true);     
                    model.addAttribute("claimsList2", p.getClaims());                }
                else {
                    model.addAttribute("hasClaims2", false);
                }
            } else {
                model.addAttribute("productFound2", false);
                System.out.println("No product found with ID: " + userInput2);
                model.addAttribute("errorMessage2", "Invalid Product ID. Please try again with a valid ID.");
            }
        }
        else {
            model.addAttribute("productFound2", false);
            model.addAttribute("errorMessage2", "No input provided.");
        }
        
        model.addAttribute("mission", mission);
        model.addAttribute("userInput", userInput);
        model.addAttribute("userInput2", userInput2);
        
        return "compare";
    }

}