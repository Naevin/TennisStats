package com.slashlearn.tennisstats;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondServeFragment extends Fragment {

    private NewPointListener newPointFragList;

    public SecondServeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_serve, container, false);

        //Assigning Buttons
        Button secondServeAceBtn = view.findViewById(R.id.secondServeAce);
        Button doubleFaultBtn = view.findViewById(R.id.doubleFaultButton);
        Button returnWinnerSSBtn = view.findViewById(R.id.returnWinnerSSButton);
        Button returnErrorSSBtn = view.findViewById(R.id.returnErrorSSButton);
        Button rallyStartedSSBtn = view.findViewById(R.id.rallyStartedSSButton);
        Button secondServeBackBtn = view.findViewById(R.id.secondServeBackButton);

        //Second Serve Ace Button On Click
        secondServeAceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player servingPlayer = StartPoint.currentMatch.getServingPlayer();
                servingPlayer.addPoint();
                servingPlayer.addSecondServePointCount();
                servingPlayer.addAce();
                servingPlayer.addSecondServeCount();
                StartPoint.currentMatch.getReturningPlayer().addReturnPointPlayed();
                newPointFragList.newPointFromFragment();
            }
        });

        //Double Fault Button On Click
        doubleFaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player servingPlayer = StartPoint.currentMatch.getServingPlayer();
                Player returningPlayer = StartPoint.currentMatch.getReturningPlayer();
                returningPlayer.addPoint();
                returningPlayer.addReturnPointCount();
                returningPlayer.addReturnPointPlayed();
                servingPlayer.addSecondServeCount();
                servingPlayer.addDoubleFaultCount();
                newPointFragList.newPointFromFragment();
                //TODO some kind of new point check
            }
        });

        //Return Winner Button On Click
        returnWinnerSSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.winnerFromFragment(1, 4);
            }
        });

        //Return Error Button On Click
        returnErrorSSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.errorFromFragment(1, 4);
            }
        });

        //Rally Button On Click
        rallyStartedSSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.rallyFromFragment(2);
            }
        });

        //Back Button
        secondServeBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPoint.fragManager.popBackStack();
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
