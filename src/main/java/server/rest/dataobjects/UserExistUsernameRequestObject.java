package server.rest.dataobjects;

public class UserExistUsernameRequestObject extends BaseRequestObject{
    private String username;

    public UserExistUsernameRequestObject(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
}
