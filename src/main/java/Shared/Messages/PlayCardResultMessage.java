package Shared.Messages;

import Shared.Models.Card;

public class PlayCardResultMessage {
    private Card card;
    private boolean validMove;

    public PlayCardResultMessage(Card card, boolean validMove){
        this.card = card;
        this.validMove = validMove;
    }

    public Card getCard() {
        return card;
    }

    public boolean getValidMove(){
        return validMove;
    }
}
