package com.slashlearn.tennisstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start New Match
        Button newMatchBtn = findViewById(R.id.newMatch);
        newMatchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newMatchIntent = new Intent(getApplicationContext(), NewMatchLoading.class);
                startActivity(newMatchIntent);
            }
        });
    }
}
