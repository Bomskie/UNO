package Shared.Messages;

import Shared.Models.Lobby;

import java.util.List;

public class LobbyUpdateMessage {
    private String names;

    public LobbyUpdateMessage(String names){
        this.names = names;
    }

    public String getNames() {
        return names;
    }
}
