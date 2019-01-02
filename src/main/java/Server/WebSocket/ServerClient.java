package Server.WebSocket;

import Server.REST.RestClient;
import Shared.Models.Game;
import Shared.Models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerClient implements IServerClient {

    private RestClient restClient;
    private IServerMessageGenerator messageGenerator;
    private HashMap<String, User> onlineClientsByNameToUser;
    private HashMap<String, String> onlineClientsByNameToSessionId;
    private List<Game> openGames;

    public ServerClient(IServerMessageGenerator messageGenerator){
        this.messageGenerator = messageGenerator;
        restClient = new RestClient();
        this.onlineClientsByNameToUser = new HashMap<>();
        this.onlineClientsByNameToSessionId = new HashMap<>();
        this.openGames = new ArrayList<>();
    }

    public void LoginUser(String Username, String Password, String Sessionid) {
        User user;

        //Gegarandeerde login
        user =  new User(Username, Password);
        onlineClientsByNameToUser.put(Sessionid, user);
        messageGenerator.notifyLoginResult(Sessionid, 1, null);

        if (!onlineClientsByNameToSessionId.containsValue(Username)) {
            if (restClient.usernameExists(Username)){
                String pw = restClient.getPassword(Username);
                if (pw.equals(Password)){
                    user = new User(Username, Password);
                    onlineClientsByNameToUser.put(Sessionid, user);
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
}
