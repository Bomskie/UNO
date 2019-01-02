package Server.WebSocket;

import Shared.Models.Game;
import Shared.Models.User;

import java.util.List;

public interface IServerMessageGenerator {
    void notifyLoginResult(String sessionId, int error, User user);
    //void notifyRegisterResult(String sessionId, boolean result, String message);
    void notifyCurrentOpengames(String sessionid, List<Game> gameList);
}