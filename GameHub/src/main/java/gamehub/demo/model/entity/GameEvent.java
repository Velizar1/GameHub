package gamehub.demo.model.entity;

import java.util.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@Table(name = "game_events")
public class GameEvent extends BaseEntity {

    private String description;
    @Column(name = "players",nullable = false)
    private Integer numberOfPlayers;
    private String devision;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;
    @ManyToOne(fetch = FetchType.LAZY)
    private Player owner;
    @ManyToMany(targetEntity = Player.class,  fetch = FetchType.LAZY)
    private Set<Player> players;

    public GameEvent() {
        players = new HashSet<Player>();
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player user) {
        this.owner = user;
    }
}
