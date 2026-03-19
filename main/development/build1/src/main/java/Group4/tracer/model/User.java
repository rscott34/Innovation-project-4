package Group4.tracer.model;

import java.util.Map;

import Group4.tracer.enums.Rank;
import Group4.tracer.enums.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
    private String missions = "";

    @Transient
    public Map<Rank, Integer> rankMax = Map.of(
        Rank.Copper, 15,
        Rank.Bronze, 30,
        Rank.Silver, 60,
        Rank.Gold, 90,
        Rank.Platinum, 150,
        Rank.Diamond, 210,
        Rank.Legendary, -1
    );
    @Transient
    public Map<Rank, Integer> rankMin = Map.of(
        Rank.Copper, 0,
        Rank.Bronze, 15,
        Rank.Silver, 30,
        Rank.Gold, 60,
        Rank.Platinum, 90,
        Rank.Diamond, 150,
        Rank.Legendary, 210
    );

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
    public void addToScore(int points) {
        score += points;
    }

    public String getMissions() {
        return missions;
    }
    public void addMission(String id) {
        if (missions.isEmpty()) {
            missions = id;
        } else {
            missions = missions.concat("," + id);
        }
    }
    public void emptyMissions() {
        missions = "";
    }
    public void checkFields() {
        if (missions == null) {
            missions = "";
        }
    }

    
    public Rank getRank() {
        if (score < 15) {
            return Rank.Copper;
        } else if (score < 30) {
            return Rank.Bronze;
        } else if (score < 60) {
            return Rank.Silver;
        } else if (score < 90) {
            return Rank.Gold;
        } else if (score < 150) {
            return Rank.Platinum;
        } else if (score < 210) {
            return Rank.Diamond;
        } else {
            return Rank.Legendary;
        }
    }

}
