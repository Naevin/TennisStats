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
public class RallyStartedFragment extends Fragment {

    private NewPointListener newPointFragList;

    public RallyStartedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rally_started, container, false);

        //Assigning Buttons
        Button serverErrorBtn = view.findViewById(R.id.serverErrorButton);
        Button returnErrorBtn = view.findViewById(R.id.returnErrorButton);
        Button serverWinnerBtn = view.findViewById(R.id.serverWinnerButton);
        Button returnWinnerBtn = view.findViewById(R.id.returnWinnerButton);
        Button rallyStartedBackBtn = view.findViewById(R.id.rallyStartedBackButton);

        Bundle bundle = getArguments();
        final int serveHit = bundle.getInt("serveHit");

        //Server Error Button On Click
        serverErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.errorFromFragment(0, serveHit);
            }
        });

        //Returner Error Button On Click
        returnErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.errorFromFragment(1, serveHit);
            }
        });

        //Server Winner Button On Click
        serverWinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.winnerFromFragment(0, serveHit);
            }
        });

        //Server Winner Button On Click
        returnWinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.winnerFromFragment(1, serveHit);
            }
        });

        //Back Button
        rallyStartedBackBtn.setOnClickListener(new View.OnClickListener() {
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
