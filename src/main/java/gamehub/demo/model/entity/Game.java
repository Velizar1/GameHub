package gamehub.demo.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="game")
public class Game extends BaseEntity{
    @Enumerated
    @Column(name = "name",nullable = false)
    private GameName gameName;
    @Column(name = "image",nullable = false,unique = true)
    private String imageUrl;

    public Game() {
    }

    public Game(GameName gameName, String imageUrl) {
        this.gameName = gameName;
        this.imageUrl = imageUrl;
    }

    public GameName getGameName() {
        return gameName;
    }

    public void setGameName(GameName gameName) {
        this.gameName = gameName;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
