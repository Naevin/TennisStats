package com.slashlearn.tennisstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StatsDetailItemAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    String[] detailStatisticNames;
    String[] playerOneStats;
    String[] playerTwoStats;

    public StatsDetailItemAdapter(Context c, String[] dNamesIn, String[] playerOneStatsIn, String[] playerTwoStatsIn){
        detailStatisticNames = dNamesIn;
        playerOneStats = playerOneStatsIn;
        playerTwoStats = playerTwoStatsIn;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return detailStatisticNames.length;
    }

    @Override
    public Object getItem(int i) {
        return detailStatisticNames[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.stats_listview_detail, null);
        //alternate background color
        if (i % 2 == 1) {
            v.setBackgroundResource(R.color.lightGreyBackground);
        }
        TextView statDescription = v.findViewById(R.id.statDescription);
        TextView playerOneStat = v.findViewById(R.id.playerOneStat);
        TextView playerTwoStat = v.findViewById(R.id.playerTwoStat);

        statDescription.setText(detailStatisticNames[i]);
        playerOneStat.setText(playerOneStats[i]);
        playerTwoStat.setText(playerTwoStats[i]);
        return v;
    }
}
