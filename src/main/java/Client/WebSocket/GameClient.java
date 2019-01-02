package Client.WebSocket;

import Client.Controllers.WindowController;
import Client.Session;
import Shared.Models.User;

import java.io.IOException;

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

                    break;
            }
        }else{
            Session.getInstance().setUserName(user.getUsername());
            windowController.showMenu();
        }
    }

    public void setMessageGenerator(IClientMessageGenerator messageGenerator){
        this.messageGenerator = messageGenerator;
    }

    public void loginUser(String username, String password){
        messageGenerator.login(username, password);
    }
}
