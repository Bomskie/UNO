package Server.WebSocket.MessageHandlers;

import Server.WebSocket.ServerClient;
import Shared.Messages.PlayCardMessage;
import Shared.Messaging.MessageHandlerBase;

public class PlayCardMessageHandler extends MessageHandlerBase<PlayCardMessage>{
    private ServerClient serverClient;
    public PlayCardMessageHandler(ServerClient serverClient) {
        this.serverClient = serverClient;
    }

    @Override
    public void handleMessageInternal(PlayCardMessage message, String sessionId) {
        serverClient.HandleCardPlayed(message.getUserName(),message.getLobbyId(),message.getCard());
    }
}
