package gamehub.demo.service.Impl;

import gamehub.demo.mapper.UserMapper;
import gamehub.demo.model.entity.Player;
import gamehub.demo.model.service.PlayerServiceModel;
import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.repository.PlayerRepository;
import gamehub.demo.service.PlayerService;
import gamehub.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public PlayerServiceModel findByUsernameAndUser(UserServiceModel user, String nameInGame) {
        PlayerServiceModel player= this.playerRepository
                .findByUsernameInGameAndUser(nameInGame,UserMapper.INSTANCE.userServiceModelToEntity(user))
                .map(p->this.modelMapper
                .map(p,PlayerServiceModel.class))
                .orElse(null);

        if(player==null){
            player=new PlayerServiceModel();
            player.setUser(user);
            player.setUsernameInGame(String.valueOf(nameInGame));
            addPlayer(player);
            return this.playerRepository
                    .findByUsernameInGameAndUser(nameInGame,UserMapper.INSTANCE.userServiceModelToEntity(user))
                    .map(p->this.modelMapper
                            .map(p,PlayerServiceModel.class))
                    .orElse(null);
        }
        return player;
    }

    @Override
    public void addPlayer(PlayerServiceModel player) {
        this.playerRepository.saveAndFlush(this.modelMapper.map(player, Player.class));
    }

}
