package gamehub.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameEventDetailException extends RuntimeException {
    public GameEventDetailException(String message){
        super(message);
    }
}
