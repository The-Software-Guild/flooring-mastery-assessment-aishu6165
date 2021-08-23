package com.game.entity;

import java.util.Date;

public class Round {
    private int round_id;
    private int game_id;
    private Date guess_time;

    public Round() {
    }

    private String guess;
    private String result;

    public Round(int round_id, int game_id, Date guess_time, String guess, String result) {
        this.round_id=round_id;
        this.game_id=game_id;
        this.guess_time=guess_time;
        this.guess=guess;
        this.result=result;
    }

    public int getRound_id() {
        return round_id;
    }

    public void setRound_id(int round_id) {
        this.round_id = round_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public Date getGuess_time() {
        return guess_time;
    }

    public void setGuess_time(Date guess_time) {
        this.guess_time = guess_time;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
