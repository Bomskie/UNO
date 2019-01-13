package server.rest.dal;

public interface IAccountContext {
    String requestPassword(String username);
    boolean userExistsUsername(String username);
}
