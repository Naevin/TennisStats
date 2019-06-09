package com.slashlearn.tennisstats;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class ViewMatchesTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_matches_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getIntent().hasExtra("gameString")) {
            String gameString = (String) getIntent().getStringExtra("gameString");
            String matchString = gameString.substring(0, gameString.indexOf("["));
            String playerString = gameString.substring(gameString.indexOf("["));
            String playerOneString = playerString.substring(playerString.indexOf("[") + 1, playerString.indexOf("]"));
            String playerTwoString = playerString.substring(playerString.indexOf("[", playerString.indexOf("[") +1) + 1,
                    playerString.indexOf("]", playerString.indexOf("]") +1));


            TextView tempText = (TextView) findViewById(R.id.tempText);

            String result = matchString + "---------------" + playerOneString + "------------------" + playerTwoString;
            tempText.setText(result);
            System.out.println(result);
        }
    }
}
