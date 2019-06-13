package com.slashlearn.tennisstats;

import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private int firstSetGame;
    private int secondSetGame;
    private int thirdSetGame;
    private int fourthSetGame;
    private int fifthSetGame;
    private int pointCount, firstServePointCount, secondServePointCount, returnPointCount, returnPointTotalPlayed;
    private int setCount;
    private int aceCount;
    private int firstServeCount;
    private int firstServeErrorCount;
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
        this.firstServeErrorCount = 0;
        this.secondServeCount = 0;
        this.doubleFaultCount = 0;
    }

    public Player(boolean loading, String playerStrIn) {
        String[] tokens = playerStrIn.split(",");
        this.name = tokens[0];
        this.firstSetGame = Integer.parseInt(tokens[1].substring(tokens[1].indexOf("=") + 1));
        this.secondSetGame = Integer.parseInt(tokens[2].substring(tokens[2].indexOf("=") + 1));
        this.thirdSetGame = Integer.parseInt(tokens[3].substring(tokens[3].indexOf("=") + 1));
        this.fourthSetGame = Integer.parseInt(tokens[4].substring(tokens[4].indexOf("=") + 1));
        this.fifthSetGame = Integer.parseInt(tokens[5].substring(tokens[5].indexOf("=") + 1));
        this.pointCount = Integer.parseInt(tokens[6].substring(tokens[6].indexOf("=") + 1));
        this.setCount = Integer.parseInt(tokens[7].substring(tokens[7].indexOf("=") + 1));
        this.aceCount = Integer.parseInt(tokens[8].substring(tokens[8].indexOf("=") + 1));
        this.firstServeCount = Integer.parseInt(tokens[9].substring(tokens[9].indexOf("=") + 1));
        this.firstServeErrorCount = Integer.parseInt(tokens[10].substring(tokens[10].indexOf("=") + 1));
        this.secondServeCount = Integer.parseInt(tokens[11].substring(tokens[11].indexOf("=") + 1));
        this.doubleFaultCount = Integer.parseInt(tokens[12].substring(tokens[12].indexOf("=") + 1));
        this.foreBaselineWinnerCount = Integer.parseInt(tokens[13].substring(tokens[13].indexOf("=") + 1));
        this.backBaselineWinnerCount = Integer.parseInt(tokens[14].substring(tokens[14].indexOf("=") + 1));
        this.foreApproachWinnerCount = Integer.parseInt(tokens[15].substring(tokens[15].indexOf("=") + 1));
        this.backApproachWinnerCount = Integer.parseInt(tokens[16].substring(tokens[16].indexOf("=") + 1));
        this.foreReturnWinnerCount = Integer.parseInt(tokens[17].substring(tokens[17].indexOf("=") + 1));
        this.backReturnWinnerCount = Integer.parseInt(tokens[18].substring(tokens[18].indexOf("=") + 1));
        this.foreVolleyWinnerCount = Integer.parseInt(tokens[19].substring(tokens[19].indexOf("=") + 1));
        this.backVolleyWinnerCount = Integer.parseInt(tokens[20].substring(tokens[20].indexOf("=") + 1));
        this.lostBaselineCount = Integer.parseInt(tokens[21].substring(tokens[21].indexOf("=") + 1));
        this.lostNetCount = Integer.parseInt(tokens[22].substring(tokens[22].indexOf("=") + 1));
        this.foreBaselineErrorU = Integer.parseInt(tokens[23].substring(tokens[23].indexOf("=") + 1));
        this.foreBaselineErrorF = Integer.parseInt(tokens[24].substring(tokens[24].indexOf("=") + 1));
        this.backBaselineErrorU = Integer.parseInt(tokens[25].substring(tokens[25].indexOf("=") + 1));
        this.backBaselineErrorF = Integer.parseInt(tokens[26].substring(tokens[26].indexOf("=") + 1));
        this.foreApproachErrorU = Integer.parseInt(tokens[27].substring(tokens[27].indexOf("=") + 1));
        this.foreApproachErrorF = Integer.parseInt(tokens[28].substring(tokens[28].indexOf("=") + 1));
        this.backApproachErrorU = Integer.parseInt(tokens[29].substring(tokens[29].indexOf("=") + 1));
        this.backApproachErrorF  = Integer.parseInt(tokens[30].substring(tokens[30].indexOf("=") + 1));
        this.foreReturnErrorU = Integer.parseInt(tokens[31].substring(tokens[31].indexOf("=") + 1));
        this.foreReturnErrorF = Integer.parseInt(tokens[32].substring(tokens[32].indexOf("=") + 1));
        this.backReturnErrorU = Integer.parseInt(tokens[33].substring(tokens[33].indexOf("=") + 1));
        this.backReturnErrorF = Integer.parseInt(tokens[34].substring(tokens[34].indexOf("=") + 1));
        this.foreVolleyErrorU = Integer.parseInt(tokens[35].substring(tokens[35].indexOf("=") + 1));
        this.foreVolleyErrorF = Integer.parseInt(tokens[36].substring(tokens[36].indexOf("=") + 1));
        this.backVolleyErrorU = Integer.parseInt(tokens[37].substring(tokens[37].indexOf("=") + 1));
        this.backVolleyErrorF = Integer.parseInt(tokens[38].substring(tokens[38].indexOf("=") + 1));
        this.wonBaselineCount = Integer.parseInt(tokens[39].substring(tokens[39].indexOf("=") + 1));
        this.wonApproachCount = Integer.parseInt(tokens[40].substring(tokens[40].indexOf("=") + 1));
        this.wonVolleyCount = Integer.parseInt(tokens[41].substring(tokens[41].indexOf("=") + 1));
        this.firstServePointCount = Integer.parseInt(tokens[42].substring(tokens[42].indexOf("=") + 1));
        this.secondServePointCount = Integer.parseInt(tokens[43].substring(tokens[43].indexOf("=") + 1));
        this.returnPointCount = Integer.parseInt(tokens[44].substring(tokens[44].indexOf("=") + 1));
        this.returnPointTotalPlayed = Integer.parseInt(tokens[45].substring(tokens[45].indexOf("=") + 1));
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

    //For stats Summary
    public String getAceCountString() {
        return Integer.toString(this.aceCount);
    }

    public String getDoubleFaultCountString() {
        return Integer.toString(this.doubleFaultCount);
    }

    public String getFirstServePercentage() {
        int firstSIn =  this.getFirstServeIn();
        long firstSPercentage = Math.round(((double) firstSIn / (double) this.firstServeCount) * 100);
        if (firstServeCount == 0)
            return "N/A (" + firstSIn + "/" + firstServeCount + ")";
        return firstSPercentage + "% ("  + firstSIn + "/" + firstServeCount + ")";
    }

    public String getFirstServePointsWon() {
        long firstSWonPercentage = Math.round(((double) this.firstServePointCount /(double)this.getFirstServeIn()) * 100);
        if (this.getFirstServeIn() == 0)
            return "N/A (" + this.firstServePointCount + "/" + this.getFirstServeIn() + ")";
        return firstSWonPercentage + "% (" + this.firstServePointCount + "/" + this.getFirstServeIn() + ")";
    }

    public String getSecondServePointsWon() {
        long secondSWonPercentage = Math.round(((double) this.secondServePointCount / (double) this.secondServeCount) * 100);
        if (this.secondServeCount == 0)
            return "N/A (" + this.secondServePointCount + "/" + this.secondServeCount + ")";
        return secondSWonPercentage + "% (" + this.secondServePointCount + "/" + this.secondServeCount + ")";
    }

    public String getTotalServePointsWon() {
        int totalServePointsWon = this.firstServePointCount + this.secondServePointCount;
        int totalServePoints = this.getFirstServeIn() + this.secondServeCount;
        long totalServePercentage = Math.round(((double) totalServePointsWon/ (double) totalServePoints) * 100);
        if (totalServePoints == 0)
            return "N/A (" + totalServePointsWon + "/" + totalServePoints + ")";
        return totalServePercentage + "% (" + totalServePointsWon + "/" + totalServePoints + ")";
    }

    public String getTotalReturnPointsWon() {
        long totalReturnPercentage = Math.round(((double) this.returnPointCount / (double) this.returnPointTotalPlayed) * 100);
        if (this.returnPointTotalPlayed == 0)
            return "N/A (" + this.returnPointCount + "/" + this.returnPointTotalPlayed + ")";
        return totalReturnPercentage + "% (" + this.returnPointCount + "/" + this.returnPointTotalPlayed + ")";
    }

    public String getTotalWinners() {
        int totalWinners = foreBaselineWinnerCount + backBaselineWinnerCount + foreApproachWinnerCount + backApproachWinnerCount
                + foreVolleyWinnerCount + backVolleyWinnerCount + foreReturnWinnerCount + backReturnWinnerCount + aceCount;
        return Integer.toString(totalWinners);
    }

    public String getTotalUnforcedErrors() {
        int totalUError = foreBaselineErrorU + backBaselineErrorU + foreApproachErrorU + backBaselineErrorU
                + foreVolleyErrorU + backVolleyErrorU + foreReturnErrorU + backReturnErrorF + doubleFaultCount;
        return Integer.toString(totalUError);
    }

    public String getTotalPointsWon() {
        int totalPointsPlayed = this.getFirstServeIn() + this.secondServeCount + this.returnPointTotalPlayed;
        int totalPointsWon = this.returnPointCount + this.firstServePointCount + this.secondServePointCount;
        long totalServePercentage = Math.round(((double) totalPointsWon/ (double) totalPointsPlayed) * 100);
        if (totalPointsPlayed == 0)
            return "N/A" + totalPointsWon + "/" + totalPointsPlayed + ")";
        return totalServePercentage + "% (" + totalPointsWon + "/" + totalPointsPlayed + ")";
    }

    public String getTotalNetPointsWon() {
        int wonNetPoints = this.wonVolleyCount + this.backVolleyWinnerCount + this.foreVolleyWinnerCount;
        int totalNetPoints = this.backVolleyWinnerCount + this.foreVolleyWinnerCount + this.lostNetCount + this.wonVolleyCount + getTotalNetErrors();
        long netPointsPercentage = Math.round(((double) wonNetPoints/ (double) totalNetPoints) * 100);
        return netPointsPercentage + "% (" + wonNetPoints + "/" + totalNetPoints + ")";
    }

    private int getTotalNetErrors(){
        return this.foreVolleyErrorF + this.foreVolleyErrorU + this.backReturnErrorF + this.backVolleyErrorU;
    }

    private int getSecondServeIn() {
        return this.secondServeCount - this.doubleFaultCount;
    }

    private int getFirstServeIn() {
        return this.firstServeCount - this.firstServeErrorCount;
    }
    //End stats Summary


    //Detail Stats
    public String getSecondServePercentage() {
        int secondSIn = this.getSecondServeIn();
        long secondSPercentage = Math.round(((double) secondSIn/ (double) this.secondServeCount) * 100);
        if (secondServeCount == 0)
            return "N/A (" + secondSIn + "/" + secondServeCount + " )";
        return secondSPercentage + "% (" + secondSIn + "/" + secondServeCount + ")";
    }

    public String getForehandWinners() {
        int forehandWinnerTotal = foreBaselineWinnerCount + foreApproachWinnerCount + foreReturnWinnerCount;
        return Integer.toString(forehandWinnerTotal);
    }

    public String getBackhandWinners() {
        int backhandWinnerTotal = backBaselineWinnerCount + backApproachWinnerCount + backReturnWinnerCount;
        return Integer.toString(backhandWinnerTotal);
    }

    public String getApproachWinners() {
        int approachWinners = foreApproachWinnerCount + backApproachWinnerCount;
        return Integer.toString(approachWinners);
    }

    public String getVolleyWinnerCount() {
        int volleyWinnerTotal = backVolleyWinnerCount + foreVolleyWinnerCount;
        return Integer.toString(volleyWinnerTotal);
    }

    public String getReturnWinnerCount() {
        int returnWinnerTotal = foreReturnWinnerCount + backReturnWinnerCount;
        return Integer.toString(returnWinnerTotal);
    }

    public String getTotalForehandUnforced(){
        int forehandUnforcedTotal = foreBaselineErrorU + foreApproachErrorU + foreReturnErrorU;
        return Integer.toString(forehandUnforcedTotal);
    }

    public String getTotalBackhandUnforced() {
        int backhandUnforcedTotal = backBaselineErrorU + backApproachErrorU + backReturnErrorU;
        return Integer.toString(backhandUnforcedTotal);
    }

    public String getApproachUnforced() {
        return Integer.toString(foreApproachErrorU + backApproachErrorU);
    }

    public String getTotalVolleyUnforced() {
        return Integer.toString(foreVolleyErrorU + backVolleyErrorU);
    }

    public String getTotalReturnUnforced() {
        return Integer.toString(foreReturnErrorU + backVolleyErrorU);
    }

    public String getTotalForcedErrors() {
        int forcedTotal = foreApproachErrorF + backApproachErrorF + foreBaselineErrorF + backBaselineErrorF
                + foreReturnErrorF + backReturnErrorF + foreVolleyErrorF + backVolleyErrorF;
        return Integer.toString(forcedTotal);
    }

    public String getTotalForehandForced() {
        int foreForcedTotal = foreApproachErrorF + foreReturnErrorF + foreBaselineErrorF;
        return Integer.toString(foreForcedTotal);
    }

    public String getTotalBackhandForced() {
        int backForcedTotal = backApproachErrorF + backReturnErrorF + backBaselineErrorF;
        return Integer.toString(backForcedTotal);
    }

    public String getApproachForced() {
        return Integer.toString(foreApproachErrorF + backApproachErrorF);
    }

    public String getTotalVolleyForced() {
        return Integer.toString(backVolleyErrorF + foreVolleyErrorF);
    }

    public String getTotalReturnForced() {
        return Integer.toString(foreApproachErrorF + backApproachErrorF);
    }
    //End Detail Stats

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

    public void addFirstServePointCount() {
        this.firstServePointCount++;
    }

    public void addSecondServePointCount() {
        this.secondServePointCount++;
    }

    public void addReturnPointCount() {
        this.returnPointCount++;
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
        this.firstServeErrorCount++;
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

    public void addReturnPointPlayed() {
        this.returnPointTotalPlayed++;
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
        return  "[" + name +
                ",firstSetGame=" + firstSetGame +
                ",secondSetGame=" + secondSetGame +
                ",thirdSetGame=" + thirdSetGame +
                ",fourthSetGame=" + fourthSetGame +
                ",fifthSetGame=" + fifthSetGame +
                ",pointCount=" + pointCount +
                ",setCount=" + setCount +
                ",aceCount=" + aceCount +
                ",firstServeCount=" + firstServeCount +
                ",firstServeErrorCount=" + firstServeErrorCount +
                ",secondServeCount=" + secondServeCount +
                ",doubleFaultCount=" + doubleFaultCount +
                ",foreBaselineWinnerCount=" + foreBaselineWinnerCount +
                ",backBaselineWinnerCount=" + backBaselineWinnerCount +
                ",foreApproachWinnerCount=" + foreApproachWinnerCount +
                ",backApproachWinnerCount=" + backApproachWinnerCount +
                ",foreReturnWinnerCount=" + foreReturnWinnerCount +
                ",backReturnWinnerCount=" + backReturnWinnerCount +
                ",foreVolleyWinnerCount=" + foreVolleyWinnerCount +
                ",backVolleyWinnerCount=" + backVolleyWinnerCount +
                ",lostBaselineCount=" + lostBaselineCount +
                ",lostNetCount=" + lostNetCount +
                ",foreBaselineErrorU=" + foreBaselineErrorU +
                ",foreBaselineErrorF=" + foreBaselineErrorF +
                ",backBaselineErrorU=" + backBaselineErrorU +
                ",backBaselineErrorF=" + backBaselineErrorF +
                ",foreApproachErrorU=" + foreApproachErrorU +
                ",foreApproachErrorF=" + foreApproachErrorF +
                ",backApproachErrorU=" + backApproachErrorU +
                ",backApproachErrorF=" + backApproachErrorF +
                ",foreReturnErrorU=" + foreReturnErrorU +
                ",foreReturnErrorF=" + foreReturnErrorF +
                ",backReturnErrorU=" + backReturnErrorU +
                ",backReturnErrorF=" + backReturnErrorF +
                ",foreVolleyErrorU=" + foreVolleyErrorU +
                ",foreVolleyErrorF=" + foreVolleyErrorF +
                ",backVolleyErrorU=" + backVolleyErrorU +
                ",backVolleyErrorF=" + backVolleyErrorF +
                ",wonBaselineCount=" + wonBaselineCount +
                ",wonApproachCount=" + wonApproachCount +
                ",wonVolleyCount=" + wonVolleyCount +
                ",firstServePointCount=" + firstServePointCount +
                ",secondServePointCount=" + secondServePointCount +
                ",returnPointCount=" + returnPointCount +
                ",returnPointTotalPlayed=" + returnPointTotalPlayed +
                "] ";
    }
}
