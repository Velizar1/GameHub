package gamehub.demo.model.service;

import gamehub.demo.model.entity.GameName;

public class GameServiceModel extends BaseServiceModel{
    private GameName gameName;
    private String imageUrl;

    public GameServiceModel() {
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
