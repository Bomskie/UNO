package Server;

import Server.REST.RESTServer;
import Server.WebSocket.WebSocketServer;

public class ServerStart {
    public static void main(String[] args) throws Exception {
        WebSocketServer.main(args);
        RESTServer.main(args);
    }
}
