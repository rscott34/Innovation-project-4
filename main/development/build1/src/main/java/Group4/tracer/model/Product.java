package Group4.tracer.model;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import Group4.tracer.enums.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    private String productId;
    private String name;
    private ProductType category;
    private String brand;
    private String description;
    private List<InputShare> components;
    private List<Stage> stages;
    private List<Claim> claims;

    public Product(String productId, String name, String category, String brand, String description) {
        this.productId = productId;
        this.name = name;
        this.brand = brand;
        this.description = description;
        setCategoryString(category);
    }

    public void addClaimsFromStrings(List<List<String>> claimRecords) {
        for (int i = 0; i < claimRecords.size(); i++) {
            List<String> current = claimRecords.get(i);
            this.addClaim(new Claim(current.get(0), current.get(2), current.get(3), current.get(4), current.get(5)));
        }
    }

    public void addComponent(InputShare item) {
        if (item == null)
            throw new IllegalArgumentException("InputShare cannot be null");
        if (components == null)
            components = new ArrayList<>();
        if (getTotalPerc() + item.getPercentage() > 100)
            throw new IllegalArgumentException("Adding component would valid percentage limits (0-100%)");
        components.add(item);
    }

    public void removeComponent(String inputId) {
        for (InputShare item : components) {
            if (item.getInputId().equals(inputId)) {
                components.remove(item);
                return;
            }
        }
    }
    public InputShare getComponent (String inputId) {
        for (InputShare item : components) {
            if (item.getInputId().equals(inputId))
                return item;
        }
        throw new NoSuchElementException("Input not found: " + inputId);
    }

    public void addClaim(Claim item) {
        if (item == null)
            throw new IllegalArgumentException("Claim cannot be null");
        if (claims == null)
            claims = new ArrayList<>();
        claims.add(item);
    }

    public void removeClaim(String claimId) {
        for (Claim item : claims) {
            if (item.getClaimId().equals(claimId)) {
                claims.remove(item);
                return;
            }
        }
    }

    public Claim getClaim(String claimId) {
        for (Claim item : claims) {
            if (item.getClaimId().equals(claimId))
                return item;
        }
        throw new NoSuchElementException("Claim not found: " + claimId);
    }

    public Claim getClaimByIndex(int index) {
        return claims.get(index);
    }

    public void addStage(Stage item) {
        if (item == null)
            throw new IllegalArgumentException("Stage cannot be null");
        if (stages == null)
            stages = new ArrayList<>();
        stages.add(item);
    }

    public void removeStage(String stageId) {
        for (Stage item : stages) {
            if (item.getStageId().equals(stageId)) {
                stages.remove(item);
                return;
            }
        }
    }

    public Stage getStage(String stageId) {
        for (Stage item : stages) {
            if (item.getStageId().equals(stageId))
                return item;
        }
        throw new NoSuchElementException("Input not found: " + stageId);
    }

    public float getTotalPerc() {
        if (components == null)
            return 0;
        float total = 0;
        for (InputShare input : components) {
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