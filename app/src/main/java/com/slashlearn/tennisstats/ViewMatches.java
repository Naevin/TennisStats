package com.slashlearn.tennisstats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ViewMatches extends AppCompatActivity {


    private ArrayList<String> getFiles(File pathNameIn) {
        ArrayList<String> fileNames = new ArrayList<>();
        File[] fileList = pathNameIn.listFiles();

        for (File f : fileList) {
            if (f.getName().contains("-~~-")) {
                fileNames.add(f.getName());
            }
        }
        Collections.sort(fileNames);
        Collections.reverse(fileNames);
        return fileNames;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matches);

        RecyclerView matchRecyclerView = findViewById(R.id.viewMatchesRecyclerView);
        final ArrayList<String> fileNames = getFiles(getFilesDir());

        MatchItemRAdapter itemAdapter = new MatchItemRAdapter(this, fileNames);
        matchRecyclerView.setAdapter(itemAdapter);
        matchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
