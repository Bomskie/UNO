package client.websocket;

import client.controllers.WindowController;
import client.Session;
import shared.messages.CreateGameResultMessage;
import shared.messages.JoinGameResultMessage;
import shared.messages.LobbyUpdateMessage;
import shared.messages.StartGameResultMessage;
import shared.models.Card;
import shared.models.User;
import javafx.application.Platform;

public class GameClient implements IGameClient {

    private static GameClient gameClient = new GameClient();
    private IClientMessageGenerator messageGenerator;

    private WindowController windowController = WindowController.getWindowController();

    public static GameClient getGameClient(){
        return gameClient;
    }

    @Override
    public void handleLoginResult(int message, User user) {
        if(user == null){
            switch (message){
                case 1:
                    windowController.showMsg("Login", "niet gelukt");
                case 2:
                    windowController.showMsg("Login", "al ingelogd");
                    break;
            }
        }else{
            Session.getInstance().setUserName(user.getUsername());
            windowController.showMenu();
        }
    }

    @Override
    public void handleCreateResult(CreateGameResultMessage msg) {
        if (msg.returnSucces()){
            Session.getInstance().setCurrentLobby(msg.returnLobbyId());
            windowController.getLobbyController().setOwnerShip(true);
            windowController.getLobbyController().setLobbyLabel(msg.returnLobbyId());
            windowController.showLobby();
        }else {
            windowController.showMsg("Maken", msg.returnErrorMessage());
        }
    }

    @Override
    public void handleJoinResult(JoinGameResultMessage msg) {
        if (msg.returnSucces()){
            Session.getInstance().setCurrentLobby(msg.returnLobbyId());
            windowController.getLobbyController().setOwnerShip(false);
            windowController.getLobbyController().setLobbyLabel(msg.returnLobbyId());
            windowController.showLobby();
        }else {
            windowController.showMsg("Joinen", msg.returnErrorMessage());
        }
    }

    @Override
    public void handleLobbyUpdate(LobbyUpdateMessage msg) {
        Platform.runLater(() -> {
            try{
                windowController.getLobbyController().updateLobby(msg.getNames());
            }catch (Exception e){
                windowController.showMsg("",e.toString());
            }
        });

    }

    public void setMessageGenerator(IClientMessageGenerator messageGenerator){
        this.messageGenerator = messageGenerator;
    }

    public void loginUser(String username, String password){
        messageGenerator.login(username, password);
    }

    public void CreateGame(){
        messageGenerator.createGame(Session.getInstance().getUserName());
    }

    public void JoinGame(String lobbyId){messageGenerator.joinGame(lobbyId, Session.getInstance().getUserName());}

    public void StartGame(){messageGenerator.startGame(Session.getInstance().getCurrentLobby());}

    public void handleStartGameResult(StartGameResultMessage msg){
        windowController.showGame();
        Platform.runLater(()->{
            windowController.getGameController().Prepare(msg.getUsersAsString());
        });
    }

    @Override
    public void GiveCard(Card card) {
        Platform.runLater(()->{
            windowController.getGameController().AddCardToHand(card);
        });
    }

    @Override
    public void AdvanceTurn(String player, Card topCard) {
        Platform.runLater(()->{
            windowController.getGameController().ReloadUI(topCard,player);
        });
    }

    @Override
    public void PlayCard(Card card) {
        messageGenerator.playCard(Session.getInstance().getUserName(), card, Session.getInstance().getCurrentLobby());
    }

    @Override
    public void handlePlayedCard(Card card, boolean validMove) {
        if (validMove){
            Platform.runLater(()->{
                windowController.getGameController().validatePlay();
            });
        }
        else{
            windowController.showMsg("Move", "Niet geldig");
        }
    }

    @Override
    public void requestCard() {
        messageGenerator.requestCard(Session.getInstance().getUserName(), Session.getInstance().getCurrentLobby());
    }

    @Override
    public void finishGame() {
        messageGenerator.finishGame(Session.getInstance().getUserName(), Session.getInstance().getCurrentLobby());
    }

    @Override
    public void handleFinishedGame(String userName) {
        windowController.showMenu();
        windowController.showMsg("Afgelopen", userName + " Heeft gewonnen");
    }

    public void CheckGameIn(){
        messageGenerator.checkUserIn(Session.getInstance().getUserName(), Session.getInstance().getCurrentLobby());
    }
}
