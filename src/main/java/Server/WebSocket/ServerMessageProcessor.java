package Server.WebSocket;

import Shared.Messaging.IMessageHandler;
import Shared.Messaging.IMessageHandlerFactory;
import Shared.Messaging.MessageProcessorBase;

public class ServerMessageProcessor extends MessageProcessorBase implements IServerMessageProcessor {

    private IServerClient game;

    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory)
    {
        super(messageHandlerFactory);
    }

    public void registerGame(IServerClient game)
    {
        this.game = game;
    }

    public IServerClient getGame()
    {
        return game;
    }

    @Override
    public void processMessage(String sessionId, String type, String data) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getGame());
        handler.handleMessage(data, sessionId);
    }

    public void handleDisconnect(String sessionId)
    {

    }
}