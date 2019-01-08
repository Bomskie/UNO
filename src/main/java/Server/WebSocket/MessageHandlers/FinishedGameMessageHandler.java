package Server.WebSocket.MessageHandlers;

import Server.WebSocket.ServerClient;
import Shared.Messages.FinishedGameMessage;
import Shared.Messaging.MessageHandlerBase;

public class FinishedGameMessageHandler extends MessageHandlerBase<FinishedGameMessage> {
    ServerClient serverClient;

    public FinishedGameMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }
    @Override
    public void handleMessageInternal(FinishedGameMessage message, String sessionId) {
        serverClient.FinishGame(message.getUserName(),message.getLobbyId());
    }
}
