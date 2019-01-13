package shared.messages;

public class CreateGameMessage {
    private String userName;

    public CreateGameMessage(String userName){
        this.userName = userName;
    }

    public String getUser() {
        return userName;
    }
}
