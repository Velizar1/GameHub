package gamehub.demo.web;

import gamehub.demo.model.binding.EventUpdateBindingModel;
import gamehub.demo.model.binding.GameAddBindingModel;
import gamehub.demo.model.service.GameAddServiceModel;
import gamehub.demo.model.service.PlayerServiceModel;
import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.model.view.EventViewModel;
import gamehub.demo.service.GameEventService;
import gamehub.demo.service.PlayerService;
import gamehub.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/game")
public class GameEventController {
    private final GameEventService gameEventService;
    private final PlayerService playerService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public GameEventController(GameEventService gameEventService, PlayerService playerService, UserService userService, ModelMapper modelMapper) {
        this.gameEventService = gameEventService;
        this.playerService = playerService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addEvent(Model model,HttpSession httpSession){
        if(httpSession.getAttribute("user")==null){
            return "redirect:/";
        }
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
        httpSession.setAttribute("usernameInGame",gameAddBindingModel.getUserName());
        this.gameEventService.addEvent(this.modelMapper
                .map(gameAddBindingModel, GameAddServiceModel.class),httpSession);
        return "redirect:/home";
    }
    @GetMapping("/detail")
    public String detail(@RequestParam("id") String id, Model model ,HttpSession httpSession){

        if(httpSession.getAttribute("user")==null){
            return "redirect:/";
        }
        EventViewModel event=this.gameEventService.findById(id);

        model.addAttribute("event",event);
        return "event-detail";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id ,HttpSession httpSession){

        if(httpSession.getAttribute("user")==null){
            return "redirect:/";
        }
        EventViewModel event=this.gameEventService.findById(id);
        if(event!=null && event.getOwner().getUser().getUserName().equals(httpSession.getAttribute("username"))){
            this.gameEventService.deleteEvent(event);
        }
        return "redirect:/home";
    }
    @PostMapping("/add/player")
    public String delete(@ModelAttribute("eventUpdateBindingModel") EventUpdateBindingModel eventUpdateBindingModel,
                         @RequestParam("id") String id,
                         HttpSession httpSession){

        EventViewModel event=this.gameEventService.findById(id);
        UserServiceModel user = this.userService.findByUsername(String.valueOf(httpSession.getAttribute("username")));
        PlayerServiceModel playerServiceModel=this.playerService
                .findByUsername(eventUpdateBindingModel.getUserNick(),user,eventUpdateBindingModel.getUserNick());

        if(event!=null && playerServiceModel!=null){

            this.gameEventService.updateRelations(this.modelMapper
            .map(event,GameAddServiceModel.class),playerServiceModel);
        }
        return "redirect:/game/detail/?id="+id;
    }
}
