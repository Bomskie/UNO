package Server.WebSocket.MessageHandlers;

import Server.WebSocket.ServerClient;
import Shared.Messaging.IMessageHandler;
import Shared.Messaging.IMessageHandlerFactory;

public class ServerMessageHandleFactory implements IMessageHandlerFactory {

    @Override
    public IMessageHandler getHandler(String simpleType, Object obj) {
        ServerClient serverClient = (ServerClient)obj;
        switch(simpleType)
        {
            case "LoginMessage":
                return new LoginMessageHandler(serverClient);
            case "CreateGameMessage":
                return new createGameMessageHandler(serverClient);
            case "JoinGameMessage":
                return new JoinGameMessageHandler(serverClient);
            case "StartGameMessage":
                return new StartGameMessageHandler(serverClient);
            case "CheckGameInMessage":
                return new CheckGameInMessageHandler(serverClient);
            case "PlayCardMessage":
                return new PlayCardMessageHandler(serverClient);
            case "RequestCardMessage":
                return new RequestCardMessageHandler(serverClient);
            case "FinishedGameMessage":
                return new FinishedGameMessageHandler(serverClient);
            default:
                return null;
        }
    }
}