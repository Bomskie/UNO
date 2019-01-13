package client.websocket;

import shared.messages.CreateGameResultMessage;
import shared.messages.JoinGameResultMessage;
import shared.messages.LobbyUpdateMessage;
import shared.messages.StartGameResultMessage;
import shared.models.Card;
import shared.models.User;

public interface IGameClient {
    void handleLoginResult(int message, User user);
    void handleCreateResult(CreateGameResultMessage msg);
    void handleJoinResult(JoinGameResultMessage msg);
    void handleLobbyUpdate(LobbyUpdateMessage msg);
    void handleStartGameResult(StartGameResultMessage msg);
    void GiveCard(Card card);
    void AdvanceTurn(String player, Card topCard);
    void PlayCard(Card card);
    void handlePlayedCard(Card card, boolean validMove);
    void requestCard();
    void finishGame();
    void handleFinishedGame(String userName);
}