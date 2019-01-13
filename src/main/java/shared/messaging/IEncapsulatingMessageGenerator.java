package shared.messaging;

public interface IEncapsulatingMessageGenerator {
    EncapsulatingMessage generateMessage(Object content);

    String generateMessageString(Object content);


}
