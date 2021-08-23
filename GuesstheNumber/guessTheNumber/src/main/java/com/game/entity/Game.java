package com.game.entity;

public class Game {
    private int game_id;
    private int answer;
    private boolean finished;

    public Game() {
    }

    public Game(int game_id, int answer, boolean finished) {
        this.game_id=game_id;
        this.answer=answer;
        this.finished=finished;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}

