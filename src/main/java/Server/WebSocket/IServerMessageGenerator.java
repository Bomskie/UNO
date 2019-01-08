package Server.WebSocket;

import Shared.Models.Card;
import Shared.Models.Game;
import Shared.Models.User;

import java.util.List;

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