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
public class ErrorFragment extends Fragment {


    public ErrorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_error, container, false);

        //Assigning TextView
        TextView errorMadeByE = (TextView) view.findViewById(R.id.errorMadeByE);
        TextView pointWonByE = (TextView) view.findViewById(R.id.pointWonByE);

        //Display the text for who made error and
        //who won point based upon arguments passed to fragment
        Bundle bundle = getArguments();
        int errorKey = bundle.getInt("errorKey");
        String servingPlayerFName = StartPoint.currentMatch.getServingPlayer().getFirstName();
        String returnPlayerFName = StartPoint.currentMatch.getReturningPlayer().getFirstName();
        String wonPoint = " won the point";
        String error = " made the error";
        if (errorKey == 0) { //if the server made the error
            wonPoint = returnPlayerFName + wonPoint;
            error = servingPlayerFName + error;
        } else { //else returner made the error
            wonPoint = servingPlayerFName + wonPoint;
            error = returnPlayerFName + error;
        }
        errorMadeByE.setText(error);
        pointWonByE.setText(wonPoint);

        //Assigning Buttons
        Button errorBackBtn = view.findViewById(R.id.errorBackButton);

        //Back Button
        errorBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPoint.fragManager.popBackStack();
            }
        });


        return view;
    }

}
