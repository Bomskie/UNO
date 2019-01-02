package Client.WebSocket;

import Shared.Messages.LoginMessage;

public class ClientMessageGenerator implements IClientMessageGenerator {

    private IClientWebSocket clientSocket;

    public ClientMessageGenerator(IClientWebSocket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    @Override
    public void login(String username, String password) {
        clientSocket.send(new LoginMessage(username, password));
    }
}
