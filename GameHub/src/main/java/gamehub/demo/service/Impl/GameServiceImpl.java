package gamehub.demo.service.Impl;

import gamehub.demo.model.entity.GameName;
import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.repository.GameRepository;
import gamehub.demo.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GameServiceModel findGameByName(String name) {
        return this.gameRepository.findByGameName(GameName.valueOf(name))
                .map(g->this.modelMapper.map(g,GameServiceModel.class))
                .orElse(null);
    }
}
