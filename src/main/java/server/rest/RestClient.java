package server.rest;

import server.rest.dataobjects.RequestPasswordRequestObject;
import server.rest.dataobjects.RequestPasswordResultObject;
import server.rest.dataobjects.UserExistUsernameRequestObject;
import server.rest.dataobjects.UserExistUsernameResultObject;

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
