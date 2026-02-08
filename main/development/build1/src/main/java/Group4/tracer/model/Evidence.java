package Group4.tracer.model;

public class Evidence {
    private String evidenceId;
    private Stage stageLinked = null;
    private String issuer;
    private String date;
    private String summary;
    private String fileReference;

    public Evidence(String id, String stageLinked, String issuer, String date, String summary, String fileReference) {
        evidenceId = id;
        this.issuer = issuer;
        this.date = date;
        this.summary = summary;
        this.fileReference = fileReference;
        // **TO DO** - write stageLinked part (later)
    }
    public Evidence(String id, String issuer, String date, String summary, String fileReference) {
        evidenceId = id;
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
    public Stage getStage() {
        if (stageLinked == null) {
            throw new IllegalStateException("There is not a stage linked with this piece.");
        }
        return stageLinked;
    }
    public void setStage(Stage stage) {
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
}
