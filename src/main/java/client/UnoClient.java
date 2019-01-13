package client;

import client.controllers.WindowController;
import client.websocket.*;
import client.websocket.messagehandlers.ClientMessageHandlerFactory;
import shared.messaging.IMessageHandlerFactory;
import javafx.application.Application;
import javafx.stage.Stage;


public class UnoClient extends Application {
    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        IClientWebSocket socket = new ClientWebSocket();

        IClientMessageGenerator generator = new ClientMessageGenerator(socket);

        IMessageHandlerFactory messageHandlerFactory = new ClientMessageHandlerFactory();

        IClientMessageProcessor clientMessageProcessor = new ClientMessageProcessor(messageHandlerFactory);

        socket.setMessageProcessor(clientMessageProcessor);

        GameClient.getGameClient().setMessageGenerator(generator);

        socket.start();

        WindowController.getWindowController().LoadStages();
        WindowController.getWindowController().showLogin();
    }
}
