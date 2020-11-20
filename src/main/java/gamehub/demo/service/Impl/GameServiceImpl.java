package gamehub.demo.service.Impl;

import gamehub.demo.model.binding.GameAddBindingModel;
import gamehub.demo.model.entity.Game;
import gamehub.demo.model.entity.GameEvent;
import gamehub.demo.model.entity.GameName;
import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.model.view.EventViewModel;
import gamehub.demo.repository.GameEventRepository;
import gamehub.demo.repository.GameRepository;
import gamehub.demo.service.GameService;
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

    public GameServiceImpl(UserService userService, GameRepository gameService, ModelMapper modelMapper, GameEventRepository gameEventRepository) {
        this.userService = userService;
        this.gameRepository = gameService;

        this.modelMapper = modelMapper;
        this.gameEventRepository = gameEventRepository;
    }

   @Override
    public void InitGames() {
        if(this.gameRepository.count()==0) {
            Arrays.stream(GameName.values())
                    .forEach(g -> {
                        this.gameRepository
                                .save(new Game(g, g.getImage()));
                    });
        }
    }

    @Override
    public void addEvent(GameAddServiceModel gameAddServiceModel, HttpSession httpSession) {
        UserServiceModel userServiceModel =
                this.userService.findByUsername(String.valueOf(httpSession.getAttribute("username")));

        GameServiceModel gameServiceModel=
                this.gameRepository
                        .findByGameName(gameAddServiceModel.getGame().getGameName())
                .map(g->this.modelMapper.map(g,GameServiceModel.class))
                .orElse(null);

        if(userServiceModel!=null && gameServiceModel!=null){
            gameAddServiceModel.setGame(gameServiceModel);
            gameAddServiceModel.setOwner(userServiceModel);
            this.gameEventRepository.saveAndFlush(this.modelMapper
                    .map(gameAddServiceModel, GameEvent.class)
            );
        }
    }

    @Override
    public List<GameAddServiceModel> findAll() {
        return this.gameEventRepository
                .findAll()
                .stream()
                .map(g -> this.modelMapper.map(g, GameAddServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public EventViewModel findById(String id) {
         return this.gameEventRepository
                 .findById(id)
                 .map(x->this.modelMapper.map(x,EventViewModel.class))
                 .orElse(null);

    }
}
