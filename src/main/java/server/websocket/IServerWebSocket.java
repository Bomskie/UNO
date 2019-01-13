package server.websocket;

import shared.websockets.IWebSocket;

public interface IServerWebSocket extends IWebSocket {

    void sendTo(String sessionId, Object object);

    void broadcast(Object object);

    void sendToOthers(String sessionId, Object object);

}
