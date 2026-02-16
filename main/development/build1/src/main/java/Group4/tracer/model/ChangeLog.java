package Group4.tracer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

//create changelog class with getters and setters 
@Entity
@Table(name = "\"changeLog\"")
public class ChangeLog {
    @Id
    @Column(name = "log_id")
    private String logId;

    @Column(name = "entity_type")
    private String entityType;

    @Column(name = "entity_id")
    private String entityId;

    @Column(name = "changed_by")
    private String changedBy;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "change_summary")
    private String changeSummary;

    // Getters and Setters
    //login id
    public String getLogId() { return logId; }
    public void setLogId(String logId) { this.logId = logId; }
    //entity tye
    public String getEntityType() { return entityType; }

    public void setEntityType(String entityType) { this.entityType = entityType; }
    //entiry ID
    public String getEntityId() { return entityId; }
    public void setEntityId(String entityId) { this.entityId = entityId; }
    //verifier username who changes the details
    public String getChangedBy() { return changedBy; }
    public void setChangedBy(String changedBy) { this.changedBy = changedBy; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public String getChangeSummary() { return changeSummary; }
    public void setChangeSummary(String changeSummary) { this.changeSummary = changeSummary; }
}
