package client.websocket.messagehandlers;

import client.websocket.GameClient;
import shared.messages.AdvanceTurnMessage;
import shared.messaging.MessageHandlerBase;

public class AdvanceTurnMessageHandler extends MessageHandlerBase<AdvanceTurnMessage> {
    @Override
    public void handleMessageInternal(AdvanceTurnMessage message, String sessionId) {
        GameClient.getGameClient().AdvanceTurn(message.getPlayerTurn(), message.getTopCard());
    }
}
