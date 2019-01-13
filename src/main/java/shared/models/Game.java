package shared.models;

import server.websocket.ServerClient;
import shared.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<String> players;
    private List<Card> deck;
    private ServerClient serverClient;
    private List<Card> stack;

    private int checkedIn = 0;
    private int PlayerTurn = 0;

    public Game(List<String> players, ServerClient serverClient){
        this.players = players;
        this.serverClient = serverClient;
        deck = new ArrayList<>();
        stack = new ArrayList<>();
    }

    public void nextTurn(){
        PlayerTurn++;
        if (PlayerTurn > players.size() -1){
            PlayerTurn = 0;
        }
        giveTurn(PlayerTurn);
    }

    private void giveTurn(int playerTurn) {
        for (String s: players){
            serverClient.advanceTurn(s, stackTopCard(), players.get(playerTurn));
        }
    }

    private void startGame() {
        createDeck();
        Shuffle();
        deal();
        stack.add(giveCard());
        giveTurn(0);
    }

    private Card giveCard(){
        if (deck.size() == 0){
            for (Card c : stack){
                deck.add(c);
            }
            stack.clear();
            stack.add(deck.get(deck.size() - 1));
            Shuffle();
        }
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    private void deal(){
        for(int i=0;i<7;i++){
            for (String player : players) {
                serverClient.sendCardToGameUser(giveCard(), player);
            }
        }
    }

    private void Shuffle(){
        Random r = new Random();
        List<Card> temp = new ArrayList<Card>();

        for (int i = deck.size()-1; i > 0; i--) {
            int j = r.nextInt(i+1);
            temp.add(deck.get(j));
            deck.remove(j);
        }

        deck = temp;
    }

    private void createDeck(){
        for (Color c: Color.values()) {
            Card card = new Card();
            card.kleur = c;
            card.nummer = 0;
            card.special = false;
            card.type = null;
            deck.add(card);

            for (int i = 1; i<10;i++){
                for (int loop = 0; loop<2;loop++){
                    card = new Card();
                    card.kleur = c;
                    card.nummer = i;
                    card.special = false;
                    card.type = null;
                    deck.add(card);
                }
            }
        }
    }

    private boolean canPlay(Card card){
        Card topCard = stackTopCard();
        if (card.kleur == topCard.kleur){
            return true;
        }
        return card.nummer == topCard.nummer;
    }

    private Card stackTopCard(){return stack.get(stack.size() - 1);}

    public void checkPlayerIn(String playerName){
        if (players.contains(playerName)){
            checkedIn++;
        }
        if (checkedIn == players.size()){
            startGame();
        }
    }

    public List<String> getPlayers() {
        return players;
    }

    public Boolean playCard(Card card) {
        if (canPlay(card)){
           stack.add(card);
        }
        return canPlay(card);
    }

    public Card requestCard() {
        return giveCard();
    }

    //unitTest Returns
    public List<Card> getDeck(){return deck;}
    public Card getStackTopCard(){return stackTopCard();}
    public int getCheckedIn() {
        return checkedIn;
    }
    public int getPlayerTurn() {
        return PlayerTurn;
    }
    public void startGameTest(){
        createDeck();
        Shuffle();
        Card card = new Card();
        card.nummer = 2;
        card.kleur = Color.Blauw;
        stack.add(card);
    }
}
