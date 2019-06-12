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
public class ErrorFragment extends Fragment {

    private NewPointListener newPointFragList;

    public ErrorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_error, container, false);

        //Assigning Buttons
        Button errorBackBtn = view.findViewById(R.id.errorBackButton);
        Button nextBtn = view.findViewById(R.id.errorNextButton);
        final RadioGroup errorPositionGrp = (RadioGroup) view.findViewById(R.id.errorPosition);
        final RadioGroup wingErrorGrp = view.findViewById(R.id.wingError);
        final RadioGroup forcedSetGrp = view.findViewById(R.id.forcedSettingButton);
        final RadioGroup pointWonPosGrp = view.findViewById(R.id.pointWonEPosition);

        //Assigning TextView
        TextView errorMadeByE = (TextView) view.findViewById(R.id.errorMadeByE);
        TextView pointWonByE = (TextView) view.findViewById(R.id.pointWonByE);

        //Display the text for who made error and
        //who won point based upon arguments passed to fragment
        Bundle bundle = getArguments();
        final int errorKey = bundle.getInt("errorKey");
        final int serveHit = bundle.getInt("serveHit");

        if (serveHit > 2) {
            errorPositionGrp.check(R.id.onReturnE);
        }

        String servingPlayerFName = StartPoint.currentMatch.getServingPlayer().getFirstName();
        String returnPlayerFName = StartPoint.currentMatch.getReturningPlayer().getFirstName();
        final Player errorHitPlayer, pointWonPlayer;
        String wonPoint = " won the point";
        String error = " made the error";

        if (errorKey == 0) { //if the server made the error
            wonPoint = returnPlayerFName + wonPoint;
            error = servingPlayerFName + error;
            pointWonPlayer = StartPoint.currentMatch.getReturningPlayer();
            errorHitPlayer = StartPoint.currentMatch.getServingPlayer();
        } else { //else returner made the error
            wonPoint = servingPlayerFName + wonPoint;
            error = returnPlayerFName + error;
            pointWonPlayer = StartPoint.currentMatch.getServingPlayer();
            errorHitPlayer = StartPoint.currentMatch.getReturningPlayer();

        }
        errorMadeByE.setText(error);
        pointWonByE.setText(wonPoint);

        //Back Button
        errorBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPoint.fragManager.popBackStack();
            }
        });

        //Next Button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player servingPlayer = StartPoint.currentMatch.getServingPlayer();
                int errorPositionGrpID = errorPositionGrp.getCheckedRadioButtonId();
                int wingErrorGrpID = wingErrorGrp.getCheckedRadioButtonId();
                int forcedSetGrpID = forcedSetGrp.getCheckedRadioButtonId();
                int pointWonPosGrpID = pointWonPosGrp.getCheckedRadioButtonId();
                errorHitPlayer.giveError(errorPositionGrpID, wingErrorGrpID, forcedSetGrpID);
                pointWonPlayer.pointWonPosition(pointWonPosGrpID);

                if (serveHit % 2 == 1) {
                    servingPlayer.addFirstServeCount();
                } else {
                    servingPlayer.addSecondServeCount();
                }
                if (errorKey == 1) {
                    if (serveHit % 2 == 1) {
                        StartPoint.currentMatch.getServingPlayer().addFirstServePointCount();
                    } else {
                        StartPoint.currentMatch.getServingPlayer().addSecondServePointCount();
                    }
                } else {
                    StartPoint.currentMatch.getReturningPlayer().addReturnPointCount();
                }

                pointWonPlayer.addPoint();
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
