package Group4.tracer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Evidence {
    @Id
    private String evidenceId;
    @ManyToOne
    @JoinColumn(name = "stage_id") //tells ide that stage_id has a many-to-one relation
    private Stages stageLinked = null;
    private String evidenceType;
    private String issuer;
    private String date;
    private String summary;
    private String fileReference;

    public Evidence() {
    }

    public Evidence(String id, String stageLinked, String evidenceType, String issuer, String date, String summary, String fileReference) {
        evidenceId = id;
        this.evidenceType = evidenceType;
        this.issuer = issuer;
        this.date = date;
        this.summary = summary;
        this.fileReference = fileReference;
    }
    
    public Evidence(String id, String evidenceType, String issuer, String date, String summary, String fileReference) {
        evidenceId = id;
        this.evidenceType = evidenceType;
        this.issuer = issuer;
        this.date = date;
        this.summary = summary;
        this.fileReference = fileReference;
    }
    public String getId() {
        return evidenceId;
    }
    public void setId(String id) {
        evidenceId = id;
    }
    public Stages getStage() {
        if (stageLinked == null) {
            throw new IllegalStateException("There is not a stage linked with this piece.");
        }
        return stageLinked;
    }

    //Setters and getters
    public void setStage(Stages stage) {
        stageLinked = stage;
    }
    public String getIssuer() {
        return issuer;
    }
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public void setFileRef(String path) {
        fileReference = path;
    }
    public String getFileRef() {
        return fileReference;
    }
    public String getEvidenceType() {
        return evidenceType;
    }
}
