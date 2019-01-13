package server.websocket.messagehandlers;

import server.websocket.ServerClient;
import shared.messages.FinishedGameMessage;
import shared.messaging.MessageHandlerBase;

public class FinishedGameMessageHandler extends MessageHandlerBase<FinishedGameMessage> {
    ServerClient serverClient;

    public FinishedGameMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }
    @Override
    public void handleMessageInternal(FinishedGameMessage message, String sessionId) {
        serverClient.finishGame(message.getUserName(),message.getLobbyId());
    }
}
