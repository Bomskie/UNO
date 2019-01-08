package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messages.LobbyUpdateMessage;
import Shared.Messaging.MessageHandlerBase;

public class LobbyUpdateHandler extends MessageHandlerBase<LobbyUpdateMessage> {
    @Override
    public void handleMessageInternal(LobbyUpdateMessage message, String sessionId) {
        GameClient.getGameClient().handleLobbyUpdate(message);
    }
}
