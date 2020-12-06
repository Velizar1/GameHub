package gamehub.demo.web.err;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/access-denied")
public class UserErrorController {

    @GetMapping()
    public String blockAccess(Model model) {
        return "redirect:/home";
    }

    @PostMapping()
    public String actionAccess(Model model) {
        return "redirect:/home";
    }
}
