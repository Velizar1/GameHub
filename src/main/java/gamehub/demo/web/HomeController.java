package gamehub.demo.web;

import gamehub.demo.model.binding.EventBindingModel;
import gamehub.demo.model.binding.UserAddBindingModel;
import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.service.GameEventService;
import gamehub.demo.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {
    private final GameEventService gameEventService;

    private final GameService gameService;
    private final ModelMapper modelMapper;

    public HomeController(GameEventService gameEventService, GameService gameService, ModelMapper modelMapper) {
        this.gameEventService = gameEventService;
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession){
        if(httpSession.getAttribute("user")!=null){
            return "redirect:home";
        }
        return "index";
    }


    @GetMapping("/home")
    public String homePage(@ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                                 Model model,HttpSession httpSession){

        if(httpSession.getAttribute("user")==null){
            return "redirect:/";
        }

        List <EventBindingModel> events = this.gameEventService.findAll().stream()
                .map(g->this.modelMapper.map(g,EventBindingModel.class))
                .collect(Collectors.toList());

        model.addAttribute("events",events);
        return "home";
    }

}
