package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messages.FinishedGameResultMessage;
import Shared.Messaging.MessageHandlerBase;

public class FinishedGameResultHandler extends MessageHandlerBase<FinishedGameResultMessage> {

    @Override
    public void handleMessageInternal(FinishedGameResultMessage message, String sessionId) {
        GameClient.getGameClient().handleFinishedGame(message.getUserName());
    }
}
