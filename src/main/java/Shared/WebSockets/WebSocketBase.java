package Shared.WebSockets;

import Shared.Messaging.EncapsulatingMessageGenerator;
import Shared.Messaging.IEncapsulatingMessageGenerator;
import Shared.Serialization.ISerializer;
import Shared.Serialization.SerializationProvider;

public abstract class WebSocketBase {

    public IEncapsulatingMessageGenerator getEncapsulatingMessageGenerator() {
        return encapsulatingMessageGenerator;
    }

    private IEncapsulatingMessageGenerator encapsulatingMessageGenerator = new EncapsulatingMessageGenerator();

    public WebSocketBase()
    {

    }
    public abstract void start();

    public abstract void stop();

    public ISerializer<String> getSerializer() {
        return SerializationProvider.getSerializer();
    }
}
