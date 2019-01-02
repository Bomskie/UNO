package Client.WebSocket.MessageHandlers;

import Client.WebSocket.GameClient;
import Shared.Messaging.IMessageHandler;
import Shared.Messaging.IMessageHandlerFactory;

public class ClientMessageHandlerFactory implements IMessageHandlerFactory {

    @Override
    public IMessageHandler getHandler(String simpleType, Object game) {
        GameClient gameClient = new GameClient();
        switch (simpleType){
            case "LoginResultMessage":
                return new LoginResultHandler();


            default:
                return null;
        }
    }
}

