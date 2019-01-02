package Shared.Messaging;


public interface IMessageHandlerFactory {
    IMessageHandler getHandler(String simpleType, Object game);
}
