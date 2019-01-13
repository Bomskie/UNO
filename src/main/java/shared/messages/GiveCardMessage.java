package shared.messages;

import shared.models.Card;

public class GiveCardMessage {
    private Card card;

    public GiveCardMessage(Card card){
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
