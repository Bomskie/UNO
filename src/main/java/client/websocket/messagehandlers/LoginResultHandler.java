package client.websocket.messagehandlers;

import client.websocket.GameClient;
import shared.messages.LoginResultMessage;
import shared.messaging.MessageHandlerBase;

public class LoginResultHandler extends MessageHandlerBase<LoginResultMessage> {
    @Override
    public void handleMessageInternal(LoginResultMessage message, String sessionId) {
        GameClient.getGameClient().handleLoginResult(message.getMessage(), message.getUser());
    }
}
