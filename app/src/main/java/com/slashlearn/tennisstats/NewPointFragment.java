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
public class NewPointFragment extends Fragment {


    public NewPointFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_point, container, false);

        //Assigning Buttons
        Button firstServeEBtn = view.findViewById(R.id.firstServeEButton);
        Button returnWinnerBtn = view.findViewById(R.id.returnWinnerButton);
        Button returnErrorBtn = view.findViewById(R.id.returnErrorButton);
        Button rallyStartedBtn = view.findViewById(R.id.rallyStartedButton);

        //First Serve Error Button On Click
        firstServeEBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondServeFragment secondServeFrag = new SecondServeFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, secondServeFrag,null).addToBackStack(null).commit();
            }
        });

        //Return Winner On Click
        returnWinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WinnerFragment winnerFrag = new WinnerFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, winnerFrag,null).addToBackStack(null).commit();
            }
        });

        //Return Error On Click
        returnErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ErrorFragment errorFrag = new ErrorFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, errorFrag,null).addToBackStack(null).commit();
            }
        });

        //Rally Button On Click
        rallyStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RallyStartedFragment rallyStartedFrag = new RallyStartedFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, rallyStartedFrag, null ).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
