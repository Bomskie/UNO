package Client.WebSocket;

import Shared.Models.User;

import java.util.List;

public interface IGameClient {
    void handleLoginResult(int message, User user);
}