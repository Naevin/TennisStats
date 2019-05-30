package com.slashlearn.tennisstats;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private int firstSetGame;
    private int secondSetGame;
    private int thirdSetGame;
    private int fourthSetGame;
    private int fifthSetGame;
    private int pointCount;

    public Player(String nameIn) {
        this.name = nameIn;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstName() {
        String[] tokens = this.name.split(" ");
        return tokens[0];
    }

    public int getFirstSetGame() {
        return this.firstSetGame;
    }

    public int getSecondSetGame() {
        return this.secondSetGame;
    }

    public int getThirdSetGame() {
        return this.thirdSetGame;
    }

    public int getFourthSetGame() {
        return this.fourthSetGame;
    }

    public int getFifthSetGame() {
        return this.fifthSetGame;
    }

    public int getPointCount() {
        return this.pointCount;
    }

    public void addPoint() {
        this.pointCount++;
    }
}
