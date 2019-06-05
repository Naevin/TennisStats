package com.slashlearn.tennisstats;

import java.io.Serializable;

public class Match implements Serializable {


    private Player playerOne; //player names
    private Player playerTwo;

    private boolean adSetting; //true if ads are played false if no ad
    private int servingPlayer; //# of player who is serving
    private int setCount; //0: 2/3, 1: 3/5,
    private int currentSet;

    public Match(Player playerOneIn, Player playerTwoIn, boolean adSettingIn, int servingPlayerIn, int setCountIn) {
        this.playerOne = playerOneIn;
        this.playerTwo = playerTwoIn;
        this.adSetting = adSettingIn;
        this.servingPlayer = servingPlayerIn;
        this.setCount = setCountIn;
        this.currentSet = 1;
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

    public void checkGame() {
        int difference = Math.abs(this.playerOne.getPoint() - this.playerTwo.getPoint());
        if (this.adSetting) {
            if ((this.playerOne.getPoint() > 3 || this.playerTwo.getPoint() > 3) && difference > 1) {
                if (this.playerOne.getPoint() > this.playerTwo.getPoint()) {
                    this.newGame(this.playerOne);
                } else {
                    this.newGame(this.playerTwo);
                }
            }
        } else {
            if ((this.playerOne.getPoint() > 3 || this.playerTwo.getPoint() > 3)) {
                if (this.playerOne.getPoint() > this.playerTwo.getPoint()) {
                    this.newGame(this.playerOne);
                } else {
                    this.newGame(this.playerTwo);
                }
            }
        }
    }

    public void newGame(Player gameWinner) {
        gameWinner.addGame(currentSet);
        this.switchServingPlayer();
        this.playerOne.setPoint(0);
        this.playerTwo.setPoint(0);
        this.checkSet();
    }

    public String[] matchScore() {
        String[] result = new String[2];
        int servingPlayerScore = this.getServingPlayer().getPoint();
        int returningPlayerScore = this.getReturningPlayer().getPoint();

        if (servingPlayerScore > 2 && returningPlayerScore > 2) {
            if(servingPlayerScore == returningPlayerScore) {
                result[0] = "40";
                result[1] = "40";
            } else {
                if (servingPlayerScore > returningPlayerScore) {
                    result[0] = "Ad";
                    result[1] = "-";
                } else {
                    result[0] = "-";
                    result[1] = "Ad";
                }
            }
        } else {
            result[0] = getServingPlayer().pointToString();
            result[1] = getReturningPlayer().pointToString();
        }
        return result;
    }

    public void checkSet() {
        //TODO add some tiebreaker check
        int playerOneGame = playerOne.getCurrentGame(this.currentSet);
        int playerTwoGame = playerTwo.getCurrentGame(this.currentSet);
        if (Math.abs(playerOneGame - playerTwoGame) > 1 && (playerOneGame >= 6 || playerTwoGame >= 6)) {
            if(playerOneGame > playerTwoGame) {
                this.newSet(this.playerOne);
            } else {
                this.newSet(this.playerTwo);
            }
        }
        this.checkMatch();
    }

    public void newSet(Player playerIn) {
        playerIn.addSet();
        this.currentSet++;
        System.out.println(currentSet);
    }

    public void checkMatch() {
        switch(this.setCount) {
            case 0:
                if (this.playerOne.getSetCount() > 1 || this.playerTwo.getSetCount() > 1) {
                    //TODO REPLACE with some kind of match end
                    System.out.println("Match is over");
                }
                break;
            default:
                if (this.playerOne.getSetCount() > 1 || this.playerTwo.getSetCount() > 2) {
                    //TODO REPLACE WITH SOME MATCH END
                    System.out.println("Match is over");
                }
        }
    }

}
