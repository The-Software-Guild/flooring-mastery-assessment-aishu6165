package com.game.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.entity.Game;
import com.game.entity.Round;
import com.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;

//it will generate the answer(4 digit answer) and save into game table and return the game id
    @PostMapping(value = "/begin")
    public ResponseEntity<String> beginGame(){
        String gameId = gameService.beginGame();
        return new ResponseEntity<String>("game_id :"+gameId,HttpStatus.CREATED);
    }
//fetch the game data based on user given game id
    @GetMapping(value = "/game/{gameId}")
    public ResponseEntity<String> getGameById(@PathVariable Integer gameId){
        String msg = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Game game = gameService.getGameById(gameId);
            msg = objectMapper.writeValueAsString(game);
            return new ResponseEntity<String>(msg, HttpStatus.OK);
        } catch (Exception e) {
            msg =  "Game is not available with Game Id "+gameId;
        }
        return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
    }
//fetch the list of available games
    @GetMapping(value = "/game")
    public ResponseEntity<List<Game>> getGame(){
        List<Game> list = gameService.getGame();
        return new ResponseEntity<List<Game>>(list,HttpStatus.OK);
    }
//we guess the answer and return round object by adding result
    @PostMapping(value = "/guess")
    public ResponseEntity<Round> guessTheResult(@RequestBody Round round) throws Exception {
        Round round1 = gameService.guessTheGame(round);
        return new ResponseEntity<Round>(round1,HttpStatus.CREATED);
    }
//we will get the round data based on user given gameid
    @GetMapping(value = "rounds/{gameId}")
    public ResponseEntity<List<Round>> getRoundByGameId(@PathVariable Integer gameId){
        List<Round> roundList = gameService.getRoundByGameId(gameId);
        if(roundList.isEmpty()){
            return new ResponseEntity<>(roundList,HttpStatus.NOT_FOUND);
        }else {
            roundList.sort(Comparator.comparing(Round::getGuess_time));
            return new ResponseEntity<List<Round>>(roundList, HttpStatus.OK);
        }
    }
}
