package Server.WebSocket;

import Server.REST.RestClient;
import Shared.Messages.LobbyUpdateMessage;
import Shared.Models.Card;
import Shared.Models.Game;
import Shared.Models.Lobby;
import Shared.Models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerClient implements IServerClient {

    private RestClient restClient;
    private IServerMessageGenerator messageGenerator;
    private HashMap<String, User> onlineClientsByNameToUser;
    private HashMap<String, String> onlineClientsByNameToSessionId;
    private HashMap<String, Lobby> currentLobbys;
    private HashMap<String, Game> currentGames;

    public ServerClient(IServerMessageGenerator messageGenerator){
        this.messageGenerator = messageGenerator;
        restClient = new RestClient();
        this.onlineClientsByNameToUser = new HashMap<>();
        this.onlineClientsByNameToSessionId = new HashMap<>();
        this.currentLobbys = new HashMap<>();
        this.currentGames = new HashMap<>();
    }

    public void LoginUser(String Username, String Password, String Sessionid) {
        User user;

        //Gegarandeerde login
        user =  new User(Username, Password);
        onlineClientsByNameToUser.put(Sessionid, user);
        onlineClientsByNameToSessionId.put(user.getUsername(), Sessionid);
        messageGenerator.notifyLoginResult(Sessionid, 0, user);

        if (!onlineClientsByNameToSessionId.containsValue(Username)) {
            if (restClient.usernameExists(Username)){
                String pw = restClient.getPassword(Username);
                if (pw.equals(Password)){
                    user = new User(Username, Password);
                    onlineClientsByNameToUser.put(Sessionid, user);
                    onlineClientsByNameToSessionId.put(user.getUsername(), Sessionid);
                    messageGenerator.notifyLoginResult(Sessionid, 0, user);
                }
                else{
                    messageGenerator.notifyLoginResult(Sessionid, 1 , null);
                }
            }
            else {
                messageGenerator.notifyLoginResult(Sessionid, 1, null); //Naam bestaat niet
            }
        } else {
            messageGenerator.notifyLoginResult(Sessionid, 2, null);//username is logged in
        }
    }

    public void CreateGame(String user, String Sessionid){

        String lobbyId = "UNO" + user;
        try{
            if (currentLobbys.get(lobbyId) == null){
                Lobby lobby = new Lobby();
                lobby.addUserToLobby(user);
                currentLobbys.put(lobbyId,lobby);
                messageGenerator.notifyCreateGameResult(Sessionid, true, "", lobbyId);
            }else {
                messageGenerator.notifyCreateGameResult(Sessionid, false, "Bestaat al", null);
            }
        }catch (Exception e){
            messageGenerator.notifyCreateGameResult(Sessionid, false, "er is iets misgegaan", null);
        }
    }

    @Override
    public void JoinLobby(String lobbyId, String sessionID, String userName) {
        Lobby lobby = currentLobbys.get(lobbyId);
        if (lobby != null){
            lobby.addUserToLobby(userName);
            messageGenerator.notifyJoinLobbyResult(sessionID, true, "", lobbyId);
            for (String s: lobby.getUsers()) {
                String sessionId = onlineClientsByNameToSessionId.get(s);
                messageGenerator.notifyLobbyUpdate(sessionId, lobby.getUsersAsString());
            }
        }else {
            messageGenerator.notifyJoinLobbyResult(sessionID, false, "Lobby bestaat niet", null);
        }
    }

    @Override
    public void StartGame(String lobbyId) {
        Lobby lobby = currentLobbys.get(lobbyId);
        if (lobby != null){
            currentLobbys.remove(lobbyId);
            Game game = new Game(lobby.getUsers(), this, lobbyId);
            currentGames.put(lobbyId, game);
            for (String s: game.getPlayers()) {
                String sessionId = onlineClientsByNameToSessionId.get(s);
                messageGenerator.notifyStartGame(sessionId,lobbyId,lobby.getUsersAsString());
            }
        }
    }

    @Override
    public void CheckUserIn(String userName, String lobbyId) {
        Game game = currentGames.get(lobbyId);
        game.CheckPlayerIn(userName);
    }

    @Override
    public void AdvanceTurn(String userName, Card topCard, String playerTurn) {
        String sessionId = onlineClientsByNameToSessionId.get(userName);
        messageGenerator.notifyAdvanceTurn(sessionId, topCard, playerTurn);
    }

    @Override
    public void HandleCardPlayed(String userName, String lobbyId, Card card) {
        Game game = currentGames.get(lobbyId);
        game.CheckPlayedCard(userName, card);
    }

    @Override
    public void ReturnPlayedCard(String userName, Card card, boolean validMove) {
        String sessionId = onlineClientsByNameToSessionId.get(userName);
        messageGenerator.notifyPlayedCard(sessionId,card,validMove);
    }

    @Override
    public void HandleCardRequest(String userName, String lobbyId) {
        Game game = currentGames.get(lobbyId);
        game.requestCard(userName);
    }

    @Override
    public void FinishGame(String userName, String lobbyId) {
        Game game = currentGames.get(lobbyId);
        for (String p:game.getPlayers()){
            messageGenerator.notifyFinishedGame(onlineClientsByNameToSessionId.get(p), userName);
        }
        currentGames.remove(lobbyId);
    }

    public void sendCardToGameUser(Card card, String userName){
        String sessionId = onlineClientsByNameToSessionId.get(userName);
        messageGenerator.notifyGiveCard(sessionId, card);
    }
}
