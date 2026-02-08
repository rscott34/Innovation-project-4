package Group4.tracer.model;

import java.util.ArrayList;
import java.util.List;

import Group4.tracer.enums.ConfidenceLevel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Claims")
public class Claims {
    @Id
    private String claimId; //primary key of table
    private String claimType;
    private String claimText;
    private ConfidenceLevel confidenceLabel;
    private String rationale;
    private ArrayList<Evidence> evidence;

    public Claims() {
    }

    public Claims(String claimId, String claimType, String claimText, String confidenceLabel, String rationale) {
        this.claimId = claimId;
        this.claimType = claimType;
        this.claimText = claimText;
        this.rationale = rationale;
        setConfidenceLabelString(confidenceLabel);
    }

    public void addEvidenceFromStrings(List<List<String>> evidenceRecords) {
        for (int i = 0; i < evidenceRecords.size(); i++) {
            List<String> current = evidenceRecords.get(i);
            this.addEvidence(new Evidence(current.get(0), current.get(2), current.get(3), current.get(4), current.get(5)));
        }
    }

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
        this.confidenceLabel = confidenceLabel;
    }

    public final void setConfidenceLabelString(String confidenceLabel) {
        for (ConfidenceLevel label : ConfidenceLevel.values()) {
            if (label.name().equalsIgnoreCase(confidenceLabel))
                this.confidenceLabel = label;
            else
                this.confidenceLabel = null;
        }
    }

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