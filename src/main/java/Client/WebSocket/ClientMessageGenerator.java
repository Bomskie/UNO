package Client.WebSocket;

import Shared.Messages.*;
import Shared.Models.Card;

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

    @Override
    public void createGame(String username) {
        clientSocket.send(new CreateGameMessage(username));
    }

    @Override
    public void joinGame(String lobbbyId, String username) {
        clientSocket.send(new JoinGameMessage(username, lobbbyId));
    }

    @Override
    public void startGame(String lobbyId) {
        clientSocket.send(new StartGameMessage(lobbyId));
    }

    @Override
    public void checkUserIn(String userName, String lobbyId) {
        clientSocket.send(new CheckGameInMessage(userName, lobbyId));
    }

    @Override
    public void playCard(String userName, Card card, String lobbyId) {
        clientSocket.send(new PlayCardMessage(card, userName, lobbyId));
    }

    @Override
    public void requestCard(String playerName, String lobbyId) {
        clientSocket.send(new RequestCardMessage(playerName, lobbyId));
    }

    @Override
    public void finishGame(String playerName, String lobbyId) {
        clientSocket.send(new FinishedGameMessage(playerName,lobbyId));
    }
}
