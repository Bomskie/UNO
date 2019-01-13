package client.controllers;

import client.Session;
import client.websocket.GameClient;
import shared.models.Card;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    public Pane fxTopCard;
    public Text fxTopCardText;
    public HBox SpelerHand;
    public Pane Deck;
    public VBox PlayerList;

    private List<String> players;
    private List<Card> playerHand;

    private Card topCard;
    private int IndexPlayed;

    boolean turn = false;

    public GameController(){
        players = new ArrayList<>();
        playerHand = new ArrayList<>();
    }

    public void Prepare(String playersAsString){
        String[] allNames = playersAsString.split("\\|");
        for (int i = 0; i<allNames.length;i++){
            Label l = new Label();
            l.setText(allNames[i]);
            PlayerList.getChildren().add(l);
            players.add(allNames[i]);
        }
        fxTopCard.setStyle("-fx-background-color: grey");
        fxTopCardText.setText("");
        GameClient.getGameClient().CheckGameIn();
    }

    public void ReloadUI(Card topCard, String player){
        PlayerList.getChildren().clear();
        for (int i = 0; i<players.size();i++){
            Label l = new Label();
            l.setText(players.get(i));
            if (players.get(i).equals(player)){
                l.setTextFill(Color.GOLDENROD);
                turn = player.equals(Session.getInstance().getUserName());
            }
            if (player.equals(Session.getInstance().getUserName())){
                l.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 20));
            }
            else{
                l.setFont(Font.font("Calibri", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            }
            PlayerList.getChildren().add(l);
        }
        this.topCard = topCard;
        if (playerHand.size() == 0){
            GameClient.getGameClient().finishGame();
        }
        GenCardsInUI();
    }

    public void AddCardToHand(Card card) {
        playerHand.add(card);
        GenCardsInUI();
    }

    private void GenCardsInUI(){
        SpelerHand.getChildren().clear();
        int indexInHand = 0;
        for (Card card:playerHand){
            Pane p = new Pane();
            p.setPrefHeight(94);
            p.setPrefWidth(85);

            p.setMinWidth(85);
            p.setMinHeight(94);

            Circle c = new Circle();
            p.getChildren().add(c);

            c.setCenterX(43);
            c.setCenterY(56);
            c.setRadius(35);
            c.fillProperty().setValue(Paint.valueOf("Transparent"));
            c.setStroke(Paint.valueOf("WHITE"));
            c.setStrokeWidth(4);

            Text t = new Text();
            t.setText(Integer.toString(card.nummer));
            p.getChildren().add(t);
            t.layoutXProperty().setValue(33);
            t.layoutYProperty().setValue(68);
            t.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR, 40));
            t.textAlignmentProperty().setValue(TextAlignment.CENTER);
            t.setFill(Color.WHITE);


            p.layoutXProperty().setValue(50);
            p.layoutYProperty().setValue(14);
            p.setStyle("-fx-background-color:" +  card.kleur.getValue());
            p.setId(Integer.toString(indexInHand));
            

            p.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    PlayCard(event.getSource());
                }
            });

            SpelerHand.getChildren().add(p);
            indexInHand++;
        }
        if (SpelerHand.getChildren().size() > 1){
            SpelerHand.setMinWidth((SpelerHand.getChildren().size() * 85) + SpelerHand.getChildren().size() * 10);
        }
        if (topCard != null){
            fxTopCard.setStyle("-fx-background-color:" +  topCard.kleur.getValue());
            fxTopCardText.setText(Integer.toString(topCard.nummer));
        }
    }

    private void PlayCard(Object source) {
        if (turn){
            Pane s = (Pane)source;
            IndexPlayed = Integer.parseInt(s.getId());
            Card card = playerHand.get(Integer.parseInt(s.getId()));
            GameClient.getGameClient().PlayCard(card);
        }
    }

    public void validatePlay(){
        playerHand.remove(IndexPlayed);
    }

    public void RequestCard(MouseEvent mouseEvent) {
        if (turn){
            GameClient.getGameClient().requestCard();
        }
    }
}
