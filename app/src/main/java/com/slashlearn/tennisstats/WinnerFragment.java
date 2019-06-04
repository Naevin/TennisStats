package com.slashlearn.tennisstats;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WinnerFragment extends Fragment {

    private NewPointListener newPointFragList;

    public WinnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_winner, container, false);

        //Assigning Buttons
        Button winnerBackBtn = view.findViewById(R.id.winnerBackButton);
        Button nextBtn = view.findViewById(R.id.winnerNextButton);
        final RadioGroup winnerPositionGrp = (RadioGroup) view.findViewById(R.id.winnerPosition);
        final RadioGroup wingWinnerGrp = (RadioGroup) view.findViewById(R.id.wingWinner);
        final RadioGroup pointLostPosGrp = view.findViewById(R.id.pointLostByWPosition);

        //Assigning TextView
        TextView winnerMadeByW = (TextView) view.findViewById(R.id.winnerMadeByW);
        TextView pointLostByW = (TextView) view.findViewById(R.id.pointLostByW);

        Bundle bundle = getArguments();
        int winnerKey = bundle.getInt("winnerKey");

        String servingPlayerFName = StartPoint.currentMatch.getServingPlayer().getFirstName();
        String returnPlayerFName = StartPoint.currentMatch.getReturningPlayer().getFirstName();
        final Player winnerHitPlayer, lostPointPlayer;
        String hitWinner = " hit a winner";
        String lostPoint = " lost the point";

        if (winnerKey == 0) { //if the server hit winner
            hitWinner = servingPlayerFName + hitWinner;
            lostPoint = returnPlayerFName + lostPoint;
            winnerHitPlayer = StartPoint.currentMatch.getServingPlayer();
            lostPointPlayer = StartPoint.currentMatch.getReturningPlayer();

        } else { //else returner hit the winner
            hitWinner = returnPlayerFName + hitWinner;
            lostPoint = servingPlayerFName + lostPoint;
            winnerHitPlayer = StartPoint.currentMatch.getReturningPlayer();
            lostPointPlayer = StartPoint.currentMatch.getServingPlayer();
        }
        pointLostByW.setText(lostPoint);
        winnerMadeByW.setText(hitWinner);

        //Back Button
        winnerBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPoint.fragManager.popBackStack();
            }
        });

        //next Button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update stats
                int winnerPositionID = winnerPositionGrp.getCheckedRadioButtonId();
                int wingWinnerID = wingWinnerGrp.getCheckedRadioButtonId();
                int loserPositionID = pointLostPosGrp.getCheckedRadioButtonId();
                winnerHitPlayer.giveWinner(winnerPositionID, wingWinnerID);
                lostPointPlayer.pointLostPosition(loserPositionID);

                //add points
                winnerHitPlayer.addPoint();
                newPointFragList.newPointFromFragment();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try {
            newPointFragList = (NewPointListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException("must override on Message Read");
        }
    }

}
