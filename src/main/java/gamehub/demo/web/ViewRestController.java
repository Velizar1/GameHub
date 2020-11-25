package gamehub.demo.web;

import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.model.view.HomeEventViewModel;
import gamehub.demo.service.GameEventService;
import gamehub.demo.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resource")
public class ViewRestController {

    private final GameEventService gameEventService;
    private final GameService gameService;
    private final ModelMapper modelMapper;

    public ViewRestController(GameEventService gameEventService, GameService gameService, ModelMapper modelMapper) {
        this.gameEventService = gameEventService;
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    ResponseEntity<List<HomeEventViewModel>> resource(@RequestParam("game") String game) {
        List<HomeEventViewModel> events ;
        if(!game.equals("All")) {
            GameServiceModel gameServiceModel = this.gameService.findGameByName(game);
                events=this.gameEventService.findAllByGame(gameServiceModel).stream()
                    .map(
                            e -> {
                                HomeEventViewModel homeEvent = this.modelMapper.map(e, HomeEventViewModel.class);
                                homeEvent.setOwnerName(e.getOwner().getUsernameInGame());
                                homeEvent.setTakenPlaces(e.getNumberOfPlayers() +1 - e.getPlayers().size());
                                return homeEvent;
                            }
                    ).collect(Collectors.toList());
            try {
                return new ResponseEntity<>(events, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        }else {
            events = this.gameEventService.findAll().stream()
                    .map(
                            e -> {
                                HomeEventViewModel homeEvent = this.modelMapper.map(e, HomeEventViewModel.class);
                                homeEvent.setOwnerName(e.getOwner().getUsernameInGame());
                                homeEvent.setTakenPlaces(e.getNumberOfPlayers()-e.getPlayers().size());
                                return homeEvent;
                            }
                    ).collect(Collectors.toList());

            try {
                return new ResponseEntity<>(events, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        }

    }
}
