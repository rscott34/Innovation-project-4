package Group4.tracer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "verifiers")
public class Verifier {
    
    // Using escaped quotes because PostgreSQL is case-sensitive with these column names
    @Id
    @Column(name = "\"Verifier_ID\"")
    private String verifierId;

    @Column(name = "\"Username\"")
    private String username;

    @Column(name = "\"Password\"")
    private String password;

    // Getters and Setters
    public String getVerifierId() { return verifierId; }
    public void setVerifierId(String verifierId) { this.verifierId = verifierId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
