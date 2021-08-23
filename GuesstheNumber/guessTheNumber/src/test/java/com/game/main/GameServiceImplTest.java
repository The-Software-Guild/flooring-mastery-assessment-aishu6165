package com.game.main;

import com.game.dao.GameDao;
import com.game.dao.GameDaoImpl;
import com.game.entity.Game;
import com.game.entity.Round;
import com.game.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class GameServiceImplTest {
    @Autowired
    GameService gameService;
    @Autowired
    GameDao gameDao;

    @Test
    public void getGameTest(){
        List<Game> gameList = gameService.getGame();
        Assert.notNull(gameList);
    }

    @Test
    public void getGameByIdTest()throws Exception{
        Game game = gameService.getGameById(1);
        Assertions.assertEquals(1,game.getGame_id());
    }

    @Test
    public void guessTheGameTest()throws Exception{
        Round round = new Round();
        round.setGame_id(1);
        round.setGuess("3214");
        Round round1 = gameService.guessTheGame(round);
        Assertions.assertEquals("3214",round1.getGuess());
    }

    @Test
    public void getRoundByGameIdTest(){
        List<Round> list = gameService.getRoundByGameId(1);
        Assert.notNull(list);
    }

    @Test
    public void beginTest(){
        String gameId = gameService.beginGame();
        Assertions.assertNotEquals("0",gameId);
    }
}
