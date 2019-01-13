package client.websocket;

import shared.messaging.IMessageHandler;
import shared.messaging.IMessageHandlerFactory;
import shared.messaging.MessageProcessorBase;

public class ClientMessageProcessor extends MessageProcessorBase implements IClientMessageProcessor {
    public ClientMessageProcessor(IMessageHandlerFactory messageHandlerFactory)
    {
        super(messageHandlerFactory);
    }

    @Override
    public void processMessage(String sessionId, String type, String data) {
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];
        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, GameClient.getGameClient());
        handler.handleMessage(data, sessionId);
    }

    @Override
    public void handleDisconnect(String sessionId) {

    }
}
