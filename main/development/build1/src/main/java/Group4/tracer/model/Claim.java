package Group4.tracer.model;

import Group4.tracer.enums.ClaimType;
import Group4.tracer.enums.ConfidenceLevel;

public class Claim {
    private String claimId;
    private ClaimType claimType;
    private String claimText;
    private ConfidenceLevel confidenceLabel;
    private String rationale;

    //getters and setters
    public String getClaimId() {
        return claimId;
    }
    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }
    public ClaimType getClaimType() {
        return claimType;
    }
    public void setClaimType(ClaimType claimType) {
        this.claimType = claimType;
    }

    public String getClaimText() {
        return claimText;
    }
    public void setClaimText(String claimText) {
        this.claimText = claimText;
    }

    public ConfidenceLevel getConfidenceLabel() {
        return confidenceLabel;
    }
    public void setConfidenceLabel(ConfidenceLevel confidenceLabel) {
        this.confidenceLabel = confidenceLabel;}

    public String getRationale() {
        return rationale;
    }
    public void setRationale(String rationale) {
        this.rationale = rationale;
    }
}