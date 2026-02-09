package Group4.tracer;

import java.util.Random;
import java.util.Scanner;

/**
 * Complete Traceability Question System for Coursework 1
 * Contains all required functionality in one file
 */
public class TraceabilityQuestionSystem {
    
    // ==================== DATA STRUCTURE ====================
    
    /**
     * Inner class representing a Product
     */
    static class Product {
        private String id;
        private String name;
        private String description;
        private String evidenceLink;
        
        // Traceability timeline values
        private String rawMaterials;
        private String processing;
        private String assembly;
        private String transport;
        private String retail;
        
        public Product(String id, String name, String description, String evidenceLink,
                      String rawMaterials, String processing, String assembly, 
                      String transport, String retail) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.evidenceLink = evidenceLink;
            this.rawMaterials = rawMaterials;
            this.processing = processing;
            this.assembly = assembly;
            this.transport = transport;
            this.retail = retail;
        }
        
        // Getters for product
        public String getId() { return id; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public String getEvidenceLink() { return evidenceLink; }
        
        // Get traceability value by topic
        public String getTraceabilityValue(String topic) {
            switch (topic.toLowerCase()) {
                case "raw materials": return rawMaterials;
                case "processing": return processing;
                case "assembly": return assembly;
                case "transport": return transport;
                case "retail": return retail;
                default: return "Unknown topic";
            }
        }
        
        // Display product info
        public void displayInfo() {
            System.out.println("\n=== Product Information ===");
            System.out.println("ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Description: " + description);
            System.out.println("Evidence Link: " + evidenceLink);
        }
    }
    
    // ==================== QUESTION ARRAY/DATA FILE ====================
    
    // Array of questions/products following the specified structure
    private static final Product[] PRODUCTS = {
        // Product ID: 001 - Olive Oil
        new Product(
            "001",
            "Olive Oil",
            "Extra virgin olive oil from Italy",
            "https://trace.example.com/products/001-olive-oil",
            "Olives, Water, Salt",
            "Cold pressing and filtering",
            "Bottling plant in Tuscany",
            "Refrigerated truck transport",
            "Organic Grocery Store"
        ),
        
        // Product ID: 002 - Organic Cotton T-Shirt
        new Product(
            "002",
            "Organic Cotton T-Shirt",
            "100% organic cotton, fair trade certified",
            "https://trace.example.com/products/002-cotton-tshirt",
            "Organic cotton, Natural dyes, Recycled thread",
            "Spinning and weaving",
            "Cut and sew factory in Bangladesh",
            "Sea freight container shipping",
            "Eco Fashion Retailer"
        ),
        
        // Product ID: 003 - Fair Trade Coffee
        new Product(
            "003",
            "Fair Trade Coffee",
            "Arabica coffee beans from Colombia",
            "https://trace.example.com/products/003-coffee",
            "Coffee beans, Paper filter, Biodegradable packaging",
            "Roasting and grinding",
            "Packaging facility in Bogota",
            "Air freight to distribution center",
            "Specialty Coffee Shop"
        ),
        
        // Product ID: 004 - Smartphone
        new Product(
            "004",
            "Eco Smartphone",
            "Modular smartphone with ethical components",
            "https://trace.example.com/products/004-smartphone",
            "Recycled aluminum, Conflict-free minerals, Bioplastic",
            "PCB assembly and component soldering",
            "Assembly factory in Germany",
            "Carbon-neutral air freight",
            "Electronics Retail Store"
        ),
        
        // Product ID: 005 - Chocolate Bar
        new Product(
            "005",
            "Dark Chocolate Bar",
            "70% cocoa dark chocolate, fair trade",
            "https://trace.example.com/products/005-chocolate",
            "Cocoa beans, Sugar, Cocoa butter, Vanilla",
            "Fermenting, Roasting, Conching",
            "Chocolate molding and wrapping facility",
            "Refrigerated transport",
            "Specialty Food Store"
        )
    };
    
    // Traceability topics array
    private static final String[] TRACEABILITY_TOPICS = {
        "raw materials",
        "processing", 
        "assembly",
        "transport",
        "retail"
    };
    
    // ==================== CORE FUNCTIONS ====================
    
    private Random random = new Random();
    
    /**
     * Step 1: Randomly select a product ID from the array
     */
    public Product randomlySelectProduct() {
        int randomIndex = random.nextInt(PRODUCTS.length);
        return PRODUCTS[randomIndex];
    }
    
