package client.websocket.messagehandlers;

import client.websocket.GameClient;
import shared.messages.CreateGameResultMessage;
import shared.messaging.MessageHandlerBase;

public class CreateGameResultHandler extends MessageHandlerBase<CreateGameResultMessage> {

    @Override
    public void handleMessageInternal(CreateGameResultMessage message, String sessionId) {
        GameClient.getGameClient().handleCreateResult(message);
    }
}
