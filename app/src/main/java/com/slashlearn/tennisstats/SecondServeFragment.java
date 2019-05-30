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
public class SecondServeFragment extends Fragment {


    public SecondServeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second_serve, container, false);

        //Assigning Buttons
        Button doubleFaultBtn = view.findViewById(R.id.doubleFaultButton);
        Button returnWinnerSSBtn = view.findViewById(R.id.returnWinnerSSButton);
        Button returnErrorSSBtn = view.findViewById(R.id.returnErrorSSButton);
        Button rallyStartedSSBtn = view.findViewById(R.id.rallyStartedSSButton);
        Button secondServeBackBtn = view.findViewById(R.id.secondServeBackButton);

        //Double Fault Button On Click
        doubleFaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO update for double fault
            }
        });

        //Return Winner Button On Click
        returnWinnerSSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WinnerFragment winnerFrag = new WinnerFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, winnerFrag,null).addToBackStack(null).commit();
            }
        });

        //Return Error Button On Click
        returnErrorSSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ErrorFragment errorFrag = new ErrorFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, errorFrag,null).addToBackStack(null).commit();
            }
        });

        //Rally Button On Click
        rallyStartedSSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RallyStartedFragment rallyStartedFrag = new RallyStartedFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, rallyStartedFrag, null ).addToBackStack(null).commit();
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

}
