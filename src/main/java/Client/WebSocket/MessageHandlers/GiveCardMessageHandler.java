package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messages.GiveCardMessage;
import Shared.Messaging.MessageHandlerBase;

public class GiveCardMessageHandler extends MessageHandlerBase<GiveCardMessage> {
    @Override
    public void handleMessageInternal(GiveCardMessage message, String sessionId) {
        GameClient.getGameClient().GiveCard(message.getCard());
    }
}
