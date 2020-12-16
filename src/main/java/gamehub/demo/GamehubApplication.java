package gamehub.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
public class GamehubApplication {

    //org.springframework.validation.BindingResult.userAddBindingModel
    public static void main(String[] args) {
        SpringApplication.run(GamehubApplication.class, args);
    }

}
