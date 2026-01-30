package Group4.tracer.model;

public class InputShare {
    private String inputId;
    private String productId;
    private String inputName;
    private String country;
    private int percentage;
    private String notes;

    //getters and setters
    public String getInputId() { return inputId; }
    public void setInputId(String inputId) { this.inputId = inputId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getInputName() { return inputName; }
    public void setInputName(String inputName) { this.inputName = inputName; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public int getPercentage() { return percentage; }
    public void setPercentage(int percentage) { this.percentage = percentage; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}