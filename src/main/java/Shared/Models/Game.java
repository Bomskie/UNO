package Shared.Models;

import Server.WebSocket.ServerClient;
import Shared.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<String> players;
    private List<Card> Deck;
    private ServerClient serverClient;
    private List<Card> Stack;
    private String lobbyId;

    private int checkedIn = 0;
    private int PlayerTurn = 0;

    public Game(List<String> players, ServerClient serverClient, String lobbyId){
        this.players = players;
        this.serverClient = serverClient;
        Deck = new ArrayList<>();
        Stack = new ArrayList<>();
        this.lobbyId = lobbyId;
    }

    public void CheckPlayerIn(String playerName){
        if (players.contains(playerName)){
            checkedIn++;
        }
        if (checkedIn == players.size()){
            StartGame();
        }
    }

    public List<String> getPlayers() {
        return players;
    }

    private void NextTurn(){
        PlayerTurn++;
        if (PlayerTurn > players.size() -1){
            PlayerTurn = 0;
        }
        GiveTurn(PlayerTurn);
    }

    private void GiveTurn(int playerTurn) {
        for (String s: players){
            serverClient.AdvanceTurn(s, Stack.get(Stack.size() - 1), players.get(playerTurn));
        }
    }

    private void StartGame() {
        CreateDeck();
        Shuffle();
        Deal();
        Stack.add(GiveCard());
        GiveTurn(0);
    }

    private Card GiveCard(){
        if (Deck.size() == 0){
            Deck = Stack;
            Stack.clear();
            Stack.add(Deck.get(Deck.size() - 1));
            Shuffle();
        }
        Card card = Deck.get(0);
        Deck.remove(0);
        return card;
    }

    private void Deal(){
        for(int i=0;i<7;i++){
            for (String player : players) {
                serverClient.sendCardToGameUser(GiveCard(), player);
            }
        }
    }

    private void Shuffle(){
        Random r = new Random();
        List<Card> temp = new ArrayList<Card>();

        for (int i = Deck.size()-1; i > 0; i--) {
            int j = r.nextInt(i+1);
            temp.add(Deck.get(j));
            Deck.remove(j);
        }

        Deck = temp;
    }

    private void CreateDeck(){
        for (Color c: Color.values()) {
            Card card = new Card();
            card.kleur = c;
            card.nummer = 0;
            card.special = false;
            card.type = null;
            Deck.add(card);

            for (int i = 1; i<10;i++){
                for (int loop = 0; loop<2;loop++){
                    card = new Card();
                    card.kleur = c;
                    card.nummer = i;
                    card.special = false;
                    card.type = null;
                    Deck.add(card);
                }
            }
        }
    }

    private boolean canPlay(Card card){
        Card topCard = Stack.get(Stack.size() - 1);
        if (card.kleur == topCard.kleur){
            return true;
        }
        return card.nummer == topCard.nummer;
    }

    public void CheckPlayedCard(String userName, Card card) {
        if (canPlay(card)){
            serverClient.ReturnPlayedCard(userName, card, true);
            Stack.add(card);
            NextTurn();
        }
        else {
            serverClient.ReturnPlayedCard(userName, card, false);
        }
    }

    public void requestCard(String userName) {
        serverClient.sendCardToGameUser(GiveCard(), userName);
        NextTurn();
    }
}
