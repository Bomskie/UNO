package Server.REST;

import Server.REST.DataObjects.RequestPasswordRequestObject;
import Server.REST.DataObjects.RequestPasswordResultObject;
import Server.REST.DataObjects.UserExistUsernameRequestObject;
import Server.REST.DataObjects.UserExistUsernameResultObject;

public class RestClient extends BaseRestClient implements IRestClient {

    private static final String URL = "http://localhost:8096/uno";

    @Override
    String getBaseURL() {
        return URL;
    }

    @Override
    public boolean usernameExists(String username) {
        UserExistUsernameRequestObject request = new UserExistUsernameRequestObject(username);
        return executeQueryPost(request, "/requestusername", UserExistUsernameResultObject.class).isSuccess();
    }

    @Override
    public String getPassword(String username) {
        RequestPasswordRequestObject request = new RequestPasswordRequestObject(username);
        return executeQueryPost(request, "/requestpassword", RequestPasswordResultObject.class).getPassword();
    }

}
