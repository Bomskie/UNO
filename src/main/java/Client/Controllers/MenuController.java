package Client.Controllers;

import Client.WebSocket.GameClient;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MenuController {
    public Button makeGame;
    public Button joinGame;
    public TextField tbJoin;

    public void createGame(MouseEvent mouseEvent) {
        GameClient.getGameClient().CreateGame();
    }

    public void joinGame(MouseEvent mouseEvent) {
        if (!tbJoin.getText().equals(""))
            GameClient.getGameClient().JoinGame(tbJoin.getText());
        else WindowController.getWindowController().showMsg("Foutje", "Voer een LobbyId in");
    }
}
