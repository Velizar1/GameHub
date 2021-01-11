package gamehub.demo.service;

import gamehub.demo.model.service.GameServiceModel;

public interface GameService {
    GameServiceModel findGameByName(String name);
}
