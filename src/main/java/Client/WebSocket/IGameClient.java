package Client.WebSocket;

import Shared.Messages.CreateGameResultMessage;
import Shared.Messages.JoinGameResultMessage;
import Shared.Messages.LobbyUpdateMessage;
import Shared.Messages.StartGameResultMessage;
import Shared.Models.Card;
import Shared.Models.User;

import java.util.List;

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