package Server.WebSocket;

import Server.WebSocket.MessageHandlers.ServerMessageHandleFactory;
import Shared.Logging.Logger;
import Shared.Messaging.IMessageHandlerFactory;
import Shared.Messaging.IMessageProcessor;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

public class WebSocketServer {

    private static final int PORT = 8008;

    public static void main(String[] args)
    {
        IMessageHandlerFactory factory = new ServerMessageHandleFactory();
        IMessageProcessor messageHandler = new ServerMessageProcessor(factory);
        final UnoWebSocketService serverWebSocket = new UnoWebSocketService();
        serverWebSocket.setMessageProcessor(messageHandler);

        IServerMessageGenerator messageGenerator = new ServerMessageGenerator(serverWebSocket);
        ((ServerMessageProcessor) messageHandler).registerGame(new ServerClient(messageGenerator));

        Server wsServer = new Server();
        ServerConnector connector = new ServerConnector(wsServer);
        connector.setPort(PORT);
        wsServer.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        wsServer.setHandler(contextHandler);

        try
        {
            // Initialize javax.mercuryserver layer
            ServerContainer wsContainer = WebSocketServerContainerInitializer.configureContext(contextHandler);
            //Add websocket endpoint to javax.websocket layer
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(serverWebSocket.getClass(), serverWebSocket.getClass().getAnnotation(ServerEndpoint.class).value()).configurator(new ServerEndpointConfig.Configurator() {
                @Override
                public <T> T getEndpointInstance(Class<T> endpointClass) {
                    return (T) serverWebSocket;
                }
            }).build();
            wsContainer.addEndpoint(config);
            wsServer.start();
            wsServer.join();
        }
        catch (Exception exc)
        {
            Logger.getInstance().log(exc);
        }
    }
}
