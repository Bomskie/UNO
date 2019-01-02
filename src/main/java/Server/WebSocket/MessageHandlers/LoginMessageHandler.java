package Server.WebSocket.MessageHandlers;
import Server.WebSocket.IServerClient;
import Server.WebSocket.ServerClient;
import Shared.Messages.LoginMessage;
import Shared.Messages.LoginResultMessage;
import Shared.Messaging.MessageHandlerBase;

public class LoginMessageHandler extends MessageHandlerBase<LoginMessage> {
    IServerClient serverClient;

    public LoginMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }

    @Override
    public void handleMessageInternal(LoginMessage message, String sessionId) {
        serverClient.LoginUser(message.getPlayerName(), message.getPassword(), sessionId);
    }
}
