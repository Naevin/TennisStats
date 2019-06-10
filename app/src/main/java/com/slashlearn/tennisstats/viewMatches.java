package com.slashlearn.tennisstats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class viewMatches extends AppCompatActivity {

    private ArrayList<String> getFiles(File pathNameIn) {
        ArrayList<String> fileNames = new ArrayList<>();
        File filePath = pathNameIn;
        File[] fileList = filePath.listFiles();

        for (File f : fileList) {
            fileNames.add(f.getName());
        }
        return fileNames;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matches);

        ListView matchListView = findViewById(R.id.matchListView);
        ArrayList<String> fileNames = getFiles(getFilesDir());

        MatchItemAdapter itemAdapter = new MatchItemAdapter(this, fileNames);
        matchListView.setAdapter(itemAdapter);

    }
}
