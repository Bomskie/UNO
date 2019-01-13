package server.websocket.messagehandlers;

import server.websocket.ServerClient;
import shared.messages.StartGameMessage;
import shared.messaging.MessageHandlerBase;

public class StartGameMessageHandler extends MessageHandlerBase<StartGameMessage> {
    private ServerClient serverClient;
    public StartGameMessageHandler(ServerClient serverClient){this.serverClient = serverClient;}
    @Override
    public void handleMessageInternal(StartGameMessage message, String sessionId) {
        serverClient.startGame(message.getLobbyId());
    }
}
