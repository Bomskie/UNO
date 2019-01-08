package Server.WebSocket.MessageHandlers;

import Server.WebSocket.ServerClient;
import Shared.Messages.CreateGameMessage;
import Shared.Messages.JoinGameMessage;
import Shared.Messaging.MessageHandlerBase;

public class JoinGameMessageHandler extends MessageHandlerBase<JoinGameMessage> {
    ServerClient serverClient;
    public JoinGameMessageHandler(ServerClient serverClient){this.serverClient = serverClient;}

    @Override
    public void handleMessageInternal(JoinGameMessage message, String sessionId) {
        serverClient.JoinLobby(message.getLobbyId(), sessionId, message.getUserName());
    }
}
