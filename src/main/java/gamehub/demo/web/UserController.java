package gamehub.demo.web;

import gamehub.demo.model.binding.UserAddBindingModel;
import gamehub.demo.model.binding.UserLoginBindingModel;
import gamehub.demo.model.service.UserServiceModel;
import gamehub.demo.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;

        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model,HttpSession httpSession){

        if(httpSession.getAttribute("user")!=null){
            return "redirect:/";
        }
        if(model.getAttribute("userLoginBindingModel")==null){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }
    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult, HttpSession httpSession,
                               RedirectAttributes redirectAttributes){

        UserServiceModel user=this.userService.findByUsername(userLoginBindingModel.getUserName());
        if(bindingResult.hasErrors() || user==null|| !user.getPassword().equals(userLoginBindingModel.getPassword())){
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",bindingResult);
            return "redirect:login";
        }
        userLoginBindingModel=modelMapper.map(user,UserLoginBindingModel.class);
        httpSession.setAttribute("user",userLoginBindingModel);
        httpSession.setAttribute("username",userLoginBindingModel.getUserName());
        return "redirect:/home";
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }


    @GetMapping("/register")
    public String register(Model model,HttpSession httpSession){
        if(httpSession.getAttribute("user")!=null){
            return "redirect:/";
        }
        if(model.getAttribute("userAddBindingModel")==null) {
            model.addAttribute("userAddBindingModel", new UserAddBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()||!userAddBindingModel.getPassword().equals(userAddBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userAddBindingModel",userAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel",bindingResult);
            return "redirect:register";
        }
        UserServiceModel userServiceModel=this.userService.addUser(this.modelMapper
        .map(userAddBindingModel, UserServiceModel.class));
        if(userServiceModel!=null){
            redirectAttributes.addFlashAttribute("userAddBindingModel",userAddBindingModel);
            redirectAttributes.addFlashAttribute("userExist",true);
            return "redirect:register";
        }
        return "redirect:login";
    }


}
