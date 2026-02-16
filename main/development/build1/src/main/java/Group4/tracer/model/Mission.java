package Group4.tracer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "QuestMission")
public class Mission {
    @Id
    private String mission_id; //primary key of table
    private String product_id;
    private String tier;
    private String question;
    private String answer_key;
    private String grading_type;
    private String explanation_link;

    public Mission() {
    }
    public Mission(String mission_id, String product_id, String tier, String question, String answer, String grading_type, String explanation_link) {
        this.mission_id = mission_id;
        this.product_id = product_id;
        this.tier = tier;
        this.question = question;
        this.answer_key = answer;
        this.grading_type = grading_type;
        this.explanation_link = explanation_link;
    }

    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer_key;
    }
    public String getExplanation() {
        return explanation_link;
    }
}