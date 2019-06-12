package com.slashlearn.tennisstats;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class StatsDetailItemAdapter extends BaseAdapter {

    String[] detailStatisticNames;
    String[] playerOneStats;
    String[] playerTwoStats;

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
        return null;
    }
}
