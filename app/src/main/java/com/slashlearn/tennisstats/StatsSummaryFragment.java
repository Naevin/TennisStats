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
        results[5] = playerIn.getTotalServePointsWon(); //serving point won
        results[6] = playerIn.getTotalReturnPointsWon();//receiving points won
        results[7] = playerIn.getTotalNetPointsWon(); //netPoints won
        results[8] = playerIn.getTotalPointsWon(); //total points won
        results[9] = playerIn.getTotalWinners();
        results[10] = playerIn.getTotalUnforcedErrors();
        return results;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Player servingPlayer = ViewStatsActivity.currentMatch.getServingPlayer();
        Player returningPlayer = ViewStatsActivity.currentMatch.getReturningPlayer();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats_summary, container, false);
        ListView statsSummaryListView = view.findViewById(R.id.statsSummaryListView);
        Resources res = view.getResources();
        summaryStatisticNames = res.getStringArray(R.array.summary_statistic_names);
        playerOneStats = getSummaryStats(servingPlayer);
        playerTwoStats = getSummaryStats(returningPlayer);

        StatsSummaryItemAdapter itemAdapter = new StatsSummaryItemAdapter(getActivity(), summaryStatisticNames, playerOneStats, playerTwoStats);
        statsSummaryListView.setAdapter(itemAdapter);

        return view;
    }

}
