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
            case "CreateGameResultMessage":
                return new CreateGameResultHandler();
            case "JoinGameResultMessage":
                return new JoinGameResultHandler();
            case "LobbyUpdateMessage":
                return new LobbyUpdateHandler();
            case "StartGameResultMessage":
                return new StartGameResultMessageHandler();
            case "GiveCardMessage":
                return new GiveCardMessageHandler();
            case "AdvanceTurnMessage":
                return new AdvanceTurnMessageHandler();
            case "PlayCardResultMessage":
                return new PlayCardResultMessageHandler();
            case "FinishedGameResultMessage":
                return new FinishedGameResultHandler();
            default:
                return null;
        }
    }
}

