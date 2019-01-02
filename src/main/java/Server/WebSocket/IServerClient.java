package Server.WebSocket;

public interface IServerClient {
    void LoginUser(String Username, String Password,String sessionID);
}