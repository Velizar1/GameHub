package gamehub.demo.model.binding;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gamehub.demo.commons.validator.FiledMatch;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@FiledMatch(firstFiled = "password",secondField = "confirmPassword",message = "The passwords do not match")
public class UserAddBindingModel {
    @Length(min = 2,max = 10,message = "Username must be between 2 and 10 symbols.")
    private String userName;
    @NotNull
    @Length(min = 2,max = 10,message = "Password must be between 2 and 10 symbols.")
    private String password;

    private String confirmPassword;
    @Email
    private String email;

    public UserAddBindingModel() {
    }

    public String getUserName() {
        return userName;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
}
