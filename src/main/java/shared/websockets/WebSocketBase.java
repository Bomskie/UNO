package shared.websockets;

import shared.messaging.EncapsulatingMessageGenerator;
import shared.messaging.IEncapsulatingMessageGenerator;
import shared.serialization.ISerializer;
import shared.serialization.SerializationProvider;

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
