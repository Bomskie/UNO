package Shared.Messages;

public class StartGameMessage {
    private String lobbyId;

    public StartGameMessage(String lobbyId){
        this.lobbyId = lobbyId;
    }

    public String getLobbyId() {
        return lobbyId;
    }
}
