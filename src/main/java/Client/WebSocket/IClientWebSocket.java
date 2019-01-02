package Client.WebSocket;

import Shared.WebSockets.IWebSocket;

public interface IClientWebSocket extends IWebSocket {
    void send(Object object);

    void onWebSocketMessageReceived(String message, String sessionId);
}
