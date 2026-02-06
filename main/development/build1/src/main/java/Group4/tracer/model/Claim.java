package Group4.tracer.model;

import Group4.tracer.enums.ConfidenceLevel;

import java.util.ArrayList;

public class Claim {
    private String claimId;
    private String claimType;
    private String claimText;
    private ConfidenceLevel confidenceLabel;
    private String rationale;
    private ArrayList<Evidence> evidence;

    //getters and setters
    public String getClaimId() {
        return claimId;
    }
    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }
    public String getClaimType() {
        return claimType;
    }
    public void setClaimType(String claimType) {
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

    public void addEvidence(Evidence evidence) {
        if (this.evidence == null)
            this.evidence = new ArrayList<>();
        this.evidence.add(evidence);
    }
    public Evidence getEvidence(int index) {
        return evidence.get(index);
    }
}