package gamehub.demo.model.binding;


import gamehub.demo.model.entity.GameName;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

public class GameAddBindingModel {
    @NotNull(message = "Enter valid game name")
    private GameName gameName;
    @Length(min = 2,max = 10,message = "Username must be between 2 and 10 symbols.")
    private String userName;
    @Length(min = 2,max = 10,message = "Devision must be between 2 and 10 symbols.")
    private String devision;

    @DecimalMin(value = "1",message = "Enter number of players")
    private Integer numberOfPlayers;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The day must not be in the past")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The day must not be in the past")
    private LocalDateTime dueDate;
    @Length(max = 30,message = "Description must be max 30 symbols")
    private String description;

    public GameAddBindingModel() {
    }

    public GameName getGameName() {
        return gameName;
    }

    public void setGameName(GameName gameName) {
        this.gameName = gameName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDevision() {
        return devision;
    }

    public void setDevision(String devision) {
        this.devision = devision;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }


    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
