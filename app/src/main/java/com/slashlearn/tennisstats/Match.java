package com.slashlearn.tennisstats;

import java.io.Serializable;

public class Match implements Serializable {

    private String matchTitle;
    private Player playerOne; //player names
    private Player playerTwo;

    private boolean adSetting; //true if ads are played false if no ad
    private int servingPlayer; //# of player who is serving
    private int setCount; //0: 2/3, 1: 3/5,
    private int currentSet;

    public Match(String matchTitleIn, Player playerOneIn, Player playerTwoIn, boolean adSettingIn, int servingPlayerIn, int setCountIn) {
        this.matchTitle = matchTitleIn;
        this.playerOne = playerOneIn;
        this.playerTwo = playerTwoIn;
        this.adSetting = adSettingIn;
        this.servingPlayer = servingPlayerIn;
        this.setCount = setCountIn;
        this.currentSet = 1;
    }

    public Match(String gameString) {
        String matchString = gameString.substring(0, gameString.indexOf("["));
        String playerString = gameString.substring(gameString.indexOf("["));
        String playerOneString = playerString.substring(playerString.indexOf("[") + 1, playerString.indexOf("]"));
        String playerTwoString = playerString.substring(playerString.indexOf("[", playerString.indexOf("[") +1) + 1,
                playerString.indexOf("]", playerString.indexOf("]") +1));
        this.playerOne = new Player(true, playerOneString);
        this.playerTwo = new Player(true, playerTwoString);

        //assigning from to string to variables
        String[] tokens = matchString.split(",");
        this.matchTitle = tokens[0];
        if(tokens[1].contains("true")) {
            this.adSetting = true;
        } else {
            this.adSetting = false;
        }
        this.servingPlayer = Integer.parseInt(tokens[2].substring(tokens[2].indexOf("=") + 1));
        this.setCount = Integer.parseInt(tokens[3].substring(tokens[3].indexOf("=") + 1));
        this.currentSet = Integer.parseInt(tokens[4].substring(tokens[4].indexOf("=") + 1));

    }

    public String getMatchTitle() {
        return this.matchTitle;
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

    /**
     * Checks if a player has won a game and if so starts a new game.
     */
    public void checkGame() {
        int playerOnePoint = playerOne.getPoint();
        int playerTwoPoint = playerTwo.getPoint();
        int difference = Math.abs(playerOnePoint - playerTwoPoint);
        if (this.playerOne.getCurrentGame(currentSet) == 6 && this.playerTwo.getCurrentGame(currentSet) == 6){
            if ((playerOnePoint + playerTwoPoint) % 2 == 1) {
                this.switchServingPlayer();
            }
            if(Math.abs(playerOnePoint - playerTwoPoint) > 1  && (playerOnePoint >= 7 || playerTwoPoint >= 7)) {
                if (playerOnePoint > playerTwoPoint) {
                    this.newGame(this.playerOne);
                } else {
                    this.newGame(this.playerTwo);
                }
            }
        } else if (this.adSetting) {
            if ((playerOnePoint > 3 || playerTwoPoint > 3) && difference > 1) {
                if (playerOnePoint > playerTwoPoint) {
                    this.newGame(this.playerOne);
                } else {
                    this.newGame(this.playerTwo);
                }
            }
        } else {
            if ((playerOnePoint > 3 || playerTwoPoint > 3)) {
                if (playerOnePoint > playerTwoPoint) {
                    this.newGame(this.playerOne);
                } else {
                    this.newGame(this.playerTwo);
                }
            }
        }
    }

    /**
     * Resets point counts from last game and creates a new game. Also calls the checkSet method
     * @param gameWinner the person who won the game.
     */
    public void newGame(Player gameWinner) {
        gameWinner.addGame(currentSet);
        this.switchServingPlayer();
        this.playerOne.setPoint(0);
        this.playerTwo.setPoint(0);
        this.checkSet();
    }

    /**
     * Method that determines the match score.
     * @return string array containing the serving player score
     * in idx 0 and the returner score in idx 1
     */
    public String[] matchScore() {
        String[] result = new String[2];
        int servingPlayerScore = this.getServingPlayer().getPoint();
        int returningPlayerScore = this.getReturningPlayer().getPoint();
        int servingPlayerGame = this.getServingPlayer().getCurrentGame(this.currentSet);
        int returningPlayerGame = this.getReturningPlayer().getCurrentGame(this.currentSet);

        //If a tiebreak is occurring just return the raw scores
        if (servingPlayerGame == 6 && returningPlayerGame == 6) {
            result[0] = Integer.toString(servingPlayerScore);
            result[1] = Integer.toString(returningPlayerScore);
            return result;
        }

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
        int playerOneGame = playerOne.getCurrentGame(this.currentSet);
        int playerTwoGame = playerTwo.getCurrentGame(this.currentSet);
        if (Math.abs(playerOneGame - playerTwoGame) > 1 && (playerOneGame >= 6 || playerTwoGame >= 6)) {
            if(playerOneGame > playerTwoGame) {
                this.newSet(this.playerOne);
            } else {
                this.newSet(this.playerTwo);
            }
        }

        //tiebreaker
        if (Math.abs(playerOneGame - playerTwoGame) == 1 && (playerOneGame >= 6 && playerTwoGame >= 6)){
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

    @Override
    public String toString() {
        return matchTitle +
                ",adSetting=" + adSetting +
                ",servingPlayer=" + servingPlayer +
                ",setCount=" + setCount +
                ",currentSet=" + currentSet +
                ",playerOne=" + playerOne +
                ",playerTwo=" + playerTwo +
                ",";
    }
}
