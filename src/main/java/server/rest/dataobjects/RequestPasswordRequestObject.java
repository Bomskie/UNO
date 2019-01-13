package server.rest.dataobjects;

public class RequestPasswordRequestObject extends BaseRequestObject {
    private String username;

    public RequestPasswordRequestObject(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
}
