package Group4.tracer.model;

import Group4.tracer.enums.StageType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Stages")

public class Stages {
    @Id
    private String stageId;
    @Enumerated(EnumType.STRING)
    private StageType stageName;
    private String location;
    private String startDate;
    private String endDate;
    private String description;

    public Stages() {
    }

    public Stages(String stageId, String stageName, String location, String startDate, String endDate, String description) {
        this.stageId = stageId;
        setStageTypeString(stageName);
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }
    //getters and setters
    public String getStageId() {
        return stageId;
    }
    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    public StageType getStageType() {
        return stageName;
    }
    public String getStageTypeText() {
        if (stageName == null) {
            return "null";
        }
        return this.stageName.getText();
    }
    public final void setStageTypeString(String stageType) {
        for (StageType label : StageType.values()) {
            if (label.name().equalsIgnoreCase(stageType)) {
                this.stageName = label;  
                return;
            }
        }
        this.stageName = null;
    }
    public void setStageType(StageType stageType) {
        stageName = stageType;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}