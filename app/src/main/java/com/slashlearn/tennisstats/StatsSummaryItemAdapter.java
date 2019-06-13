package com.slashlearn.tennisstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class StatsSummaryItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] summaryStatisticNames;
    String[] playerOneStats;
    String[] playerTwoStats;

    public StatsSummaryItemAdapter(Context c, String[] ssNamesIn, String[] playerOneStatsIn, String[] playerTwoStatsIn){
        summaryStatisticNames = ssNamesIn;
        playerOneStats = playerOneStatsIn;
        playerTwoStats = playerTwoStatsIn;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return summaryStatisticNames.length;
    }

    @Override
    public Object getItem(int i) {
        return summaryStatisticNames[i];
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

        statDescription.setText(summaryStatisticNames[i]);
        playerOneStat.setText(playerOneStats[i]);
        playerTwoStat.setText(playerTwoStats[i]);
        return v;
    }
}
