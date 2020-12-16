package gamehub.demo.web;

import gamehub.demo.error.GameEventDetailException;
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
//TODO fix game-detail pages in html(session err)

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
    public String addEvent(Model model){

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
        gameEventService.updateCacheEvents();
        return "redirect:/home";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") String id, Model model){


        EventViewModel event=this.gameEventService.findById(id);

        if(event ==null){
            throw new GameEventDetailException("Event was not found.");
            //return "redirect:/game-crash/error";
        }
        model.addAttribute("event",event);
        return "event-detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id ,HttpSession httpSession){

        EventViewModel event=this.gameEventService.findById(id);
        if(event!=null && event.getOwner().getUser().getUserName().equals(httpSession.getAttribute("user"))){
            this.gameEventService.deleteEvent(event);
            gameEventService.updateCacheEvents();
        }
        return "redirect:/home";
    }
    @PostMapping("/add/player")
    public String addPlayer(@ModelAttribute("eventUpdateBindingModel") EventUpdateBindingModel eventUpdateBindingModel,
                         @RequestParam("id") String id ,RedirectAttributes attributes,
                         HttpSession httpSession){

        if(eventUpdateBindingModel.getUserNick().length()>25){
            attributes.addFlashAttribute("length","Username is too long!");
            return "redirect:/game/detail/?id="+id;
        }

        EventViewModel event=this.gameEventService.findById(id);
        UserServiceModel user = this.userService.findByUsername(String.valueOf(httpSession.getAttribute("user")));
        PlayerServiceModel playerServiceModel=this.playerService
                .findByUsernameAndUser(user,eventUpdateBindingModel.getUserNick());


        if(event!=null && playerServiceModel!=null){

           if(!this.gameEventService.updateRelations(this.modelMapper
            .map(event,GameAddServiceModel.class),playerServiceModel)){
               attributes.addFlashAttribute("taken","Username is already taken for this event!");
           }
            gameEventService.updateCacheEvents();
        }
        return "redirect:/game/detail/?id="+id;
    }
}
