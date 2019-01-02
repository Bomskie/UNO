package Client.Controllers;

import Client.WebSocket.GameClient;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class LoginController {
    public TextField tbUserName;
    public PasswordField tbPassword;
    public Button btGo;

    public void Login(MouseEvent mouseEvent) throws Exception {
        if (!tbUserName.getText().equals("") && !tbPassword.getText().equals("")){
            GameClient.getGameClient().loginUser(tbUserName.getText(), tbPassword.getText());
        }
        //als Login correct
    }
}
