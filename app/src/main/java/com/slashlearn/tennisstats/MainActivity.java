package com.slashlearn.tennisstats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolBar;

    //Firebase
    private FirebaseAuth mAuth;

    //TODO sort out all the current match indexes
    //public static String currentMatchTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainToolBar = (Toolbar)  findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        //Start New Match
        Button newMatchBtn = findViewById(R.id.newMatch);
        //Button loadMatchBtn = findViewById(R.id.existMatch);
        Button matchBtn = findViewById(R.id.matches);
        Button syncOnlineBtn = findViewById(R.id.syncOnlineButton);

        //new match on click
        newMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newMatchIntent = new Intent(getApplicationContext(), NewMatchLoading.class);
                startActivity(newMatchIntent);
            }
        });

        matchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewMatchIntent = new Intent(getApplicationContext(), ViewMatches.class);
                startActivity(viewMatchIntent);
            }
        });

        syncOnlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    Intent mainIntent = new Intent(MainActivity.this, SyncAccountActivity.class);
                    startActivity(mainIntent);
                    //finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logoutButton) {
            mAuth.signOut();
        }
        return super.onOptionsItemSelected(item);
    }
}
