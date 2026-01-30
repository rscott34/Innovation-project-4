package Group4.tracer.model;

public class Stage {
    private String stageId;
    private String productId;
    private String stageType;
    private String location;
    private String startDate;
    private String endDate;
    private String description;

    //getters and setters
    public String getStageId() { return stageId; }
    public void setStageId(String stageId) { this.stageId = stageId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getStageType() { return stageType; }
    public void setStageType(String stageType) { this.stageType = stageType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}