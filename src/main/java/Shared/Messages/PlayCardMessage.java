package Shared.Messages;

import Shared.Models.Card;

public class PlayCardMessage {
    private Card card;
    private String userName;
    private String lobbyId;

    public PlayCardMessage(Card card, String userName, String lobbyId){
        this.card = card;
        this.lobbyId = lobbyId;
        this.userName = userName;
    }

    public Card getCard() {
        return card;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public String getUserName() {
        return userName;
    }
}
