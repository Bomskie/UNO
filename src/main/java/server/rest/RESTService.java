package server.rest;

import server.rest.dal.AccountContext;
import server.rest.dal.IAccountContext;
import server.rest.dataobjects.RequestPasswordRequestObject;
import server.rest.dataobjects.UserExistUsernameRequestObject;
import shared.logging.LogLevel;
import shared.logging.Logger;
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
            Logger.getInstance().log("[rest] RequestPassword", LogLevel.INFORMATION);
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
        Logger.getInstance().log("[rest] RequestUsername", LogLevel.INFORMATION);
        if (input != null) {
            UserExistUsernameRequestObject request = gson.fromJson(input, UserExistUsernameRequestObject.class);
            boolean result = mySQAccountContext.userExistsUsername(request.getUsername());
            String response = ResponseHelper.getUserExistsUsernameObjectResponse(result);
            return Response.status(200).entity(response).build();
        }
        return Response.status(400).entity(ResponseHelper.getErrorResponse()).build();
    }
}
