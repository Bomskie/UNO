package shared.messages;

public class FinishedGameResultMessage {
    private String userName;

    public FinishedGameResultMessage(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
