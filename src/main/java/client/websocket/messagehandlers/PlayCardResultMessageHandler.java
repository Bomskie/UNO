package client.websocket.messagehandlers;

import client.websocket.GameClient;
import shared.messages.PlayCardResultMessage;
import shared.messaging.MessageHandlerBase;

public class PlayCardResultMessageHandler extends MessageHandlerBase<PlayCardResultMessage> {
    @Override
    public void handleMessageInternal(PlayCardResultMessage message, String sessionId) {
        GameClient.getGameClient().handlePlayedCard(message.getCard(), message.getValidMove());
    }
}
