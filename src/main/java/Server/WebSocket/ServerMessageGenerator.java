package Server.WebSocket;

import Shared.Models.Game;
import Shared.Models.User;
import Shared.Messages.CurrentOpenGamesResultMessage;
import Shared.Messages.LoginResultMessage;
import Shared.Messages.RegisterResultMessage;

import java.util.List;

public class ServerMessageGenerator implements IServerMessageGenerator {

    private IServerWebSocket serverSocket;

    public ServerMessageGenerator(IServerWebSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void notifyLoginResult(String sessionId, int error, User user)
    {
        LoginResultMessage msg = new LoginResultMessage(user,error);
        serverSocket.sendTo(sessionId, msg);
    }

    /*
    public void notifyRegisterResult(String sessionId, boolean result, String message){
        RegisterResultMessage msg = new RegisterResultMessage(result,message);
        serverSocket.sendTo(sessionId, msg);
    }
 */
    public void notifyCurrentOpengames(String sessionid, List<Game> gameList) {
        CurrentOpenGamesResultMessage msg = new CurrentOpenGamesResultMessage(gameList);
        serverSocket.sendTo(sessionid,msg);
    }
}


