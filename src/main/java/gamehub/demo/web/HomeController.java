package gamehub.demo.web;

import gamehub.demo.model.binding.EventBindingModel;
import gamehub.demo.model.binding.UserAddBindingModel;
import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.service.GameEventService;
import gamehub.demo.service.GameService;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")

public class HomeController {
    private final GameEventService gameEventService;
    private final ModelMapper modelMapper;

    public HomeController(GameEventService gameEventService, ModelMapper modelMapper) {
        this.gameEventService = gameEventService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @Secured("USER")
    @GetMapping("/home")
    public String homePage(Model model){
        List <EventBindingModel> events = this.gameEventService.findAll().stream()
                .map(g->this.modelMapper.map(g,EventBindingModel.class))
                .collect(Collectors.toList());

        GameServiceModel mostPlayed=this.gameEventService.mostPlayedGame();
        if(mostPlayed!=null) {
            model.addAttribute("mostPlayed",mostPlayed.getGameName().name());
        }
        model.addAttribute("events",events);
        return "home";
    }
    //TODO add scheduler functionality
   /* @Scheduled(fixedRate = 1000)
    public ModelAndView updateGameInfo(){

        ModelAndView modelAndView=new ModelAndView();
        List <EventBindingModel> events = this.gameEventService.findAll().stream()
                .map(g->this.modelMapper.map(g,EventBindingModel.class))
                .collect(Collectors.toList());

        GameServiceModel mostPlayed=this.gameEventService.mostPlayedGame();
        if(mostPlayed!=null) {
            modelAndView.addObject("mostPlayed",mostPlayed.getGameName().name());
        }
        modelAndView.addObject("events",events);
        modelAndView.setViewName("home");
        return modelAndView;
    }*/

}
