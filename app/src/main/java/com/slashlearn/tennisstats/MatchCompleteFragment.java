package com.slashlearn.tennisstats;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchCompleteFragment extends Fragment {


    public MatchCompleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_complete, container, false);

        Button viewStatsBtn = view.findViewById(R.id.MCviewStatsButton);
        Button returnToMMBtn = view.findViewById(R.id.returntoMMButton);

        viewStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewMatchStatsIntent = new Intent(getActivity(), ViewStatsActivity.class);
                viewMatchStatsIntent.putExtra("currentMatch", StartPoint.currentMatch);
                startActivity(viewMatchStatsIntent);
            }
        });

        returnToMMBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }

}
