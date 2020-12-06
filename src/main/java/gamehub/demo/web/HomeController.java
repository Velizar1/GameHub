package gamehub.demo.web;

import gamehub.demo.model.binding.EventBindingModel;
import gamehub.demo.model.binding.UserAddBindingModel;
import gamehub.demo.service.GameEventService;
import gamehub.demo.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
//TODO fix redirection

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

    @GetMapping("/home")
    public String homePage(@ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                                 Model model){
        List <EventBindingModel> events = this.gameEventService.findAll().stream()
                .map(g->this.modelMapper.map(g,EventBindingModel.class))
                .collect(Collectors.toList());

        model.addAttribute("events",events);
        return "home";
    }

}
