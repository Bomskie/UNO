package Client.Controllers;

import Client.Session;
import Client.WebSocket.GameClient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class LobbyController {
    public Label lLobbyId;
    public VBox vbPlayers;
    public Button btStartGame;

    private int playerCount = 0;

    public void setOwnerShip(boolean ownerShip){
        btStartGame.setVisible(ownerShip);
        if (ownerShip){
            Label l = new Label();
            l.setText(Session.getInstance().getUserName());
            vbPlayers.getChildren().add(l);
        }
    }
    public void setLobbyLabel(String lobbyId){lLobbyId.setText(lobbyId);}
    public void updateLobby(String names){
        vbPlayers.getChildren().clear();
        String[] allNames = names.split("\\|");
        playerCount = allNames.length;
        for (int i = 0; i<allNames.length;i++){
            Label l = new Label();
            l.setText(allNames[i]);
            vbPlayers.getChildren().add(l);
        }
    }

    public void StartGame(MouseEvent mouseEvent) {
        if (playerCount > 1){
            GameClient.getGameClient().StartGame();
        }
    }
}
