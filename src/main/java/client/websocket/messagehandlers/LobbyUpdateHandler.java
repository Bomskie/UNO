package client.websocket.messagehandlers;

import client.websocket.GameClient;
import shared.messages.LobbyUpdateMessage;
import shared.messaging.MessageHandlerBase;

public class LobbyUpdateHandler extends MessageHandlerBase<LobbyUpdateMessage> {
    @Override
    public void handleMessageInternal(LobbyUpdateMessage message, String sessionId) {
        GameClient.getGameClient().handleLobbyUpdate(message);
    }
}
