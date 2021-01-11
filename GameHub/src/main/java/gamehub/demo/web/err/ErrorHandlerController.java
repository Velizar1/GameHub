package gamehub.demo.web.err;

import gamehub.demo.error.GameEventDetailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class ErrorHandlerController {

    //this annotation is used in Rest
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({GameEventDetailException.class})
    public ModelAndView handleEventException(GameEventDetailException ex){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("game-event-err");
        modelAndView.addObject("err",ex.getMessage());

        return modelAndView;
    }
}
