package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messages.StartGameResultMessage;
import Shared.Messaging.MessageHandlerBase;

public class StartGameResultMessageHandler extends MessageHandlerBase<StartGameResultMessage> {
    @Override
    public void handleMessageInternal(StartGameResultMessage message, String sessionId) {
        GameClient.getGameClient().handleStartGameResult(message);
    }
}
