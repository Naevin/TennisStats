package com.slashlearn.tennisstats;

import java.io.Serializable;

public class Match implements Serializable {


    private Player playerOne; //player names
    private Player playerTwo;

    private boolean adSetting; //true if ads are played false if no ad
    private int servingPlayer; //# of player who is serving
    private int setCount; //0: 2/3, 1: 3/5, 2: 8game pro set

    public Match(Player playerOneIn, Player playerTwoIn, boolean adSettingIn, int servingPlayerIn, int setCountIn) {
        this.playerOne = playerOneIn;
        this.playerTwo = playerTwoIn;
        this.adSetting = adSettingIn;
        this.servingPlayer = servingPlayerIn;
        this.setCount = setCountIn;
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    public int getSetCount() {
        return this.setCount;
    }

    public void switchServingPlayer() {
        if(this.servingPlayer == 1) {
            this.servingPlayer = 2;
        } else {
            this.servingPlayer = 1;
        }
    }

    public Player getServingPlayer() {
        if (this.servingPlayer == 1) {
            return this.playerOne;
        }
        return this.playerTwo;
    }

    public Player getReturningPlayer() {
        if (this.servingPlayer == 1) {
            return this.playerTwo;
        }
        return this.playerOne;
    }

    public void printInfo() {
        String gameInfo;
        gameInfo = (String) this.playerOne.getName();
        gameInfo = gameInfo + (String) this.playerTwo.getName();
        gameInfo = gameInfo + this.adSetting + this.servingPlayer + this.setCount;
        System.out.println(gameInfo);
    }
}
