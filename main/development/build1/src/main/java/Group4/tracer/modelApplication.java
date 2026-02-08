package Group4.tracer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Group4.tracer.model.Claims;
import Group4.tracer.model.Products;

public class modelApplication {
    public static final String COMMA_DELIMITER = ",";
    public static final String DATASET_PATH = 
        new java.io.File(System.getProperty("user.dir"))
            .getParentFile()
            .getParentFile()
            .getAbsolutePath() + "\\dataset\\";

    public static void main (String[] args) {
        //Example of generating a product class once the record is found
        Products p1 = new Products("P001", "Prod1", "Luxury", "Nike", "desc1");

        //Example of adding all claims related to that product, so the ui can display them all
        System.out.println("--Adding claims to product--");
        p1.addClaimsFromStrings(loadClaims(p1.getProductId()));
        
        //Example of adding all the evidence for a specific claim, once that claim card is pressed
        System.out.println("--Adding evidence to the first claim in product--");
        Claims exampleClaim = p1.getClaimByIndex(0);
        exampleClaim.addEvidenceFromStrings(loadEvidence(exampleClaim.getClaimId()));
        
    }
    public static List<List<String>> loadClaims(String productId) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATASET_PATH + "claims.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                List<String> record = Arrays.asList(values);
                if (record.size() == 6 && record.get(1).equals(productId)) {
                    records.add(record);
                    System.out.printf("Added record: %s to Product %s\n", record.toString(), productId);
                }
            
            }
        } catch (IOException e) {
            System.out.println("Failed to read claims.csv");
        }
        return records;
    }

    public static List<List<String>> loadEvidence(String claimId) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATASET_PATH + "evidence.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                List<String> record = Arrays.asList(values);
                if (record.size() == 7 && record.get(1).equals(claimId)) {
                    records.add(record);
                    System.out.printf("Added record: %s to Claim %s\n", record.toString(), claimId);
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read evidence.csv");
        }
        return records;
    }
}


