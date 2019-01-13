package server.websocket;

import shared.messages.*;
import shared.models.Card;
import shared.models.User;

public class ServerMessageGenerator implements IServerMessageGenerator {

    private IServerWebSocket serverSocket;

    public ServerMessageGenerator(IServerWebSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void notifyLoginResult(String sessionId, int error, User user) {
        LoginResultMessage msg = new LoginResultMessage(user,error);
        serverSocket.sendTo(sessionId, msg);
    }

    public void notifyCreateGameResult(String sessionId, boolean succes, String error, String lobbyId) {
        CreateGameResultMessage msg = new CreateGameResultMessage(lobbyId, error, succes);
        serverSocket.sendTo(sessionId, msg);
    }

    @Override
    public void notifyJoinLobbyResult(String sessionId, boolean succes, String error, String lobbyId) {
        JoinGameResultMessage msg = new JoinGameResultMessage(lobbyId, error, succes);
        serverSocket.sendTo(sessionId,msg);
    }

    @Override
    public void notifyLobbyUpdate(String sessionId, String names) {
        LobbyUpdateMessage msg = new LobbyUpdateMessage(names);
        serverSocket.sendTo(sessionId, msg);
    }

    @Override
    public void notifyStartGame(String sessionId, String lobbyId, String usersAsString) {
        StartGameResultMessage msg = new StartGameResultMessage(lobbyId, usersAsString);
        serverSocket.sendTo(sessionId, msg);
    }

    @Override
    public void notifyGiveCard(String sessionId, Card card) {
        GiveCardMessage msg = new GiveCardMessage(card);
        serverSocket.sendTo(sessionId, msg);
    }

    @Override
    public void notifyAdvanceTurn(String sessionId, Card card, String playerTurn) {
        AdvanceTurnMessage msg = new AdvanceTurnMessage(playerTurn, card);
        serverSocket.sendTo(sessionId, msg);
    }

    @Override
    public void notifyPlayedCard(String sessionId, Card card, boolean validMove) {
        PlayCardResultMessage msg = new PlayCardResultMessage(card,validMove);
        serverSocket.sendTo(sessionId,msg);
    }

    @Override
    public void notifyFinishedGame(String sessionId, String userName) {
        FinishedGameResultMessage msg = new FinishedGameResultMessage(userName);
        serverSocket.sendTo(sessionId, msg);
    }

}


