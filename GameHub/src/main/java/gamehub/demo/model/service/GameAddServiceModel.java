package gamehub.demo.model.service;

import gamehub.demo.model.entity.Player;

import java.util.Set;

import java.time.LocalDateTime;
import java.util.HashSet;

public class GameAddServiceModel extends BaseServiceModel{
    private String description;
    private Integer numberOfPlayers;
    private String devision;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private GameServiceModel game;
    private PlayerServiceModel owner;
    private Set<PlayerServiceModel> players;

    public GameAddServiceModel() {
        players=new HashSet<>();
    }

    public GameServiceModel getGame() {
        return game;
    }

    public void setGame(GameServiceModel game) {
        this.game = game;
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

    public PlayerServiceModel getOwner() {
        return owner;
    }

    public void setOwner(PlayerServiceModel owner) {
        this.owner = owner;
    }


    public Set<PlayerServiceModel> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerServiceModel> players) {
        this.players = players;
    }
}
