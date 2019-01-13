package client.websocket;

import shared.websockets.IWebSocket;

public interface IClientWebSocket extends IWebSocket {
    void send(Object object);

    void onWebSocketMessageReceived(String message, String sessionId);
}
