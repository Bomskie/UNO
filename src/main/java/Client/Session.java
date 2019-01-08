package Client;

public class Session {
    private static Session s = new Session();
    private String userName = "";
    private String currentLobby = "";
    private Session(){

    }

    public static Session getInstance(){return s;}

    public void setUserName(String userName){this.userName = userName;}
    public void setCurrentLobby(String lobbyId){this.currentLobby = lobbyId;}
    public String getCurrentLobby(){return currentLobby;}
    public String getUserName() {return userName;}
}
