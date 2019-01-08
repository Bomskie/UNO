package Shared.Models;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private List<String> users;

    public Lobby(){
        users = new ArrayList<>();
    }

    public void addUserToLobby(String userName){
        users.add(userName);
    }

    public void removeUserFromLobby(User user){
        users.remove(user.getUsername());
    }

    public List<String> getUsers(){
        return users;
    }

    public String getUsersAsString(){
        String userString = "";
        for (String s :users){
            userString = userString + s + "|";
        }
        return userString;
    }
}
