package Group4.tracer.model;

public class Claim {
    private String claimId;
    private String productId;
    private String claimType;
    private String claimText;
    private String confidenceLabel;
    private String rationale;

    //getters and setters
    public String getClaimId() { return claimId; }
    public void setClaimId(String claimId) { this.claimId = claimId; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getClaimType() { return claimType; }
    public void setClaimType(String claimType) { this.claimType = claimType; }

    public String getClaimText() { return claimText; }
    public void setClaimText(String claimText) { this.claimText = claimText; }

    public String getConfidenceLabel() { return confidenceLabel; }
    public void setConfidenceLabel(String confidenceLabel) { this.confidenceLabel = confidenceLabel; }

    public String getRationale() { return rationale; }
    public void setRationale(String rationale) { this.rationale = rationale; }
}