package shared.messaging;

public interface IMessageHandler {

    void handleMessage(String message, String sessionId);
}