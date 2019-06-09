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
    private int setCount;

    private int aceCount;
    private int firstServeCount;
    private int firstServerErrorCount;
    private int secondServeCount;
    private int doubleFaultCount;
    private int foreBaselineWinnerCount;
    private int backBaselineWinnerCount;
    private int foreApproachWinnerCount;
    private int backApproachWinnerCount;
    private int foreReturnWinnerCount;
    private int backReturnWinnerCount;
    private int foreVolleyWinnerCount;
    private int backVolleyWinnerCount;
    private int lostBaselineCount;
    private int lostNetCount;

    //errors
    private int foreBaselineErrorU, foreBaselineErrorF, backBaselineErrorU, backBaselineErrorF;
    private int foreApproachErrorU, foreApproachErrorF, backApproachErrorU, backApproachErrorF;
    private int foreReturnErrorU, foreReturnErrorF, backReturnErrorU, backReturnErrorF;
    private int foreVolleyErrorU, foreVolleyErrorF, backVolleyErrorU, backVolleyErrorF;
    private int wonBaselineCount, wonApproachCount, wonVolleyCount;

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

    public String pointToString() {
        String result = "";
        switch(this.pointCount) {
            case 0:
                result = "0"; break;
            case 1:
                result = "15"; break;
            case 2:
                result = "30"; break;
            case 3:
                result = "40"; break;
            default:
                result = "Error";
        }
        return result;
    }

    public int getPoint() {
        return this.pointCount;
    }

    public void setPoint(int val) {
        this.pointCount = val;
    }

    public int getCurrentGame(int currentSetIn) {
        switch (currentSetIn) {
            case 1:
                return firstSetGame;
            case 2:
                return secondSetGame;
            case 3:
                return thirdSetGame;
            case 4:
                return fourthSetGame;
            case 5:
                return fifthSetGame;
            default:
                return -1;
        }
    }

    public int getSetCount() {
        return this.setCount;
    }

    public void addSet() {
        this.setCount++;
    }

    public int getForeBaselineWinnerCount() {
        return this.foreBaselineWinnerCount;
    }

    public int getBackBaselineWinnerCount() {
        return this.backBaselineWinnerCount;
    }

    public int getForeApproachWinnerCount() {
        return this.foreApproachWinnerCount;
    }

    public int getBackApproachWinnerCount() {
        return this.backApproachWinnerCount;
    }

    public int getForeReturnWinnerCount() {
        return this.foreReturnWinnerCount;
    }

    public int getBackReturnWinnerCount() {
        return this.backReturnWinnerCount;
    }

    public int getForeVolleyWinnerCount() {
        return this.foreVolleyWinnerCount;
    }

    public int getBackVolleyWinnerCount() {
        return this.backVolleyWinnerCount;
    }

    public int getLostBaselineCount() {
        return this.lostBaselineCount;
    }

    public int getLostNetCount() {
        return this.lostNetCount;
    }

    public void addPoint() {
        this.pointCount++;
    }

    public void addGame(int currentSet) {
        switch (currentSet) {
            case 1:
                this.firstSetGame++;
                break;
            case 2:
                this.secondSetGame++;
                break;
            case 3:
                this.thirdSetGame++;
                break;
            case 4:
                this.fourthSetGame++;
                break;
            default:
                this.fifthSetGame++;
        }
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

    public void addForeBaselineWinner() {
        this.foreBaselineWinnerCount++;
    }

    public void addBackBaselineWinner() {
        this.backBaselineWinnerCount++;
    }

    public void addForeApproachWinner() {
        this.foreApproachWinnerCount++;
    }

    public void addBackApproachWinner() {
        this.backApproachWinnerCount++;
    }

    public void addForeReturnWinner() {
        this.foreReturnWinnerCount++;
    }

    public void addBackReturnWinner() {
        this.backReturnWinnerCount++;
    }

    public void addForeVolleyWinner() {
        this.foreVolleyWinnerCount++;
    }

    public void addBackVolleyWinner() {
        this.backVolleyWinnerCount++;
    }

    public void addLostBaseline() {
        this.lostBaselineCount++;
    }

    public void addLostNet() {
        this.lostNetCount++;
    }


    //errors

    public void giveWinner(int winnerPositionIDIn, int wingWinnerIDIn) {
        switch (winnerPositionIDIn) {
            case R.id.baselineButton:
                if (wingWinnerIDIn == R.id.forehandButton) {
                    this.addForeBaselineWinner();
                } else {
                    this.addBackBaselineWinner();
                }
                break;
            case R.id.approachButton:
                if (wingWinnerIDIn == R.id.forehandButton) {
                    this.addForeApproachWinner();
                } else {
                    this.addBackApproachWinner();
                }
                break;
            case R.id.onReturn:
                if (wingWinnerIDIn == R.id.forehandButton) {
                    this.addForeReturnWinner();
                } else {
                    this.addBackReturnWinner();
                }
                break;
            default:
                if (wingWinnerIDIn == R.id.forehandButton) {
                    this.addForeVolleyWinner();
                } else {
                    this.addBackVolleyWinner();
                }
                break;
        }
    }

    public void giveError(int errorPositionGrpID, int wingErrorGrpID, int forcedSetGrpID) {
        if (forcedSetGrpID == R.id.forcedButton) {
            this.giveForcedError(errorPositionGrpID, wingErrorGrpID);
        } else {
            this.giveUnforcedError(errorPositionGrpID, wingErrorGrpID);
        }
    }

    private void giveForcedError(int errorPositionGrpIDIn, int wingErrorGrpIDIn) {
        if (wingErrorGrpIDIn == R.id.forehandButtonE) {
            switch (errorPositionGrpIDIn){
                case R.id.baselineButtonE:
                    this.foreBaselineErrorF++;
                    break;
                case R.id.approachButtonE:
                    this.foreApproachErrorF++;
                    break;
                case R.id.onReturnE:
                    this.foreReturnErrorF++;
                    break;
                default:
                    this.foreVolleyErrorF++;
                    break;
            }
        } else {
            switch (errorPositionGrpIDIn){
                case R.id.baselineButtonE:
                    this.backBaselineErrorF++;
                    break;
                case R.id.approachButtonE:
                    this.backApproachErrorF++;
                    break;
                case R.id.onReturnE:
                    this.backReturnErrorF++;
                    break;
                default:
                    this.backVolleyErrorF++;
                    break;
            }
        }
    }

    private void giveUnforcedError(int errorPositionGrpIDIn, int wingErrorGrpIDIn) {
        if (wingErrorGrpIDIn == R.id.forehandButtonE) {
            switch (errorPositionGrpIDIn){
                case R.id.baselineButtonE:
                    this.foreBaselineErrorU++;
                    break;
                case R.id.approachButtonE:
                    this.foreApproachErrorU++;
                    break;
                case R.id.onReturnE:
                    this.foreReturnErrorU++;
                    break;
                default:
                    this.foreVolleyErrorU++;
                    break;
            }
        } else {
            switch (errorPositionGrpIDIn){
                case R.id.baselineButtonE:
                    this.backBaselineErrorU++;
                    break;
                case R.id.approachButtonE:
                    this.backApproachErrorU++;
                    break;
                case R.id.onReturnE:
                    this.backReturnErrorU++;
                    break;
                default:
                    this.backVolleyErrorU++;
                    break;
            }
        }
    }

    public void pointLostPosition(int loserPositionID) {
        if (loserPositionID == R.id.lostBaselineButton) {
            this.addLostBaseline();
        } else {
            this.addLostNet();
        }
    }

    public void pointWonPosition(int pointWonPosGrpIDIn) {
        if (pointWonPosGrpIDIn == R.id.pointWonBaseline) {
            this.wonBaselineCount++;
        } else if (pointWonPosGrpIDIn == R.id.pointWonApproach) {
            this.wonApproachCount++;
        } else {
            this.wonVolleyCount++;
        }
    }

    @Override
    public String toString() {
        return  "[name=" + name +
                ", firstSetGame=" + firstSetGame +
                ", secondSetGame=" + secondSetGame +
                ", thirdSetGame=" + thirdSetGame +
                ", fourthSetGame=" + fourthSetGame +
                ", fifthSetGame=" + fifthSetGame +
                ", pointCount=" + pointCount +
                ", setCount=" + setCount +
                ", aceCount=" + aceCount +
                ", firstServeCount=" + firstServeCount +
                ", firstServerErrorCount=" + firstServerErrorCount +
                ", secondServeCount=" + secondServeCount +
                ", doubleFaultCount=" + doubleFaultCount +
                ", foreBaselineWinnerCount=" + foreBaselineWinnerCount +
                ", backBaselineWinnerCount=" + backBaselineWinnerCount +
                ", foreApproachWinnerCount=" + foreApproachWinnerCount +
                ", backApproachWinnerCount=" + backApproachWinnerCount +
                ", foreReturnWinnerCount=" + foreReturnWinnerCount +
                ", backReturnWinnerCount=" + backReturnWinnerCount +
                ", foreVolleyWinnerCount=" + foreVolleyWinnerCount +
                ", backVolleyWinnerCount=" + backVolleyWinnerCount +
                ", lostBaselineCount=" + lostBaselineCount +
                ", lostNetCount=" + lostNetCount +
                ", foreBaselineErrorU=" + foreBaselineErrorU +
                ", foreBaselineErrorF=" + foreBaselineErrorF +
                ", backBaselineErrorU=" + backBaselineErrorU +
                ", backBaselineErrorF=" + backBaselineErrorF +
                ", foreApproachErrorU=" + foreApproachErrorU +
                ", foreApproachErrorF=" + foreApproachErrorF +
                ", backApproachErrorU=" + backApproachErrorU +
                ", backApproachErrorF=" + backApproachErrorF +
                ", foreReturnErrorU=" + foreReturnErrorU +
                ", foreReturnErrorF=" + foreReturnErrorF +
                ", backReturnErrorU=" + backReturnErrorU +
                ", backReturnErrorF=" + backReturnErrorF +
                ", foreVolleyErrorU=" + foreVolleyErrorU +
                ", foreVolleyErrorF=" + foreVolleyErrorF +
                ", backVolleyErrorU=" + backVolleyErrorU +
                ", backVolleyErrorF=" + backVolleyErrorF +
                ", wonBaselineCount=" + wonBaselineCount +
                ", wonApproachCount=" + wonApproachCount +
                ", wonVolleyCount=" + wonVolleyCount +
                "] ";
    }
}
