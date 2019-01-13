package server.rest.dataobjects;

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
