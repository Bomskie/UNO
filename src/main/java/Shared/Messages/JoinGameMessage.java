package Shared.Messages;

public class JoinGameMessage {
    private String userName;
    private String lobbyId;

    public JoinGameMessage(String userName, String lobbyId){
        this.userName = userName;
        this.lobbyId = lobbyId;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public String getUserName() {
        return userName;
    }
}
