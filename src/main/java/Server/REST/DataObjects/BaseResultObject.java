package Server.REST.DataObjects;

public class BaseResultObject {
    private boolean success;

    public boolean isSuccess()
    {
        return this.success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }
}
