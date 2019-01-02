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

            default:
                return null;
        }
    }
}