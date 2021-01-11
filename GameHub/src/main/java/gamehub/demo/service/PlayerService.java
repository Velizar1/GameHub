package gamehub.demo.service;

import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.service.PlayerServiceModel;
import gamehub.demo.model.service.UserServiceModel;

import javax.servlet.http.HttpSession;

public interface PlayerService {
    PlayerServiceModel findByUsernameAndUser( UserServiceModel user, String nameInGame);


    void addPlayer(PlayerServiceModel player);
}
