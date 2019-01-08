package Server.WebSocket.MessageHandlers;

import Server.WebSocket.ServerClient;
import Shared.Messages.StartGameMessage;
import Shared.Messaging.MessageHandlerBase;

public class StartGameMessageHandler extends MessageHandlerBase<StartGameMessage> {
    private ServerClient serverClient;
    public StartGameMessageHandler(ServerClient serverClient){this.serverClient = serverClient;}
    @Override
    public void handleMessageInternal(StartGameMessage message, String sessionId) {
        serverClient.StartGame(message.getLobbyId());
    }
}
