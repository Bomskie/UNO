package shared.messages;

import shared.models.User;

public class LoginResultMessage {

    private int error;
    private User user;

    public LoginResultMessage(User user, int error){
        this.error = error;
        this.user = user;
    }

    public int getMessage(){
        return error;
    }

    public void setMessage(String message){
        this.error = error;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}
