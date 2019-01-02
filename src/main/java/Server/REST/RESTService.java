package Server.REST;

import Server.REST.DAL.AccountContext;
import Server.REST.DAL.IAccountContext;
import Server.REST.DataObjects.RequestPasswordRequestObject;
import Server.REST.DataObjects.UserExistUsernameRequestObject;
import Shared.Logging.LogLevel;
import Shared.Logging.Logger;
import com.google.gson.Gson;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/uno")
public class RESTService {
    //Field
    private Gson gson;
    private IAccountContext mySQAccountContext;

    public RESTService()
    {
        gson = new Gson();
        mySQAccountContext = new AccountContext();
    }

    @POST
    @Path("/requestpassword")
    @Consumes("application/json")
    @Produces("application/json")
    public Response requestPassword(String input)
    {
        if (input != null)
        {
            Logger.getInstance().log("[REST] RequestPassword", LogLevel.INFORMATION);
            RequestPasswordRequestObject request = gson.fromJson(input, RequestPasswordRequestObject.class);
            String result = mySQAccountContext.requestPassword(request.getUsername());
            String response = ResponseHelper.getRequestPasswordResultObjectResponse(result);
            return Response.status(200).entity(response).build();
        }
        return Response.status(400).entity(ResponseHelper.getErrorResponse()).build();
    }

    @POST
    @Path("/requestusername")
    @Consumes("application/json")
    @Produces("application/json")
    public Response userExitstUsername(String input) {
        Logger.getInstance().log("[REST] RequestUsername", LogLevel.INFORMATION);
        if (input != null) {
            UserExistUsernameRequestObject request = gson.fromJson(input, UserExistUsernameRequestObject.class);
            boolean result = mySQAccountContext.userExistsUsername(request.getUsername());
            String response = ResponseHelper.getUserExistsUsernameObjectResponse(result);
            return Response.status(200).entity(response).build();
        }
        return Response.status(400).entity(ResponseHelper.getErrorResponse()).build();
    }
}
