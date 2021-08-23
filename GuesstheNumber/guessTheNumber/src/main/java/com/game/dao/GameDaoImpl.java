package com.game.dao;

import com.game.entity.Game;
import com.game.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Game getGameById(Integer gameId)throws Exception {
        String query = "select game_id,answer,finished from game where game_id=?";
        return jdbcTemplate.queryForObject(query, new Object[]{gameId}, (rs, rowNum) ->
                new Game(
                        rs.getInt("game_id"),
                        rs.getInt("answer"),
                        rs.getBoolean("finished")
                ));
    }

    @Override
    public List<Game> getGame() {
        String query = "select game_id,answer,finished from Game";
        return jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new Game(
                                rs.getInt("game_id"),
                                rs.getInt("answer"),
                                rs.getBoolean("finished")
                        )
        );
    }

    @Override
    public void updateGame(Game game)throws Exception {
        String query = "update game set finished=? where game_id=?";
        jdbcTemplate.update(query,game.isFinished(),game.getGame_id());
    }

    @Override
    public Round saveRound(Round round) {
        String query = "INSERT INTO round (game_id, " +
                "    guess_time, " +
                "    guess, " +
                "    result) " +
                "VALUES (?, ?, ?, ?)";
        // define query arguments
        Object[] params = new Object[] { round.getGame_id(), round.getGuess_time(), round.getGuess(),round.getResult() };

        // define SQL types of the arguments
        int[] types = new int[] { Types.INTEGER, Types.TIMESTAMP, Types.VARCHAR, Types.VARCHAR };

        // execute insert query to insert the data
        // return number of row / rows processed by the executed query
        int row = jdbcTemplate.update(query, params, types);
        System.out.println("Row "+row);
        String count = "select count(*) from round";
        int idCount = jdbcTemplate.queryForObject(count,Integer.class);
        round.setRound_id(idCount);

        return round;
    }

    @Override
    public List<Round> getRoundByGameId(Integer gameId)throws Exception {
        String query = "select round_id,game_id,guess_time,guess,result from Round where game_id=?";
        return jdbcTemplate.query(
                query,new Object[]{gameId},
                (rs, rowNum) ->
                        new Round(
                                rs.getInt("round_id"),
                                rs.getInt("game_id"),
                                rs.getDate("guess_time"),
                                rs.getString("guess"),
                                rs.getString("result")
                        )
        );
    }

    @Override
    public String insertGame(Game game) {
        String query = "INSERT INTO game (answer, " +
                "    finished) " +
                "VALUES (?, ?)";
        // define query arguments
        Object[] params = new Object[] { game.getAnswer(), game.isFinished()};

        // define SQL types of the arguments
        int[] types = new int[] { Types.INTEGER, Types.BOOLEAN };

        // execute insert query to insert the data
        // return number of row / rows processed by the executed query
        int row = jdbcTemplate.update(query, params, types);
        System.out.println("Row "+row);
        String count = "select count(*) from game";
        int idCount = jdbcTemplate.queryForObject(count,Integer.class);
        String gameId = String.valueOf(idCount);
        return gameId;
    }
}
