package Server.REST.DataObjects;

public class RequestPasswordResultObject extends BaseResultObject {

    private String password;

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
