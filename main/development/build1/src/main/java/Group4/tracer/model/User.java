package Group4.tracer.model;

import Group4.tracer.enums.UserType;
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

    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User() {
    }

    public User(String userId, String userName, String password, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        setUserTypeString(userType);
    }


    //getters and setters
    public String getUserId() {
        return userId;
    }
    public void setUserId(String Id) {
        userId = Id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String name) {
        userName = name;
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
}
