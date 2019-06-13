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
public class StatsDetailFragment extends Fragment {

    String[] detailStatisticNames;
    String[] playerOneStats;
    String[] playerTwoStats;

    public StatsDetailFragment() {
        // Required empty public constructor
    }

    private String[] getDetailStats(Player playerIn) {
        String[] results = new String[28];
        int j = 0;
        results[j] = playerIn.getAceCountString();j++;//amount of aces
        results[j] = playerIn.getDoubleFaultCountString();j++;//amount of double faults
        results[j] = playerIn.getFirstServePercentage();j++;
        results[j] = playerIn.getSecondServePercentage();j++;
        results[j] = playerIn.getFirstServePointsWon();j++;
        results[j] = playerIn.getSecondServePointsWon();j++;
        results[j] = playerIn.getTotalServePointsWon();j++;
        results[j] = playerIn.getTotalReturnPointsWon();j++;
        results[j] = playerIn.getTotalNetPointsWon();j++;
        results[j] = playerIn.getTotalPointsWon();j++;
        results[j] = playerIn.getTotalWinners();j++;
        results[j] = playerIn.getForehandWinners();j++;
        results[j] = playerIn.getBackhandWinners();j++;
        results[j] = playerIn.getApproachWinners();j++;
        results[j] = playerIn.getVolleyWinnerCount();j++;
        results[j] = playerIn.getReturnWinnerCount();j++;
        results[j] = playerIn.getTotalUnforcedErrors();j++;
        results[j] = playerIn.getTotalForehandUnforced();j++;
        results[j] = playerIn.getTotalBackhandUnforced();j++;
        results[j] = playerIn.getApproachUnforced();j++;
        results[j] = playerIn.getTotalVolleyUnforced();j++;
        results[j] = playerIn.getTotalReturnUnforced();j++;
        results[j] = playerIn.getTotalForcedErrors();j++;
        results[j] = playerIn.getTotalForehandForced();j++;
        results[j] = playerIn.getTotalBackhandForced();j++;
        results[j] = playerIn.getApproachForced();j++;
        results[j] = playerIn.getTotalVolleyForced();j++;
        results[j] = playerIn.getTotalReturnForced();
        return results;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Player servingPlayer = ViewStatsActivity.currentMatch.getServingPlayer();
        Player returningPlayer = ViewStatsActivity.currentMatch.getReturningPlayer();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats_detail, container, false);
        ListView statsDetailListView = view.findViewById(R.id.statsDetailListView);
        Resources res = view.getResources();
        detailStatisticNames = res.getStringArray(R.array.detail_statistic_names);
        playerOneStats = getDetailStats(servingPlayer);
        playerTwoStats = getDetailStats(returningPlayer);

        StatsDetailItemAdapter itemAdapter = new StatsDetailItemAdapter(getActivity(), detailStatisticNames, playerOneStats, playerTwoStats);
        statsDetailListView.setAdapter(itemAdapter);
        return view;
    }

}
