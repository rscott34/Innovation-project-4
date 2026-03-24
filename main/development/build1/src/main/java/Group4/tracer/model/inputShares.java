package Group4.tracer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"inputShares\"")
public class inputShares {

    @Id
    private String inputId; //primary key for table

    @Column(name = "product_id") // This forces it to map correctly to the DB column
    private String productId;
    private String inputName;
    private String country;
    private float percentage; //percentage is stored as int in database, this may need changing
    //private String notes; //not used in database anymore 

    public inputShares() {
    }

    //getters and setters
    public String getInputId() {
        return inputId;
    }
    public void setInputId(String inputId) {
        this.inputId = inputId;
    }
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getInputName() {
        return inputName;
    }
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public float getPercentage() {
        return percentage;
    }
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    // public String getNotes() {
    //     return notes;
    // }
    // public void setNotes(String notes) {
    //     this.notes = notes;
    // }
}