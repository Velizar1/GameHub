package gamehub.demo.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="players")
public class Player extends BaseEntity {
    @ManyToOne()
    private  User user;
    @Column(name = "username",unique = true,nullable = false)
    private  String usernameInGame;
    @ManyToMany(mappedBy = "players", targetEntity = GameEvent.class, fetch = FetchType.LAZY)
    private Set<GameEvent> gameEvents;

    public Player() {
        gameEvents=new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<GameEvent> getGameEvents() {
        return gameEvents;
    }

    public void setGameEvents(Set<GameEvent> gameEvents) {
        this.gameEvents = gameEvents;
    }

    public String getUsernameInGame() {
        return usernameInGame;
    }

    public void setUsernameInGame(String usernameInGame) {
        this.usernameInGame = usernameInGame;
    }




}
