package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messages.LoginResultMessage;
import Shared.Messaging.IMessageHandler;
import Shared.Messaging.MessageHandlerBase;

public class LoginResultHandler extends MessageHandlerBase<LoginResultMessage> {
    @Override
    public void handleMessageInternal(LoginResultMessage message, String sessionId) {
        GameClient.getGameClient().handleLoginResult(message.getMessage(), message.getUser());
    }
}
