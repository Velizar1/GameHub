package gamehub.demo.service;

import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.service.PlayerServiceModel;
import gamehub.demo.model.service.UserServiceModel;

import javax.servlet.http.HttpSession;

public interface PlayerService {
    PlayerServiceModel findByUsername(String username, UserServiceModel user, String nameInGame);

    PlayerServiceModel findByUsername(String username);

    void addPlayer(PlayerServiceModel player);
}
