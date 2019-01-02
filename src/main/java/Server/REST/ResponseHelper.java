package Server.REST;

import Server.REST.DataObjects.BaseResultObject;
import Server.REST.DataObjects.RequestPasswordResultObject;
import Server.REST.DataObjects.UserExistUsernameResultObject;
import com.google.gson.Gson;

public class ResponseHelper {
    private static final Gson gson = new Gson();

    static String getErrorResponse()
    {
        BaseResultObject result = new BaseResultObject();
        result.setSuccess(false);

        return gson.toJson(result);
    }

    static String getRequestPasswordResultObjectResponse(String password) {
        RequestPasswordResultObject result = new RequestPasswordResultObject();
        result.setPassword(password);
        return gson.toJson(result);
    }

    static String getUserExistsUsernameObjectResponse(boolean success)
    {
        UserExistUsernameResultObject result = new UserExistUsernameResultObject();
        result.setSuccess(success);

        return gson.toJson(result);
    }
}
