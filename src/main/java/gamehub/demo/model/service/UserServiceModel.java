package gamehub.demo.model.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserServiceModel {
    @NotNull
    @Length(min = 2,max = 10,message = "Username must be between 2 and 10 symbols.")
    private String userName;
    @NotNull
    @Length(min = 2,max = 10,message = "Password must be between 2 and 10 symbols.")
    private String password;
    @Email
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
