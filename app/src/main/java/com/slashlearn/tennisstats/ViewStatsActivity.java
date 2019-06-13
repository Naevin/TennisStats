package com.slashlearn.tennisstats;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewStatsActivity extends AppCompatActivity {

    public static FragmentManager fragManager;
    public static Match currentMatch;

    private void updateScore(Match matchIn){

        Player player1 = matchIn.getPlayerOne();
        Player player2 = matchIn.getPlayerTwo();

        //extra updates for additional sets
        switch(matchIn.getSetCount()) {
            case 1:
                TextView p1Set4 = (TextView) findViewById(R.id.stats_p1Set4);
                TextView p2Set4 = (TextView) findViewById(R.id.stats_p2Set4);
                p1Set4.setText(Integer.toString(player1.getFourthSetGame()));
                p2Set4.setText(Integer.toString(player2.getFourthSetGame()));
                TextView p1Set5 = (TextView) findViewById(R.id.stats_p1Set5);
                TextView p2Set5 = (TextView) findViewById(R.id.stats_p2Set5);
                p1Set5.setText(Integer.toString(player1.getFifthSetGame()));
                p2Set5.setText(Integer.toString(player2.getFifthSetGame()));
                p1Set4.setVisibility(View.VISIBLE);
                p2Set4.setVisibility(View.VISIBLE);
                p1Set5.setVisibility(View.VISIBLE);
                p2Set5.setVisibility(View.VISIBLE);
            case 0:
                TextView p1Set2 = (TextView) findViewById(R.id.stats_p1Set2);
                TextView p2Set2 = (TextView) findViewById(R.id.stats_p2Set2);
                p1Set2.setText(Integer.toString(player1.getSecondSetGame()));
                p2Set2.setText(Integer.toString(player2.getSecondSetGame()));
                TextView p1Set3 = (TextView) findViewById(R.id.stats_p1Set3);
                TextView p2Set3 = (TextView) findViewById(R.id.stats_p2Set3);
                p1Set3.setText(Integer.toString(player1.getThirdSetGame()));
                p2Set3.setText(Integer.toString(player2.getThirdSetGame()));
                p1Set2.setVisibility(View.VISIBLE);
                p2Set2.setVisibility(View.VISIBLE);
                p1Set3.setVisibility(View.VISIBLE);
                p2Set3.setVisibility(View.VISIBLE);
            default:
                TextView p1Set1 = (TextView) findViewById(R.id.stats_p1Set1);
                TextView p2Set1 = (TextView) findViewById(R.id.stats_p2Set1);
                p1Set1.setText(Integer.toString(player1.getFirstSetGame()));
                p2Set1.setText(Integer.toString(player2.getFirstSetGame()));
                p1Set1.setVisibility(View.VISIBLE);
                p2Set1.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void updatePlayerDisplay(Match matchIn) {
        //top names
        TextView p1NameDisplay1 = (TextView) findViewById(R.id.stats_p1NameDisplay1);
        TextView p2NameDisplay2 = (TextView) findViewById(R.id.stats_p2NameDisplay1);
        String playerOneName = matchIn.getPlayerOne().getName();
        String playerTwoName = matchIn.getPlayerTwo().getName();
        p1NameDisplay1.setText(playerOneName);
        p2NameDisplay2.setText(playerTwoName);

        //point update names
        TextView servePlayerDisplay = (TextView) findViewById(R.id.stats_servePlayer);
        TextView returnPlayerDisplay = (TextView) findViewById(R.id.stats_returnPlayer);
        TextView servePointDisplay = (TextView) findViewById(R.id.stats_serveScore);
        TextView returnPointDisplay = (TextView) findViewById(R.id.stats_returnScore);
        String[] score = matchIn.matchScore();
        String servingPlayerPoint = score[0];
        String returnPlayerPoint = score[1];
        servePlayerDisplay.setText(matchIn.getServingPlayer().getFirstName());
        returnPlayerDisplay.setText(matchIn.getReturningPlayer().getFirstName());
        servePointDisplay.setText(servingPlayerPoint);
        returnPointDisplay.setText(returnPlayerPoint);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.summary_stats:
                    StatsSummaryFragment statsSummaryFrag = new StatsSummaryFragment();
                    fragManager.beginTransaction().replace(R.id.stats_fragmentContainer, statsSummaryFrag,null).commit();
                    return true;
                case R.id.detail_stats:
                    StatsDetailFragment statsDetailFrag = new StatsDetailFragment();
                    fragManager.beginTransaction().replace(R.id.stats_fragmentContainer, statsDetailFrag,null).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (getIntent().hasExtra("currentMatch")) {
            currentMatch = (Match) getIntent().getSerializableExtra("currentMatch");
            updatePlayerDisplay(currentMatch); //displaying player one and two names
            updateScore(currentMatch); //Setting the scores:
        }

        fragManager = getSupportFragmentManager();
        if (findViewById(R.id.stats_fragmentContainer) != null) {
            //fragment overlapping? check this later
            if (savedInstanceState != null) {
                return;
            }
            //new Point fragment
            StatsSummaryFragment statsSummaryFrag = new StatsSummaryFragment();
            FragmentTransaction fragTransaction = fragManager.beginTransaction();
            fragTransaction.add(R.id.stats_fragmentContainer, statsSummaryFrag, null);
            fragTransaction.commit();
        }
    }

}
