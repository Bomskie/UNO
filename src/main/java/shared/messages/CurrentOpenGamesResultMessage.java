package shared.messages;

import shared.models.Game;

import java.util.List;

public class CurrentOpenGamesResultMessage {
    private List<Game>openGames;

    public CurrentOpenGamesResultMessage(List<Game> games){
        openGames = games;
    }

    public List<Game> getOpenGames(){
        return openGames;
    }
}