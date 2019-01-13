package client.websocket.messagehandlers;

import client.websocket.GameClient;
import shared.messages.FinishedGameResultMessage;
import shared.messaging.MessageHandlerBase;

public class FinishedGameResultHandler extends MessageHandlerBase<FinishedGameResultMessage> {

    @Override
    public void handleMessageInternal(FinishedGameResultMessage message, String sessionId) {
        GameClient.getGameClient().handleFinishedGame(message.getUserName());
    }
}
