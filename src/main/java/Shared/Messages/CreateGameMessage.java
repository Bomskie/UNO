package Shared.Messages;

import Shared.Models.User;

public class CreateGameMessage {
    private String userName;

    public CreateGameMessage(String userName){
        this.userName = userName;
    }

    public String getUser() {
        return userName;
    }
}
