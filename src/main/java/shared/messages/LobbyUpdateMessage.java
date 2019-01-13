package shared.messages;

public class LobbyUpdateMessage {
    private String names;

    public LobbyUpdateMessage(String names){
        this.names = names;
    }

    public String getNames() {
        return names;
    }
}
