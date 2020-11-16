package gamehub.demo.model.entity;

import java.util.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@Table(name = "game_events")
public class GameEvent extends BaseEntity {
    private GameName gameName;
    private String description;
    private Integer numberOfPlayers;
    private String devision;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    @ManyToOne
    private User owner;
    @ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> players;

    public GameEvent() {
        players = new HashSet<User>();
    }

    public Set<User> getPlayers() {
        return players;
    }

    public void setPlayers(Set<User> players) {
        this.players = players;
    }
    @Column(name = "name",nullable = false)
    public GameName getGameName() {
        return gameName;
    }

    public void setGameName(GameName gameName) {
        this.gameName = gameName;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "players",nullable = false)
    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Column(name = "devision",nullable = false)
    public String getDevision() {
        return devision;
    }

    public void setDevision(String devision) {
        this.devision = devision;
    }

    @Column(name = "start_date",nullable = false)
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startsOn) {
        this.startDate = startsOn;
    }

    @Column(name = "due_date",nullable = false)
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }
}
