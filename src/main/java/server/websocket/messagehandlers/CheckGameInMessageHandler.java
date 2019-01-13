package server.websocket.messagehandlers;

import server.websocket.ServerClient;
import shared.messages.CheckGameInMessage;
import shared.messaging.MessageHandlerBase;

public class CheckGameInMessageHandler extends MessageHandlerBase<CheckGameInMessage> {
    private ServerClient serverClient;

    public CheckGameInMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }
    @Override
    public void handleMessageInternal(CheckGameInMessage message, String sessionId) {
        serverClient.checkUserIn(message.getUserName(), message.getLobbyId());
    }
}
