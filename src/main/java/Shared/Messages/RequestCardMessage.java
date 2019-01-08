package Shared.Messages;

public class RequestCardMessage {
    private String playerName;
    private String lobbyId;

    public RequestCardMessage(String playerName, String lobbyId){
        this.playerName = playerName;
        this.lobbyId = lobbyId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getLobbyId() {
        return lobbyId;
    }
}
