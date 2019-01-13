package server.websocket.messagehandlers;

import server.websocket.ServerClient;
import shared.messages.PlayCardMessage;
import shared.messaging.MessageHandlerBase;

public class PlayCardMessageHandler extends MessageHandlerBase<PlayCardMessage>{
    private ServerClient serverClient;
    public PlayCardMessageHandler(ServerClient serverClient) {
        this.serverClient = serverClient;
    }

    @Override
    public void handleMessageInternal(PlayCardMessage message, String sessionId) {
        serverClient.handleCardPlayed(message.getUserName(),message.getLobbyId(),message.getCard());
    }
}
