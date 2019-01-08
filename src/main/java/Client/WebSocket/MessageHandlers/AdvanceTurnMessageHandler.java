package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messages.AdvanceTurnMessage;
import Shared.Messaging.MessageHandlerBase;

public class AdvanceTurnMessageHandler extends MessageHandlerBase<AdvanceTurnMessage> {
    @Override
    public void handleMessageInternal(AdvanceTurnMessage message, String sessionId) {
        GameClient.getGameClient().AdvanceTurn(message.getPlayerTurn(), message.getTopCard());
    }
}
