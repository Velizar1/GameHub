package gamehub.demo.service.Impl;

import gamehub.demo.model.entity.Game;
import gamehub.demo.model.entity.GameName;
import gamehub.demo.repository.GameRepository;
import gamehub.demo.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
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
}
