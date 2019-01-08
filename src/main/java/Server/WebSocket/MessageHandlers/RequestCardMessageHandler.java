package Server.WebSocket.MessageHandlers;

import Server.WebSocket.ServerClient;
import Shared.Messages.RequestCardMessage;
import Shared.Messaging.MessageHandlerBase;

public class RequestCardMessageHandler extends MessageHandlerBase<RequestCardMessage> {
    ServerClient serverClient;
    public RequestCardMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }
    @Override
    public void handleMessageInternal(RequestCardMessage message, String sessionId) {
        serverClient.HandleCardRequest(message.getPlayerName(), message.getLobbyId());
    }
}
