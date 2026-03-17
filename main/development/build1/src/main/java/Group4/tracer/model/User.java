package Group4.tracer.model;

import Group4.tracer.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_users")
public class User {
    @Id
    private String userId;

    @Column(name = "\"user_name\"")
    private String username;

    @Column(name = "\"password\"")
    private String password;

    @Column(name = "\"user_type\"")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "score")
    private int score;

    @Column(name = "questions")
    private String missions;

    public User() {
    }

    //getters and setters
    public String getUserId() {
        return userId;
    }
    public void setUserId(String Id) {
        userId = Id;
    }
    public String getUserName() {
        return username;
    }
    public void setUserName(String name) {
        username = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserType getUserType() {
        return userType;
    }
    public String getUserTypeText() {
        if (userType == null) {
            return "null";
        }
        return userType.name();
    }
    public void setUserType(UserType type) {
        userType = type;
    }

    public final void setUserTypeString(String userType) {
        for (UserType type : UserType.values()) {
            if (type.name().equalsIgnoreCase(userType)) {
                this.userType = type;
                return;
            }
        }
        this.userType = null;
    }
    public int getScore() {
        return score;
    }

    public String[] getMissions() {
        return missions.split(",");
    }
    
}
