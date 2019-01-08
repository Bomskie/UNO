package Server.WebSocket;

import Shared.Models.Card;

import java.util.List;

public interface IServerClient {
    void LoginUser(String Username, String Password,String sessionID);

    void CreateGame(String user,String sessionID);

    void JoinLobby(String lobbyId, String userName, String sessionID);

    void StartGame(String lobbyId);

    void CheckUserIn(String userName, String lobbyId);

    void AdvanceTurn(String userName, Card topCard, String playerTurn);

    void HandleCardPlayed(String userName, String lobbyId, Card card);

    void ReturnPlayedCard(String userName, Card card, boolean validMove);

    void HandleCardRequest(String userName, String lobbyId);

    void FinishGame(String userName, String lobbyId);
}