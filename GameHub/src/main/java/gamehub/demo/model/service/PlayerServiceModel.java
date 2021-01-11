package gamehub.demo.model.service;

import gamehub.demo.model.entity.GameEvent;
import gamehub.demo.model.entity.User;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

public class PlayerServiceModel  extends BaseServiceModel implements Comparable<PlayerServiceModel>{
    private UserServiceModel user;
    private String usernameInGame;
    private Set<GameAddServiceModel> gameEvents;

    public PlayerServiceModel() {
        gameEvents=new HashSet<>();
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public String getUsernameInGame() {
        return usernameInGame;
    }

    public void setUsernameInGame(String usernameInGame) {
        this.usernameInGame = usernameInGame;
    }

    public Set<GameAddServiceModel> getGameEvents() {
        return gameEvents;
    }

    public void setGameEvents(Set<GameAddServiceModel> gameEvents) {
        this.gameEvents = gameEvents;
    }

    @Override
    public int compareTo(PlayerServiceModel o) {
        return this.getUsernameInGame().compareTo(o.getUsernameInGame());
    }
}
