package gamehub.demo.model.entity;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class User extends BaseEntity {
    private String userName;
    private String password;
    private String email;
    @ManyToMany(mappedBy = "players", targetEntity = GameEvent.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GameEvent> gameEvents;

    public User() {
        gameEvents=new HashSet<GameEvent>();
    }

    @NotNull
    @Column(name = "usename",unique = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @NotNull
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<GameEvent> getGameEvents() {
        return gameEvents;
    }

    public void setGameEvents(Set<GameEvent> gameEvents) {
        this.gameEvents = gameEvents;
    }
}
