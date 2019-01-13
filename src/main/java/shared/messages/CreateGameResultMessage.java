package shared.messages;

public class CreateGameResultMessage {
    private String lobbyId;
    private String error;
    private boolean succes;

    public CreateGameResultMessage(String lobbyId, String error, boolean succes){
        this.lobbyId = lobbyId;
        this.error = error;
        this.succes = succes;
    }

    public String returnErrorMessage() {
        return error;
    }

    public boolean returnSucces() {
        return succes;
    }

    public String returnLobbyId(){
        return lobbyId;
    }
}
