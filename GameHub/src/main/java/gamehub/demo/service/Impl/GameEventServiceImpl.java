package gamehub.demo.service.Impl;

import gamehub.demo.model.entity.Game;
import gamehub.demo.model.entity.GameEvent;
import gamehub.demo.model.entity.GameName;
import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.model.service.PlayerServiceModel;
import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.model.view.EventViewModel;
import gamehub.demo.repository.GameEventRepository;
import gamehub.demo.repository.GameRepository;
import gamehub.demo.service.GameEventService;
import gamehub.demo.service.PlayerService;
import gamehub.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameEventServiceImpl implements GameEventService {


    private final UserService userService;
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final GameEventRepository gameEventRepository;
    private final PlayerService playerService;
    private final Logger LOGGER= LoggerFactory.getLogger(GameEventServiceImpl.class);

    public GameEventServiceImpl(UserService userService, GameRepository gameService, ModelMapper modelMapper, GameEventRepository gameEventRepository, PlayerService playerService) {
        this.userService = userService;
        this.gameRepository = gameService;

        this.modelMapper = modelMapper;
        this.gameEventRepository = gameEventRepository;
        this.playerService = playerService;
    }

    @Override
    public void InitGames() {
        if (this.gameRepository.count() == 0) {
            Arrays.stream(GameName.values())
                    .forEach(g -> {
                        this.gameRepository
                                .save(new Game(g, g.getImage()));
                    });
        }
    }

    @Override
    public void addEvent(GameAddServiceModel gameAddServiceModel, HttpSession httpSession) {
        UserServiceModel user = this.userService.findByUsername(String.valueOf(httpSession.getAttribute("user")));
        PlayerServiceModel player = null;

        if (user != null) {
            player = this.playerService.findByUsernameAndUser(user, String.valueOf(httpSession.getAttribute("usernameInGame")));
        } else return;

        GameServiceModel gameServiceModel =
                this.gameRepository
                        .findByGameName(gameAddServiceModel.getGame().getGameName())
                        .map(g -> this.modelMapper.map(g, GameServiceModel.class))
                        .orElse(null);

        if (gameServiceModel != null) {
            gameAddServiceModel.setGame(gameServiceModel);
            gameAddServiceModel.setOwner(player);

            updateRelations(gameAddServiceModel, player);
        }
    }

    @Override
    public boolean updateRelations(GameAddServiceModel gameAddServiceModel, PlayerServiceModel player) {
        if(gameAddServiceModel.getPlayers().stream()
                .filter(x->x.getUsernameInGame().equals(player.getUsernameInGame()))
                .count()==0){
            gameAddServiceModel.getPlayers().add(player);
            GameAddServiceModel gameAddServiceModel1 = this.modelMapper
                    .map(this.gameEventRepository.saveAndFlush(this.modelMapper
                            .map(gameAddServiceModel, GameEvent.class)), GameAddServiceModel.class);

            player.getGameEvents().add(gameAddServiceModel1);
            this.playerService.addPlayer(player);
            return true;
        }

       /* gameAddServiceModel = this.modelMapper.map(this.gameEventRepository
                .findById(gameAddServiceModel1.getId()), GameAddServiceModel.class);*/
        return false;
    }


    @Override
    @Cacheable("events")
    public List<EventViewModel> findAll() {
        LOGGER.info("cached events");
        return this.gameEventRepository
                .findAll()
                .stream()
                .map(g -> this.modelMapper.map(g, EventViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    @CachePut("events")
    public List<EventViewModel> updateCacheEvents(){
        LOGGER.info("updated cached");
        return findAll();
    }
    @Override
    public List<EventViewModel> findAllByGame(GameServiceModel game) {
        return this.gameEventRepository.findAllByGame(this.modelMapper
                .map(game,Game.class)).stream()
                .map(x->this.modelMapper.map(x,EventViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public EventViewModel findById(String id) {
        return this.gameEventRepository
                .findById(id)
                .map(x -> this.modelMapper.map(x, EventViewModel.class))
                .orElse(null);

    }
    @Override
    public GameServiceModel mostPlayedGame() {

        return gameEventRepository.getMostPlayedGame(PageRequest.of(0,1)).get(0)
                .map(g->this.modelMapper.map(g,GameServiceModel.class))
                .orElse(null);
    }

    @Override
    public void deleteEvent(EventViewModel event) {
        this.gameEventRepository.delete(this.modelMapper.map(event, GameEvent.class));
    }
}
