package server.websocket.messagehandlers;

import server.websocket.IServerClient;
import server.websocket.ServerClient;
import shared.messages.CreateGameMessage;
import shared.messaging.MessageHandlerBase;

public class createGameMessageHandler extends MessageHandlerBase<CreateGameMessage> {
    IServerClient serverClient;

    public createGameMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }

    @Override
    public void handleMessageInternal(CreateGameMessage message, String sessionId) {
        serverClient.createGame(message.getUser(), sessionId);
    }
}
