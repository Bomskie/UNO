package Client.WebSocket;

import Shared.Models.Card;

public interface IClientMessageGenerator {
    void login(String username, String password);
    void createGame(String username);
    void joinGame(String lobbbyId, String username);
    void startGame(String lobbyId);
    void checkUserIn(String userName, String lobbyId);
    void playCard(String userName, Card card, String lobbyId);
    void requestCard(String playerName, String lobbyId);
    void finishGame(String playerName, String lobbyId);
}
