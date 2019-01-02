package Shared.Messaging;

public interface IMessageHandler {

    void handleMessage(String message, String sessionId);
}