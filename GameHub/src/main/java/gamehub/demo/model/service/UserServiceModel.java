package gamehub.demo.model.service;

import java.util.HashSet;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel {
    private String userName;

    private String password;
    private String email;
    private Boolean deleted;

    public UserServiceModel() {

        this.deleted=false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
