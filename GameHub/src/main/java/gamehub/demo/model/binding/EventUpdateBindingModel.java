package gamehub.demo.model.binding;

import org.hibernate.validator.constraints.Length;

public class EventUpdateBindingModel {
    @Length(min=1,max = 30,message = "Too long user nick!")
    private String userNick;

    public EventUpdateBindingModel() {
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }
}
