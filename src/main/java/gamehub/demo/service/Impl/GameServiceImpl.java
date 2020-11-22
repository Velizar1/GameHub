package gamehub.demo.service.Impl;

import gamehub.demo.model.entity.Game;
import gamehub.demo.model.entity.GameEvent;
import gamehub.demo.model.entity.GameName;
import gamehub.demo.model.entity.Player;
import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.model.service.PlayerServiceModel;
import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.model.view.EventViewModel;
import gamehub.demo.repository.GameEventRepository;
import gamehub.demo.repository.GameRepository;
import gamehub.demo.service.GameService;
import gamehub.demo.service.PlayerService;
import gamehub.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {


    private final UserService userService;
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final GameEventRepository gameEventRepository;
    private final PlayerService playerService;

    public GameServiceImpl(UserService userService, GameRepository gameService, ModelMapper modelMapper, GameEventRepository gameEventRepository, PlayerService playerService) {
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
        UserServiceModel user = this.userService.findByUsername(String.valueOf(httpSession.getAttribute("username")));
        PlayerServiceModel player = null;

        if (user != null) {
            player = this.playerService.findByUsername(String.valueOf(httpSession.getAttribute("usernameInGame")),
                    user, String.valueOf(httpSession.getAttribute("usernameInGame")));
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
    public void saveEvent(GameAddServiceModel gameAddServiceModel, HttpSession httpSession) {
        UserServiceModel user = this.userService.findByUsername(String.valueOf(httpSession.getAttribute("username")));
        PlayerServiceModel player = null;

        if (user != null) {
            player = this.playerService.findByUsername(String.valueOf(httpSession.getAttribute("usernameInGame")),
                    user, String.valueOf(httpSession.getAttribute("usernameInGame")));
        } else return;

        updateRelations(gameAddServiceModel, player);
    }
    @Override
    public void updateRelations(GameAddServiceModel gameAddServiceModel, PlayerServiceModel player) {
        gameAddServiceModel.getPlayers().add(player);
        GameAddServiceModel gameAddServiceModel1 = this.modelMapper
                .map(this.gameEventRepository.saveAndFlush(this.modelMapper
                        .map(gameAddServiceModel, GameEvent.class)), GameAddServiceModel.class);
        gameAddServiceModel = this.modelMapper.map(this.gameEventRepository
                .findById(gameAddServiceModel1.getId()), GameAddServiceModel.class);

        player.getGameEvents().add(gameAddServiceModel);
        this.playerService.addPlayer(player);
    }

    @Override
    public List<EventViewModel> findAll() {
        return this.gameEventRepository
                .findAll()
                .stream()
                .map(g -> this.modelMapper.map(g, EventViewModel.class))
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
    public void deleteEvent(EventViewModel event) {
        this.gameEventRepository.delete(this.modelMapper.map(event, GameEvent.class));
    }
}
