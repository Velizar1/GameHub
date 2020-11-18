package gamehub.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameEventController {

    @GetMapping("/add")
    public String addEvent(){
        return "add-event";
    }
}
