package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messages.PlayCardResultMessage;
import Shared.Messaging.MessageHandlerBase;

public class PlayCardResultMessageHandler extends MessageHandlerBase<PlayCardResultMessage> {
    @Override
    public void handleMessageInternal(PlayCardResultMessage message, String sessionId) {
        GameClient.getGameClient().handlePlayedCard(message.getCard(), message.getValidMove());
    }
}
