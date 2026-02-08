package Group4.tracer.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Input_shares")
public class Input_shares {

    @Id
    private String inputId; //primary key for table
    private String inputName;
    private String country;
    private float percentage; //percentage is stored as int in database, this may need changing
    private String notes;

    public Input_shares() {
    }

    //getters and setters
    public String getInputId() {
        return inputId;
    }
    public void setInputId(String inputId) {
        this.inputId = inputId;
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
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}