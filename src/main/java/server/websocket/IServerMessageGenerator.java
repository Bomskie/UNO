package server.websocket;

import shared.models.Card;
import shared.models.User;

public interface IServerMessageGenerator {
    void notifyLoginResult(String sessionId, int error, User user);
    void notifyCreateGameResult(String sessionId, boolean succes, String error, String lobbyId);
    void notifyJoinLobbyResult(String sessionId, boolean succes, String error, String lobbyId);
    void notifyLobbyUpdate(String sessionId, String names);
    void notifyStartGame(String sessionId, String lobbyId, String usersAsString);
    void notifyGiveCard(String sessionId, Card card);
    void notifyAdvanceTurn(String sessionId, Card card, String playerTurn);
    void notifyPlayedCard(String sessionId, Card card, boolean validMove);
    void notifyFinishedGame(String sessionId, String userName);
}