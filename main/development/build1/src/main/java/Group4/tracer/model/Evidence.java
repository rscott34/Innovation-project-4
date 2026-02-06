package Group4.tracer.model;

public class Evidence {
    private String evidenceId;
    private Stage stageLinked = null;
    private String issuer;
    // **TO DO** date attribute
    private String summary;
    private String fileReference = "This/is/a/file/path";
    // **TO DO** fileReference attribute

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
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public void setFilePath(String path) {
        fileReference = path;
    }
    public String getFilePath() {
        return fileReference;
    }
}
