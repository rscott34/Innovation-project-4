package Group4.tracer.model;

import java.util.ArrayList;
import java.util.List;

import Group4.tracer.enums.GradingType;
import Group4.tracer.enums.MissionDifficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "QuestMission")
public class Mission {
    @Id
    private String missionId; //primary key of table
    private String productId;
    private MissionDifficulty tier;
    private String question;
    private String answer;
    private GradingType gradingType;
    private String[] options;
    private String feedback;
    private String anchor;

    public Mission() {
    }
    public Mission(String mission_id, String product_id, String tier, String question, String answer, String grading_type, String options, String feedback, String anchor) {
        this.missionId = mission_id;
        this.productId = product_id;
        setTierString(tier);
        this.question = question;
        this.answer = answer;
        setGradingTypeString(grading_type);
        this.options = options.split(",");
        this.feedback = feedback;
        this.anchor = anchor;
    }

    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
    public String getPassport() {
        return productId;
    }
    public String getExplanation() {
        return feedback;
    }

    public final void setTierString(String tier) {
        for (MissionDifficulty diff : MissionDifficulty.values()) {
            if (diff.name().equalsIgnoreCase(tier)) {
                this.tier = diff;
                return;
            }
        }
        this.tier = null;
    }

    public final void setGradingTypeString(String type) {
        for (GradingType grad : GradingType.values()) {
            if (grad.name().equalsIgnoreCase(type)) {
                this.gradingType = grad;
                return;
            }
        }
        this.tier = null;
    }

    public GradingType getGradingType() {
        return gradingType;
    }
    public String getGradingTypeText() {
        if (gradingType == null) {
            return "null";
        }
        return gradingType.name();
    }

    public MissionDifficulty getDifficulty() {
        return tier;
    }
    public String getDifficultyText() {
        if (tier == null) {
            return "null";
        }
        return tier.name();
    }
    public String getAnchor() {
        return anchor;
    }
    public String[] getOptions() {
        return options;
    }
}