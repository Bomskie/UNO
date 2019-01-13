package server.websocket.messagehandlers;
import server.websocket.IServerClient;
import server.websocket.ServerClient;
import shared.messages.LoginMessage;
import shared.messaging.MessageHandlerBase;

public class LoginMessageHandler extends MessageHandlerBase<LoginMessage> {
    IServerClient serverClient;

    public LoginMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }

    @Override
    public void handleMessageInternal(LoginMessage message, String sessionId) {
        serverClient.loginUser(message.getPlayerName(), message.getPassword(), sessionId);
    }
}
