package gamehub.demo.service;
import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.model.service.PlayerServiceModel;
import gamehub.demo.model.view.EventViewModel;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface GameEventService {
    void InitGames();

    void addEvent(GameAddServiceModel map, HttpSession httpSession);

    List<EventViewModel> findAll();

    List<EventViewModel> findAllByGame(GameServiceModel game);

    List<EventViewModel> updateCacheEvents();
    EventViewModel findById(String id);

    GameServiceModel mostPlayedGame();
    boolean updateRelations(GameAddServiceModel gameAddServiceModel, PlayerServiceModel player);

    void deleteEvent(EventViewModel event);
}
