package Group4.tracer.model;

import java.util.ArrayList;
import java.util.List;

import Group4.tracer.enums.ConfidenceLevel;

public class Claim {
    private String claimId;
    private String claimType;
    private String claimText;
    private ConfidenceLevel confidenceLabel;
    private String rationale;
    private ArrayList<Evidence> evidence;

    public Claim (String claimId, String claimType, String claimText, String confidenceLabel, String rationale) {
        this.claimId = claimId;
        this.claimType = claimType;
        this.claimText = claimText;
        this.rationale = rationale;
        setConfidenceLabelString(confidenceLabel);
    }

    public String[][] getListOfEvidenceDetails() {
        int i = 0;
        String[][] result = new String[evidence.size()][5];
        for (Evidence e : evidence) {
            String[] current = new String[5];
            current[0] = e.getId();
            current[1] = e.getIssuer();
            current[2] = e.getDate();
            current[3] = e.getSummary();
            current[4] = e.getFileRef();
            result[i] = current;
            i++;
            // **TO DO** - write stageLinked part (later)
        }
        return result;
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
    public String getConfidenceLabelText() {
        return confidenceLabel.name();
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
    public Evidence getEvidenceByIndex(int index) {
        return evidence.get(index);
    }
    public int getNumEvidence() {
        return evidence.size();
    }
}