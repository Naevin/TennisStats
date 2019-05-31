package com.slashlearn.tennisstats;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WinnerFragment extends Fragment {


    public WinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_winner, container, false);

        //Assigning TextView
        TextView winnerMadeByW = (TextView) view.findViewById(R.id.winnerMadeByW);
        TextView pointLostByW = (TextView) view.findViewById(R.id.pointLostByW);

        Bundle bundle = getArguments();
        int winnerKey = bundle.getInt("winnerKey");

        String servingPlayerFName = StartPoint.currentMatch.getServingPlayer().getFirstName();
        String returnPlayerFName = StartPoint.currentMatch.getReturningPlayer().getFirstName();
        String hitWinner = " hit a winner";
        String lostPoint = " lost the point";
        if (winnerKey == 0) { //if the server made the error
            hitWinner = servingPlayerFName + hitWinner;
            lostPoint = returnPlayerFName + lostPoint;
        } else { //else returner made the error
            hitWinner = returnPlayerFName + hitWinner;
            lostPoint = servingPlayerFName + lostPoint;
        }
        pointLostByW.setText(lostPoint);
        winnerMadeByW.setText(hitWinner);


        //Assigning Buttons
        Button winnerBackBtn = view.findViewById(R.id.winnerBackButton);

        //Back Button
        winnerBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPoint.fragManager.popBackStack();
            }
        });
        return view;
    }

}
