package Shared.Messages;

public class StartGameResultMessage {
    private String lobbyId;
    private String usersAsString;

    public StartGameResultMessage(String lobbyId, String usersAsString){
        this.lobbyId = lobbyId;
        this.usersAsString = usersAsString;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public String getUsersAsString() {
        return usersAsString;
    }
}
