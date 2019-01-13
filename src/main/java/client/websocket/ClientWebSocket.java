package client.websocket;

import shared.logging.LogLevel;
import shared.logging.Logger;
import shared.messaging.EncapsulatingMessage;
import shared.messaging.IMessageProcessor;
import shared.serialization.ISerializer;
import shared.serialization.SerializationProvider;
import shared.websockets.WebSocketBase;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class ClientWebSocket extends WebSocketBase implements IClientWebSocket{
    private String serverUri = "ws://localhost:8008/UnoWebSocket/";

    private Session session;

    private static ClientWebSocket instance = null;

    private IMessageProcessor messageProcessor;

    public static ClientWebSocket getInstance() {
        if (instance == null) {
            instance = new ClientWebSocket();
        }
        return instance;
    }

    @OnOpen
    public void onWebSocketConnect(Session session){
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
        onWebSocketMessageReceived(message, session.getId());
    }

    @Override
    public void send(Object object) {
        System.out.println(object);
        String msg = getEncapsulatingMessageGenerator().generateMessageString(object);
        sendMessageToServer(msg);
    }

    @Override
    public void onWebSocketMessageReceived(String message, String sessionId) {
        System.out.println(message);
        ISerializer<String> ser = SerializationProvider.getSerializer();
        EncapsulatingMessage msg = ser.deserialize(message, EncapsulatingMessage.class);
        messageProcessor.processMessage(sessionId, msg.getMessageType(), msg.getMessageData());
    }

    @Override
    public void setMessageProcessor(IMessageProcessor handler) {
        this.messageProcessor = handler;
    }

    private void sendMessageToServer(String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getInstance().log(ex);
        }
    }

    @Override
    public void start() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(serverUri));

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }

    @Override
    public void stop() {
        try {
            if(session != null)
                session.close();

        } catch (Exception ex){
            Logger.getInstance().log(ex);
        }
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        session = null;
    }

}
