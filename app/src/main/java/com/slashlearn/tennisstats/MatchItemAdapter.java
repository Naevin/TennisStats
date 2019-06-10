package com.slashlearn.tennisstats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MatchItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<String> fileNames;

    public MatchItemAdapter(Context c, ArrayList<String> fileNamesIn) {
        fileNames = fileNamesIn;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return fileNames.size();
    }

    @Override
    public Object getItem(int i) {
        return fileNames.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.match_listview_detail, null);
        TextView matchTitleTV = (TextView) v.findViewById(R.id.matchTitleTextView);
        TextView descriptionTV = (TextView) v.findViewById(R.id.matchDescripTextView);
        TextView matchDateTV = (TextView) v.findViewById(R.id.matchDateTextView);

        String fileName = fileNames.get(i);
        String[] tokens = fileName.split("-,-");

        descriptionTV.setText(tokens[0]);
        matchDateTV.setText(tokens[1]);
        matchTitleTV.setText(tokens[2]);

        return v;
    }
}
