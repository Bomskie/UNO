package server.websocket;

import server.rest.RestClient;
import shared.models.Card;
import shared.models.Game;
import shared.models.Lobby;
import shared.models.User;

import java.util.HashMap;

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

    public void loginUser(String Username, String Password, String Sessionid) {
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

    public void createGame(String user, String Sessionid){

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
    public void joinLobby(String lobbyId, String sessionID, String userName) {
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
    public void startGame(String lobbyId) {
        Lobby lobby = currentLobbys.get(lobbyId);
        if (lobby != null){
            currentLobbys.remove(lobbyId);
            Game game = new Game(lobby.getUsers(), this);
            currentGames.put(lobbyId, game);
            for (String s: game.getPlayers()) {
                String sessionId = onlineClientsByNameToSessionId.get(s);
                messageGenerator.notifyStartGame(sessionId,lobbyId,lobby.getUsersAsString());
            }
        }
    }

    @Override
    public void checkUserIn(String userName, String lobbyId) {
        Game game = currentGames.get(lobbyId);
        game.checkPlayerIn(userName);
    }

    @Override
    public void advanceTurn(String userName, Card topCard, String playerTurn) {
        String sessionId = onlineClientsByNameToSessionId.get(userName);
        messageGenerator.notifyAdvanceTurn(sessionId, topCard, playerTurn);
    }

    @Override
    public void handleCardPlayed(String userName, String lobbyId, Card card) {
        Game game = currentGames.get(lobbyId);
        String sessionId = onlineClientsByNameToSessionId.get(userName);
        boolean canPlay = game.playCard(card);
        messageGenerator.notifyPlayedCard(sessionId,card,canPlay);
        if (canPlay){
            game.nextTurn();
        }
    }

    @Override
    public void handleCardRequest(String userName, String lobbyId) {
        Game game = currentGames.get(lobbyId);
        sendCardToGameUser(game.requestCard(), userName);
        game.nextTurn();
    }

    @Override
    public void finishGame(String userName, String lobbyId) {
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
