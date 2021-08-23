package com.game.dao;

import com.game.entity.Game;
import com.game.entity.Round;

import java.util.List;

public interface GameDao {
    public Game getGameById(Integer gameId)throws Exception;
    public List<Game> getGame();
    public void updateGame(Game game)throws Exception;
    public Round saveRound(Round round);
    public List<Round> getRoundByGameId(Integer gameId)throws Exception;
    public String insertGame(Game game);
}
