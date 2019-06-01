package com.slashlearn.tennisstats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartPoint extends AppCompatActivity implements NewPointListener {

    public static FragmentManager fragManager;
    public static Match currentMatch;

    private void updateScore(Match matchIn){

        Player player1 = matchIn.getPlayerOne();
        Player player2 = matchIn.getPlayerTwo();

        //extra updates for additional sets
        switch(matchIn.getSetCount()) {
            case 1:
                TextView p1Set4 = (TextView) findViewById(R.id.p1Set4);
                TextView p2Set4 = (TextView) findViewById(R.id.p2Set4);
                p1Set4.setText(Integer.toString(player1.getFourthSetGame()));
                p2Set4.setText(Integer.toString(player2.getFourthSetGame()));
                TextView p1Set5 = (TextView) findViewById(R.id.p1Set5);
                TextView p2Set5 = (TextView) findViewById(R.id.p2Set5);
                p1Set5.setText(Integer.toString(player1.getFifthSetGame()));
                p2Set5.setText(Integer.toString(player2.getFifthSetGame()));
                p1Set4.setVisibility(View.VISIBLE);
                p2Set4.setVisibility(View.VISIBLE);
                p1Set5.setVisibility(View.VISIBLE);
                p2Set5.setVisibility(View.VISIBLE);
            case 0:
                TextView p1Set2 = (TextView) findViewById(R.id.p1Set2);
                TextView p2Set2 = (TextView) findViewById(R.id.p2Set2);
                p1Set2.setText(Integer.toString(player1.getSecondSetGame()));
                p2Set2.setText(Integer.toString(player2.getSecondSetGame()));
                TextView p1Set3 = (TextView) findViewById(R.id.p1Set3);
                TextView p2Set3 = (TextView) findViewById(R.id.p2Set3);
                p1Set3.setText(Integer.toString(player1.getThirdSetGame()));
                p2Set3.setText(Integer.toString(player2.getThirdSetGame()));
                p1Set2.setVisibility(View.VISIBLE);
                p2Set2.setVisibility(View.VISIBLE);
                p1Set3.setVisibility(View.VISIBLE);
                p2Set3.setVisibility(View.VISIBLE);
            default:
                TextView p1Set1 = (TextView) findViewById(R.id.p1Set1);
                TextView p2Set1 = (TextView) findViewById(R.id.p2Set1);
                p1Set1.setText(Integer.toString(player1.getFirstSetGame()));
                p2Set1.setText(Integer.toString(player2.getFirstSetGame()));
                p1Set1.setVisibility(View.VISIBLE);
                p2Set1.setVisibility(View.VISIBLE);
                break;
        }

    }

    private void updatePlayerDisplay(Match matchIn) {
        //top names
        TextView p1NameDisplay1 = (TextView) findViewById(R.id.p1NameDisplay1);
        TextView p2NameDisplay2 = (TextView) findViewById(R.id.p2NameDisplay1);
        String playerOneName = matchIn.getPlayerOne().getName();
        String playerTwoName = matchIn.getPlayerTwo().getName();
        p1NameDisplay1.setText(playerOneName);
        p2NameDisplay2.setText(playerTwoName);

        //point update names
        TextView servePlayerDisplay = (TextView) findViewById(R.id.servePlayer);
        TextView returnPlayerDisplay = (TextView) findViewById(R.id.returnPlayer);
        TextView servePointDisplay = (TextView) findViewById(R.id.serveScore);
        TextView returnPointDisplay = (TextView) findViewById(R.id.returnScore);
        String servingFirstName = matchIn.getServingPlayer().getFirstName();
        String returnFirstName = matchIn.getReturningPlayer().getFirstName();
        String servingPlayerPoint = matchIn.getServingPlayer().getPointString();
        String returnPlayerPoint = matchIn.getReturningPlayer().getPointString();
        servePlayerDisplay.setText(servingFirstName);
        returnPlayerDisplay.setText(returnFirstName);
        servePointDisplay.setText(servingPlayerPoint);
        returnPointDisplay.setText(returnPlayerPoint);
    }

    public void newPoint() {
        //clear the fragment stack
        fragManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        //update score and player display
        //updateScore(StartPoint.currentMatch);
        //updatePlayerDisplay(StartPoint.currentMatch);

        //new Point fragment
        NewPointFragment newPointFrag = new NewPointFragment();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.add(R.id.fragmentContainer, newPointFrag, null);
        fragTransaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_point);



        if (getIntent().hasExtra("currentMatch")) {
            currentMatch = (Match) getIntent().getSerializableExtra("currentMatch");
            updatePlayerDisplay(currentMatch); //displaying player one and two names
            updateScore(currentMatch); //Setting the scores:
        }


        //fragment section
        fragManager = getSupportFragmentManager();
        if (findViewById(R.id.fragmentContainer) != null) {

            //fragment overlapping? check this later
            if (savedInstanceState != null) {
                return;
            }
            newPoint();
        }
    }

    //implement New Point Fragment Listener
    @Override
    public void newPointFromFragment() {
        updatePlayerDisplay(StartPoint.currentMatch);
        updateScore(StartPoint.currentMatch);
        newPoint();
    }

    @Override
    public void errorFromFragment(int errorKey) {
        ErrorFragment errorFrag = new ErrorFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("errorKey", errorKey);
        errorFrag.setArguments(bundle);
        StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, errorFrag,null).addToBackStack(null).commit();
    }

    @Override
    public void winnerFromFragment(int winnerKey) {
        WinnerFragment winnerFrag = new WinnerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("winnerKey", winnerKey);
        winnerFrag.setArguments(bundle);
        StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, winnerFrag,null).addToBackStack(null).commit();
    }
}
