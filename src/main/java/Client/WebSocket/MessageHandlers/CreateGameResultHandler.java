package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messages.CreateGameResultMessage;
import Shared.Messaging.MessageHandlerBase;

public class CreateGameResultHandler extends MessageHandlerBase<CreateGameResultMessage> {

    @Override
    public void handleMessageInternal(CreateGameResultMessage message, String sessionId) {
        GameClient.getGameClient().handleCreateResult(message);
    }
}
