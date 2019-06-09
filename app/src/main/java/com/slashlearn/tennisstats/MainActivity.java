package com.slashlearn.tennisstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start New Match
        Button newMatchBtn = findViewById(R.id.newMatch);
        Button loadMatchBtn = findViewById(R.id.existMatch);
        Button matchBtn = findViewById(R.id.matches);

        //new match on click
        newMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newMatchIntent = new Intent(getApplicationContext(), NewMatchLoading.class);
                startActivity(newMatchIntent);
            }
        });

        loadMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fileIS = null;

                try {
                    fileIS = openFileInput("REPLACE WITH TITLE");
                    InputStreamReader inputStreamReader = new InputStreamReader(fileIS);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while ((text = bufferedReader.readLine()) != null) {
                        sb.append(text).append("\n");
                    }
                    Toast.makeText(MainActivity.this, sb.toString() , Toast.LENGTH_SHORT).show();

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
            }
        });

        matchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load matches
            }
        });
    }
}
