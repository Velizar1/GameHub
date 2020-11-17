package gamehub.demo.init;

import gamehub.demo.service.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitGameNames implements CommandLineRunner {

    private final GameService gameService;

    public InitGameNames(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.gameService.InitGames();
    }
}
