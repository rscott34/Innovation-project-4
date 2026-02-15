package Group4.tracer.model;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Group4.tracer.enums.ProductType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Products {
    @Id //productId is primary key
    private String productId;
    private String name;

    @Enumerated(EnumType.STRING) //store enum as a string
    private ProductType category;

    private String brand;
    private String description;

    //creates a one-to-many relation between Products table and Input_shares table
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //enforces inheritance
    @JoinColumn(name = "product_id") //creates a foreign key in the InputShare table
    private List<Input_shares> components = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Stages> stages = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Claims> claims = new ArrayList<>();

    public Products(String productId, String name, String category, String brand, String description) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.description = description;
        setCategoryString(category);
    }

    public Products() {
    }

    public void addClaimsFromStrings(List<List<String>> claimRecords) {
        for (int i = 0; i < claimRecords.size(); i++) {
            List<String> current = claimRecords.get(i);
            this.addClaim(new Claims(current.get(0), current.get(2), current.get(3), current.get(4), current.get(5)));
        }
    }

    public void addComponent(Input_shares item) {
        if (item == null)
            throw new IllegalArgumentException("InputShare cannot be null");
        if (components == null)
            components = new ArrayList<>();
        if (getTotalPerc() + item.getPercentage() > 100)
            throw new IllegalArgumentException("Adding component would valid percentage limits (0-100%)");
        components.add(item);
    }

    public void removeComponent(String inputId) {
        for (Input_shares item : components) {
            if (item.getInputId().equals(inputId)) {
                components.remove(item);
                return;
            }
        }
    }
    public Input_shares getComponent (String inputId) {
        for (Input_shares item : components) {
            if (item.getInputId().equals(inputId))
                return item;
        }
        throw new NoSuchElementException("Input not found: " + inputId);
    }

    public void addClaim(Claims item) {
        if (item == null)
            throw new IllegalArgumentException("Claim cannot be null");
        if (claims == null)
            claims = new ArrayList<>();
        claims.add(item);
    }

    public void removeClaim(String claimId) {
        for (Claims item : claims) {
            if (item.getClaimId().equals(claimId)) {
                claims.remove(item);
                return;
            }
        }
    }

    public Claims getClaim(String claimId) {
        for (Claims item : claims) {
            if (item.getClaimId().equals(claimId))
                return item;
        }
        throw new NoSuchElementException("Claim not found: " + claimId);
    }

    public Claims getClaimByIndex(int index) {
        return claims.get(index);
    }

    public void addStage(Stages item) {
        //public String[][] getListOfClaimsDetails () { //original code
        String[][] getListOfClaimsDetails; {
            int i = 0;
            String[][] result = new String[claims.size()][5];
            for (Claims claims : claims) {
                String[] current = new String[5];
                current[0] = claims.getClaimId();
                current[1] = claims.getClaimType();
                current[2] = claims.getClaimText();
                current[3] = claims.getConfidenceLabelText();
                current[4] = claims.getRationale();
                result[i] = current;
                i++;
            }
            //return result; //void method cant return, maybe print instead IDK
        }
    }

/*
    public void addStage(Stages item) {
        if (item == null)
            throw new IllegalArgumentException("Stage cannot be null");
        if (stages == null)
            stages = new ArrayList<>();
        stages.add(item);
    }
*/
    public void removeStage(String stageId) {
        for (Stages item : stages) {
            if (item.getStageId().equals(stageId)) {
                stages.remove(item);
                return;
            }
        }
    }

    public Stages getStage(String stageId) {
        for (Stages item : stages) {
            if (item.getStageId().equals(stageId))
                return item;
        }
        throw new NoSuchElementException("Input not found: " + stageId);
    }

    public float getTotalPerc() {
        if (components == null)
            return 0;
        float total = 0;
        for (Input_shares input : components) {
            total += input.getPercentage();
        }
        return total;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ProductType getCategory() {
        return category;
    }
    public void setCategory(ProductType category) {
        this.category = category;
    }

    public final void setCategoryString(String category) {
        for (ProductType type : ProductType.values()) {
            if (type.name().equalsIgnoreCase(category))
                this.category = type;
            else
                this.category = null;
        }
    }
    

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}