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

    private int aceCount;
    private int firstServeCount;
    private int firstServerErrorCount;
    private int secondServeCount;
    private int doubleFaultCount;

    public Player(String nameIn) {
        this.name = nameIn;
        this.firstSetGame = 0;
        this.secondSetGame = 0;
        this.thirdSetGame = 0;
        this.fourthSetGame = 0;
        this.fifthSetGame = 0;
        this.pointCount = 0;
        this.aceCount = 0;
        this.firstServeCount = 0;
        this.firstServerErrorCount = 0;
        this.secondServeCount = 0;
        this.doubleFaultCount = 0;
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

    public void addAce() {
        this.aceCount++;
    }

    public void addFirstServeErrorCount() {
        this.firstServerErrorCount++;
    }

    public void addFirstServeCount() {
        this.firstServeCount++;
    }

    public void addSecondServeCount() {
        this.secondServeCount++;
    }

    public void addDoubleFaultCount() {
        this.doubleFaultCount++;
    }
}