    /**
     * Getter for product by ID
     */
    public Product getProductById(String id) {
        for (Product product : PRODUCTS) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
    
    /**
     * Randomly generate a question topic
     */
    public String randomlyGenerateTopic() {
        int randomIndex = random.nextInt(TRACEABILITY_TOPICS.length);
        return TRACEABILITY_TOPICS[randomIndex];
    }
    
    /**
     * Create a question following the specified structure
     */
    public String generateQuestion(Product product, String topic) {
        return "What is the traceability timeline value for '" + topic + 
               "' in the product: " + product.getName() + 
               " (Product ID: " + product.getId() + ")?";
    }
    
    /**
     * Verification function to verify answer matches user input
     */
    public boolean verifyAnswer(Product product, String topic, String userAnswer) {
        String correctAnswer = product.getTraceabilityValue(topic);
        // Case-insensitive comparison, trim whitespace
        return userAnswer.trim().equalsIgnoreCase(correctAnswer.trim());
    }
    
    /**
     * Create answer function to display results
     * Returns formatted string for UI
     */
    public String getAnswerResult(Product product, String topic, String userAnswer) {
        String correctAnswer = product.getTraceabilityValue(topic);
        boolean isCorrect = verifyAnswer(product, topic, userAnswer);
        
        if (isCorrect) {
            return "✅ Correct! The traceability value for '" + topic + 
                   "' in " + product.getName() + " is: " + correctAnswer;
        } else {
            return "❌ Incorrect\n" +
                   "- User answer: " + userAnswer + "\n" +
                   "- Correct answer: " + correctAnswer + "\n" +
                   "- Evidence: " + product.getEvidenceLink();
        }
    }
    
    // ==================== DEMO/MAIN METHOD ====================
    
    /**
     * Main method to demonstrate the complete system
     */
    public static void main(String[] args) {
        TraceabilityQuestionSystem system = new TraceabilityQuestionSystem();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==============================================");
        System.out.println("   TRACEABILITY TIMELINE QUESTION SYSTEM");
        System.out.println("==============================================\n");
        
        // Step 1: Randomly select a product
        Product selectedProduct = system.randomlySelectProduct();
        System.out.println("Randomly selected product:");
        selectedProduct.displayInfo();
        
        // Step 2: Randomly generate a question topic
        String selectedTopic = system.randomlyGenerateTopic();
        System.out.println("\nRandomly selected topic: " + selectedTopic);
        
        // Step 3: Generate the question
        String question = system.generateQuestion(selectedProduct, selectedTopic);
        System.out.println("\n=== GENERATED QUESTION ===");
        System.out.println(question);
        
        // Get user input
        System.out.print("\nYour answer: ");
        String userAnswer = scanner.nextLine();
        
        // Verify and display results
        System.out.println("\n=== RESULT ===");
        String result = system.getAnswerResult(selectedProduct, selectedTopic, userAnswer);
        System.out.println(result);
        
        // Optional: Show what the verification function returns
        System.out.println("\n=== VERIFICATION FUNCTION OUTPUT ===");
        boolean isCorrect = system.verifyAnswer(selectedProduct, selectedTopic, userAnswer);
        System.out.println("verifyAnswer() returned: " + isCorrect);
        
        scanner.close();
        
        System.out.println("\n==============================================");
        System.out.println("            SYSTEM DEMO COMPLETE");
        System.out.println("==============================================");
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Display all products in the system (for debugging)
     */
    public void displayAllProducts() {
        System.out.println("\n=== ALL PRODUCTS IN SYSTEM ===");
        for (Product product : PRODUCTS) {
            System.out.println("\nProduct ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Raw Materials: " + product.getTraceabilityValue("raw materials"));
            System.out.println("Processing: " + product.getTraceabilityValue("processing"));
            System.out.println("Assembly: " + product.getTraceabilityValue("assembly"));
            System.out.println("Transport: " + product.getTraceabilityValue("transport"));
            System.out.println("Retail: " + product.getTraceabilityValue("retail"));
        }
    }
    
    /**
     * Display all traceability topics
     */
    public void displayAllTopics() {
        System.out.println("\n=== AVAILABLE TRACEABILITY TOPICS ===");
        for (String topic : TRACEABILITY_TOPICS) {
            System.out.println("- " + topic);
        }
    }
}