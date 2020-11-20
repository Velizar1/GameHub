package gamehub.demo.service;
import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.view.EventViewModel;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface GameService {
    void InitGames();

    void addEvent(GameAddServiceModel map, HttpSession httpSession);


    List<GameAddServiceModel> findAll();

    EventViewModel findById(String id);
}
