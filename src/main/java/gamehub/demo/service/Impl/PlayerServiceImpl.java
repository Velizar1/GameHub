package gamehub.demo.service.Impl;

import gamehub.demo.model.entity.Player;
import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.service.PlayerServiceModel;
import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.repository.PlayerRepository;
import gamehub.demo.service.PlayerService;
import gamehub.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, UserService userService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public PlayerServiceModel findByUsername(String username,UserServiceModel user, String nameInGame) {
        PlayerServiceModel player= this.playerRepository
                .findByUsernameInGame(username)
                .map(p->this.modelMapper
                .map(p,PlayerServiceModel.class))
                .orElse(null);

        if(player==null){
            player=new PlayerServiceModel();
            player.setUser(user);
            player.setUsernameInGame(String.valueOf(nameInGame));
            addPlayer(player);
            return this.playerRepository
                    .findByUsernameInGame(username)
                    .map(p->this.modelMapper
                            .map(p,PlayerServiceModel.class))
                    .orElse(null);
        }
        return player;
    }

    @Override
    public PlayerServiceModel findByUsername(String username) {
        return this.playerRepository.findByUsernameInGame(username)
                .map(u->this.modelMapper.map(u,PlayerServiceModel.class))
                .orElse(null);
    }

    @Override
    public void addPlayer(PlayerServiceModel player) {
        this.playerRepository.saveAndFlush(this.modelMapper.map(player, Player.class));
    }

}
