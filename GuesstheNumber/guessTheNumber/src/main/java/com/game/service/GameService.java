package com.game.service;

import com.game.entity.Game;
import com.game.entity.Round;

import java.util.List;

public interface GameService {
    public Game getGameById(Integer gameId)throws Exception;
    public List<Game> getGame();
    public Round guessTheGame(Round round)throws Exception;
    public List<Round> getRoundByGameId(Integer gameId);
    public String beginGame();
}
