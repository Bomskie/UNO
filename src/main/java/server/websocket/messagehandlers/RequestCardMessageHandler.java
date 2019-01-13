package server.websocket.messagehandlers;

import server.websocket.ServerClient;
import shared.messages.RequestCardMessage;
import shared.messaging.MessageHandlerBase;

public class RequestCardMessageHandler extends MessageHandlerBase<RequestCardMessage> {
    ServerClient serverClient;
    public RequestCardMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }
    @Override
    public void handleMessageInternal(RequestCardMessage message, String sessionId) {
        serverClient.handleCardRequest(message.getPlayerName(), message.getLobbyId());
    }
}
