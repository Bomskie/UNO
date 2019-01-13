package shared.messages;

public class FinishedGameMessage {
    private String userName;
    private String lobbyId;

    public FinishedGameMessage(String userName, String lobbyId){
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
