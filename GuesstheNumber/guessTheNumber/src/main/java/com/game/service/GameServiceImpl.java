package com.game.service;

import com.game.dao.GameDao;
import com.game.entity.Game;
import com.game.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private GameDao gameDao;

    @Override
    public Game getGameById(Integer gameId)throws Exception {
       Game game = gameDao.getGameById(gameId);
       if(!game.isFinished())
           game.setAnswer(new Integer(0));
       return game;
    }

    @Override
    public List<Game> getGame() {
        List<Game> list = gameDao.getGame();
        List<Game> gameList = new ArrayList<>();
        for(Game game : list){
            if(!game.isFinished())
                game.setAnswer(0);

            gameList.add(game);
        }
        return gameList;
    }

    @Override
    public Round guessTheGame(Round round)throws Exception {
        String result = null;
        int answer = Integer.parseInt(round.getGuess());
        Game game = gameDao.getGameById(round.getGame_id());
       result= guessTheResult(round,game);
        round.setResult(result);
        round.setGuess_time(new Date());
        //saving the round object
        Round round1 = gameDao.saveRound(round);
        game.setFinished(true);
        //updating game table with finished status
        gameDao.updateGame(game);
        return round1;
    }

    @Override
    public List<Round> getRoundByGameId(Integer gameId) {
        List<Round> roundList = null;
        try {
            roundList = gameDao.getRoundByGameId(gameId);
        } catch (Exception e) {
            roundList = new ArrayList<>();
            e.printStackTrace();
        }
        return roundList;
    }
    //we will generate answer and will insert into game db with finished status false
    @Override
    public String beginGame() {
        Game game =new Game();
        int answer = generateAnswer();
        game.setAnswer(answer);
        game.setFinished(false);
        String gameId = gameDao.insertGame(game);
        return gameId;
    }

    private int generateAnswer() {
        Random random = new Random();
//we are generating four digit random integer number
        int answer = (int)(Math.random()*9000)+1000;

        return answer;

    }
// we are guessing user given answer with available game data
    private String guessTheResult(Round round, Game game) {

        //we are converting answer into string
        String answer = String.valueOf(game.getAnswer());
        //we got from the user
        String guess = round.getGuess();
        //we are creating empty string object to store exact match and partial match value
        String res="";
        int e=0;
        int p=0;
        //it will store user given guess in key and value pair
        Map<Character, Integer> guessMap= new HashMap<>();
        //same for answer we are creating key value pair
        Map<Character, Integer> answerMap= new HashMap<>();

//it will count the length and iterate till guess length and we will store each element of guess and answer into the guess map and ans map
        for(int i =0; i <guess.length(); i++ ){
            guessMap.put(guess.charAt(i),i);
            answerMap.put(answer.charAt(i), i);
        }
//iterating our guess map
        for(Map.Entry<Character,Integer> entry: guessMap.entrySet()){
//we are checking answer map is containing key or not
            if(answerMap.containsKey(entry.getKey())){
//if it contains and it matches at same position then we will do exact match calculation or partial match calculation
                if((entry.getValue()== answerMap.get(entry.getKey()))){
                    e=e+1;
                }
                else{
                    p=p+1;
                }
            }

        }


        res="e:"+e+":"+"p:"+p;
        return res;
    }
}
