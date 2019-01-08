package Shared.Messages;

public class CheckGameInMessage {
    private String userName;
    private String lobbyId;

    public CheckGameInMessage(String userName, String lobbyId){
        this.userName = userName;
        this.lobbyId = lobbyId;
    }

    public String getUserName() {
        return userName;
    }

    public String getLobbyId() {
        return lobbyId;
    }
}
