package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messages.JoinGameResultMessage;
import Shared.Messaging.MessageHandlerBase;

public class JoinGameResultHandler extends MessageHandlerBase<JoinGameResultMessage> {
    @Override
    public void handleMessageInternal(JoinGameResultMessage message, String sessionId) {
        GameClient.getGameClient().handleJoinResult(message);
    }
}
