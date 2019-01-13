package server.websocket.messagehandlers;

import server.websocket.ServerClient;
import shared.messages.JoinGameMessage;
import shared.messaging.MessageHandlerBase;

public class JoinGameMessageHandler extends MessageHandlerBase<JoinGameMessage> {
    ServerClient serverClient;
    public JoinGameMessageHandler(ServerClient serverClient){this.serverClient = serverClient;}

    @Override
    public void handleMessageInternal(JoinGameMessage message, String sessionId) {
        serverClient.joinLobby(message.getLobbyId(), sessionId, message.getUserName());
    }
}
