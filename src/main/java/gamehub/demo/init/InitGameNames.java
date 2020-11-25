package gamehub.demo.init;

import gamehub.demo.service.GameEventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitGameNames implements CommandLineRunner {

    private final GameEventService gameEventService;

    public InitGameNames(GameEventService gameEventService) {
        this.gameEventService = gameEventService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.gameEventService.InitGames();
    }
}
