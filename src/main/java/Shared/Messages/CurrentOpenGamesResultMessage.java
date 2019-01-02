package Shared.Messages;

import Shared.Models.Game;

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