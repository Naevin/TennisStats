package com.slashlearn.tennisstats;


import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsSummaryFragment extends Fragment {

    String[] summaryStatisticNames;
    String[] playerOneStats;
    String[] playerTwoStats;

    public StatsSummaryFragment() {
        // Required empty public constructor
    }

    private String[] getSummaryStats(Player playerIn) {
        String[] results = new String[11];
        results[0] = playerIn.getAceCountString();//amount of aces
        results[1] = playerIn.getDoubleFaultCountString();//amount of double faults
        results[2] = playerIn.getFirstServePercentage();
        results[3] = playerIn.getFirstServePointsWon(); //won point | made first serve
        results[4] = playerIn.getSecondServePointsWon(); //won point | made second serve
        results[5] = "todo";
        results[6] = "todo";
        results[7] = "todo";
        results[8] = "todo";
        results[9] = "todo";
        results[10] = "todo";
        return results;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Player playerOne = ViewStatsActivity.currentMatch.getPlayerOne();
        Player playerTwo = ViewStatsActivity.currentMatch.getPlayerTwo();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats_summary, container, false);
        ListView statsSummaryListView = view.findViewById(R.id.statsSummaryListView);
        Resources res = view.getResources();
        summaryStatisticNames = res.getStringArray(R.array.summary_statistic_names);
        playerOneStats = getSummaryStats(playerOne);
        playerTwoStats = getSummaryStats(playerTwo);

        StatsSummaryItemAdapter itemAdapter = new StatsSummaryItemAdapter(getActivity(), summaryStatisticNames, playerOneStats, playerTwoStats);
        statsSummaryListView.setAdapter(itemAdapter);

        return view;
    }

}
