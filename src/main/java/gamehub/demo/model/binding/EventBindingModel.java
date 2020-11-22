package gamehub.demo.model.binding;

import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.model.service.PlayerServiceModel;
import gamehub.demo.model.service.UserServiceModel;

import java.time.LocalDateTime;
import java.util.Set;

public class EventBindingModel {
    private String id;
    private String description;
    private Integer numberOfPlayers;
    private String devision;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private GameServiceModel game;
    private PlayerServiceModel owner;
    private Set<PlayerServiceModel> players;

    public EventBindingModel() {
    }

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

    public String getDevision() {
        return devision;
    }

    public void setDevision(String devision) {
        this.devision = devision;
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

    public GameServiceModel getGame() {
        return game;
    }

    public void setGame(GameServiceModel game) {
        this.game = game;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
