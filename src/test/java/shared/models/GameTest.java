package shared.models;

import server.websocket.ServerClient;
import server.websocket.ServerMessageGenerator;
import server.websocket.UnoWebSocketService;
import shared.Color;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
     private Game game;

    private Card card;

    @Before
    public void setUp() throws Exception {
        List<String> players = new ArrayList<>();
        players.add("player 1");
        players.add("player 2");
        game = new Game(players, new ServerClient(new ServerMessageGenerator(new UnoWebSocketService())));
        game.startGameTest();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPlayers() {
        assertEquals(2, game.getPlayers().size());
    }

    @Test
    public void playCard() {
        card = new Card();

        card.kleur = Color.Blauw;
        card.nummer = 4;
        assertEquals(true, game.playCard(card));
        card = new Card();
        card.nummer = 4;
        card.kleur = Color.Rood;
        assertEquals(true, game.playCard(card));
        card = new Card();
        card.nummer = 2;
        card.kleur = Color.Geel;
        assertEquals(false, game.playCard(card));
    }

    @Test
    public void requestCard() {
        assertNotNull(game.requestCard());
    }

    @Test
    public void getDeck() {
        int size = game.getDeck().size();
        for (int i = 0; i < size;i++){
            game.playCard(game.requestCard());
        }
        assertEquals(0, game.getDeck().size());
        game.requestCard();
        assertNotEquals(0, game.getDeck().size());
    }

    @Test
    public void getStackTopCard() {
        if (card == null){
            card = new Card();
            card.kleur = Color.Blauw;
            card.nummer = 2;
            assertEquals(card.kleur, game.getStackTopCard().kleur);
            assertEquals(card.nummer, game.getStackTopCard().nummer);
        }
        else {
            assertEquals(card.kleur, game.getStackTopCard().kleur);
            assertEquals(card.nummer, game.getStackTopCard().nummer);
        }
    }
}