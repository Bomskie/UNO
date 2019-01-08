package Server.WebSocket.MessageHandlers;

import Server.WebSocket.IServerClient;
import Server.WebSocket.ServerClient;
import Shared.Messages.CreateGameMessage;
import Shared.Messaging.MessageHandlerBase;

public class createGameMessageHandler extends MessageHandlerBase<CreateGameMessage> {
    IServerClient serverClient;

    public createGameMessageHandler(ServerClient serverClient){
        this.serverClient = serverClient;
    }

    @Override
    public void handleMessageInternal(CreateGameMessage message, String sessionId) {
        serverClient.CreateGame(message.getUser(), sessionId);
    }
}
