package Server.WebSocket.MessageHandlers;

import Server.WebSocket.ServerClient;
import Shared.Messages.CheckGameInMessage;
import Shared.Messaging.MessageHandlerBase;

public class CheckGameInMessageHandler extends MessageHandlerBase<CheckGameInMessage> {
    private ServerClient serverClient;

    public CheckGameInMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }
    @Override
    public void handleMessageInternal(CheckGameInMessage message, String sessionId) {
        serverClient.CheckUserIn(message.getUserName(), message.getLobbyId());
    }
}
