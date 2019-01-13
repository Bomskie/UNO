package client.websocket.messagehandlers;

import client.websocket.GameClient;
import shared.messages.GiveCardMessage;
import shared.messaging.MessageHandlerBase;

public class GiveCardMessageHandler extends MessageHandlerBase<GiveCardMessage> {
    @Override
    public void handleMessageInternal(GiveCardMessage message, String sessionId) {
        GameClient.getGameClient().GiveCard(message.getCard());
    }
}
