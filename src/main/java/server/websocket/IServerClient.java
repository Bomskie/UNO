package server.websocket;

import shared.models.Card;
import shared.models.Game;

public interface IServerClient {
    void loginUser(String Username, String Password, String sessionID);

    void createGame(String user, String sessionID);

    void joinLobby(String lobbyId, String userName, String sessionID);

    void startGame(String lobbyId);

    void checkUserIn(String userName, String lobbyId);

    //void advanceTurn(String userName, Card topCard, String playerTurn);
    void advanceTurn(Game game);

    void handleCardPlayed(String userName, String lobbyId, Card card);

    void handleCardRequest(String userName, String lobbyId);

    void finishGame(String userName, String lobbyId);
}