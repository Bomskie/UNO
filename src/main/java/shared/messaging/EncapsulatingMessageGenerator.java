package shared.messaging;

import shared.serialization.ISerializer;
import shared.serialization.SerializationProvider;

public class EncapsulatingMessageGenerator implements IEncapsulatingMessageGenerator {

    ISerializer<String> ser = SerializationProvider.getSerializer();

    public EncapsulatingMessage generateMessage(Object content)
    {
        String messageForServerJson = ser.serialize(content);
        String type = content.getClass().toGenericString();
        return new EncapsulatingMessage(type, messageForServerJson);
    }

    public String generateMessageString(Object content)
    {
        EncapsulatingMessage msg = generateMessage(content);
        return ser.serialize(msg);
    }
}
