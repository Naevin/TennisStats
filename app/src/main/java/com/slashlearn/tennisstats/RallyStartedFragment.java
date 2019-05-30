package com.slashlearn.tennisstats;


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

        //Server Error Button On Click
        serverErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ErrorFragment errorFrag = new ErrorFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, errorFrag,null).addToBackStack(null).commit();
            }
        });

        //Returner Error Button On Click
        returnErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ErrorFragment errorFrag = new ErrorFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, errorFrag,null).addToBackStack(null).commit();
            }
        });

        //Server Winner Button On Click
        serverWinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WinnerFragment winnerFrag = new WinnerFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, winnerFrag,null).addToBackStack(null).commit();
            }
        });

        //Server Winner Button On Click
        returnWinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WinnerFragment winnerFrag = new WinnerFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, winnerFrag,null).addToBackStack(null).commit();
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

}
