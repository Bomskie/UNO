package Shared.Messages;

import Shared.Models.Card;

public class AdvanceTurnMessage {
    private String playerTurn;
    private Card topCard;

    public AdvanceTurnMessage(String playerTurn, Card topCard){
        this.playerTurn = playerTurn;
        this.topCard = topCard;
    }

    public Card getTopCard() {
        return topCard;
    }

    public String getPlayerTurn() {
        return playerTurn;
    }
}
