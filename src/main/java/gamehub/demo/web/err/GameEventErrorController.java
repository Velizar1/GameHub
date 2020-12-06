package gamehub.demo.web.err;

import gamehub.demo.error.GameEventDetailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/game-crash")
public class GameEventErrorController {

    @GetMapping("/error")
    public ModelAndView notFound(){
        throw new GameEventDetailException("Event not found!");
    }
}
