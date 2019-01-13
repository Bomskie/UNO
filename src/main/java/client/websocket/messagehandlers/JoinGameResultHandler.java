package client.websocket.messagehandlers;

import client.websocket.GameClient;
import shared.messages.JoinGameResultMessage;
import shared.messaging.MessageHandlerBase;

public class JoinGameResultHandler extends MessageHandlerBase<JoinGameResultMessage> {
    @Override
    public void handleMessageInternal(JoinGameResultMessage message, String sessionId) {
        GameClient.getGameClient().handleJoinResult(message);
    }
}
