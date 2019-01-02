package Server.REST;

public interface IRestClient {
    boolean usernameExists(String username);
    String getPassword(String username);
}
