package client.websocket.messagehandlers;

import client.websocket.GameClient;
import shared.messages.StartGameResultMessage;
import shared.messaging.MessageHandlerBase;

public class StartGameResultMessageHandler extends MessageHandlerBase<StartGameResultMessage> {
    @Override
    public void handleMessageInternal(StartGameResultMessage message, String sessionId) {
        GameClient.getGameClient().handleStartGameResult(message);
    }
}
