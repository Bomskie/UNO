package shared.websockets;

import shared.messaging.IMessageProcessor;

public interface IWebSocket {
    void start();

    void stop();

    void setMessageProcessor(IMessageProcessor processor);
}
