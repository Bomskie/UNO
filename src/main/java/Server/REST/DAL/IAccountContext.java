package Server.REST.DAL;

public interface IAccountContext {
    String requestPassword(String username);
    boolean userExistsUsername(String username);
}
