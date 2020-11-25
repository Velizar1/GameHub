package gamehub.demo.model.view;

import gamehub.demo.model.service.GameServiceModel;

import java.time.LocalDateTime;

public class HomeEventViewModel {
    private String id;
    private String description;
    private Integer numberOfPlayers;
    private String devision;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private String ownerName;
    private GameServiceModel game;
    private Integer takenPlaces;

    public HomeEventViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getTakenPlaces() {
        return takenPlaces;
    }

    public void setTakenPlaces(Integer takenPlaces) {
        this.takenPlaces = takenPlaces;
    }
}
