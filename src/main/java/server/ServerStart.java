package server;

import server.rest.RESTServer;
import server.websocket.WebSocketServer;

public class ServerStart {
    public static void main(String[] args) throws Exception {
        WebSocketServer.main(args);
        RESTServer.main(args);
    }
}
