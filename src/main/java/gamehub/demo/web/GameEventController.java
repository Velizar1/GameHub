package gamehub.demo.web;

import gamehub.demo.model.binding.GameAddBindingModel;
import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.service.GameServiceModel;
import gamehub.demo.service.GameService;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/game")
public class GameEventController {
    private final GameService gameService;
    private final ModelMapper modelMapper;

    public GameEventController(GameService gameService, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addEvent(Model model,HttpSession httpSession){
        if(model.getAttribute("gameAddBindingModel")==null){
            model.addAttribute("gameAddBindingModel",new GameAddBindingModel());
        }
        return "add-event";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("gameAddBindingModel") GameAddBindingModel gameAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpSession httpSession){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("gameAddBindingModel",gameAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.gameAddBindingModel",bindingResult);
            return "redirect:add";
        }
        this.gameService.addEvent(this.modelMapper
                .map(gameAddBindingModel, GameAddServiceModel.class),httpSession);
        return "redirect:/home";
    }
}
