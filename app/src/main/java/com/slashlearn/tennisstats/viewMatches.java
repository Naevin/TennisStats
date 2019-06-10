package com.slashlearn.tennisstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class viewMatches extends AppCompatActivity {

    private ArrayList<String> getFiles(File pathNameIn) {
        ArrayList<String> fileNames = new ArrayList<>();
        File filePath = pathNameIn;
        File[] fileList = filePath.listFiles();

        for (File f : fileList) {
            fileNames.add(f.getName());
        }
        Collections.reverse(fileNames);
        return fileNames;
    }

    private String loadMatchString(String matchTitleIn) {
        FileInputStream fileIS = null;
        String returnVal = "";

        try {
            fileIS = openFileInput(matchTitleIn);
            InputStreamReader inputStreamReader = new InputStreamReader(fileIS);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = bufferedReader.readLine()) != null) {
                sb.append(text);
            }
            returnVal = sb.toString();

        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileIS != null) {
                try {
                    fileIS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnVal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matches);

        ListView matchListView = findViewById(R.id.matchListView);
        final ArrayList<String> fileNames = getFiles(getFilesDir());

        MatchItemAdapter itemAdapter = new MatchItemAdapter(this, fileNames);
        matchListView.setAdapter(itemAdapter);

        //On click for the list should load the clicked match
        matchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String matchLoadString = loadMatchString(fileNames.get(i));
                Match currentMatch = new Match(matchLoadString);
                //take to StartPoint screen
                Intent loadMatchIntent = new Intent(getApplicationContext(), StartPoint.class);
                loadMatchIntent.putExtra("currentMatch", currentMatch);
                startActivity(loadMatchIntent);
            }
        });

    }
}
