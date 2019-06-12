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
public class NewPointFragment extends Fragment {

    private NewPointListener newPointFragList;
    public NewPointFragment() {
        // Required empty public constructor
    }

    public interface NewPointFragmentListener{
        public void firstServeAceNP();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_point, container, false);

        //Assigning Buttons
        Button aceBtn = view.findViewById(R.id.aceButton);
        Button firstServeEBtn = view.findViewById(R.id.firstServeEButton);
        Button returnWinnerBtn = view.findViewById(R.id.returnWinnerButton);
        Button returnErrorBtn = view.findViewById(R.id.returnErrorButton);
        Button rallyStartedBtn = view.findViewById(R.id.rallyStartedButton);

        //Ace Button On Click
        aceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player servingPlayer = StartPoint.currentMatch.getServingPlayer();
                servingPlayer.addPoint();
                servingPlayer.addFirstServePointCount();
                servingPlayer.addAce();
                servingPlayer.addFirstServeCount();
                newPointFragList.newPointFromFragment();
                //TODO some kind of new point update
            }
        });

        //First Serve Error Button On Click
        firstServeEBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player servingPlayer = StartPoint.currentMatch.getServingPlayer();
                servingPlayer.addFirstServeErrorCount();
                servingPlayer.addFirstServeCount();
                SecondServeFragment secondServeFrag = new SecondServeFragment();
                StartPoint.fragManager.beginTransaction().replace(R.id.fragmentContainer, secondServeFrag,null).addToBackStack(null).commit();
            }
        });

        //Return Winner On Click
        returnWinnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.winnerFromFragment(1, 3);
            }
        });

        //Return Error On Click
        returnErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.errorFromFragment(1, 3);
            }
        });

        //Rally Button On Click
        rallyStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPointFragList.rallyFromFragment(1);
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
