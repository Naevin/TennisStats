package com.slashlearn.tennisstats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditMatchActivity extends AppCompatActivity {

    private Match currentMatchSettings;


    private void updateDisplay() {

        currentMatchSettings.checkGame();

        TextView player1PointLabel, player1Point;
        TextView player2PointLabel, player2Point;
        TextView player1GameLabel, player1Game;
        TextView player2GameLabel, player2Game;
        TextView isServingTitle;
        TextView setSettingDisplay, adSettingDisplay;

        player1Point = findViewById(R.id.edit_player1Point);
        player2Point = findViewById(R.id.edit_player2Point);
        player1PointLabel = findViewById(R.id.edit_player1PointLabel);
        player2PointLabel = findViewById(R.id.edit_player2PointLabel);
        player1GameLabel = findViewById(R.id.edit_player1GameLabel);
        player2GameLabel = findViewById(R.id.edit_player2GameLabel);
        player1Game = findViewById(R.id.edit_player1Game);
        player2Game = findViewById(R.id.edit_player2Game);
        isServingTitle = findViewById(R.id.edit_isServingTitle);
        setSettingDisplay = findViewById(R.id.edit_setSettingDisplay);
        adSettingDisplay = findViewById(R.id.edit_adSettingDisplay);

        Player player1 = currentMatchSettings.getPlayerOne();
        Player player2 = currentMatchSettings.getPlayerTwo();
        Player servingPlayer = currentMatchSettings.getServingPlayer();
        Player returningPlayer = currentMatchSettings.getReturningPlayer();
        String[] score = currentMatchSettings.matchScore();
        if (servingPlayer == player1) {
            player1Point.setText(score[0]);
            player2Point.setText(score[1]);
            player1PointLabel.setText(player1.getFirstName() + "'s Points");
            player2PointLabel.setText(player2.getFirstName() + "'s Points");
            isServingTitle.setText(player1.getFirstName() + " is Serving");
        } else {
            player1Point.setText(score[1]);
            player2Point.setText(score[0]);
            player1PointLabel.setText(player1.getFirstName() + "'s Points");
            player2PointLabel.setText(player2.getFirstName() + "'s Points");
            isServingTitle.setText(player2.getFirstName() + " is Serving");
        }
        player1GameLabel.setText(player1.getFirstName() + "'s Games");
        player2GameLabel.setText(player2.getFirstName() + "'s Games");
        int currentSet = currentMatchSettings.getCurrentSet();
        player1Game.setText(Integer.toString(player1.getCurrentGame(currentSet)));
        player2Game.setText(Integer.toString(player2.getCurrentGame(currentSet)));

        setSettingDisplay.setText(currentMatchSettings.getSetSetting());
        adSettingDisplay.setText(currentMatchSettings.getAdSetting());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_match);
        if (getIntent().hasExtra("currentMatch")) {
            currentMatchSettings = (Match) getIntent().getSerializableExtra("currentMatch");
        }

        updateDisplay();

        //assign all dynamic buttons
        ImageButton player1PointPlusBtn = findViewById(R.id.edit_player1PointPlusButton);
        ImageButton player1PointMinusBtn = findViewById(R.id.edit_player1PointMinusButton);
        ImageButton player2PointPlusBtn = findViewById(R.id.edit_player2PointPlusButton);
        ImageButton player2PointMinusBtn = findViewById(R.id.edit_player2PointMinusButton);
        ImageButton player1GamePlusBtn = findViewById(R.id.edit_player1GamePlusButton);
        ImageButton player1GameMinusBtn = findViewById(R.id.edit_player1GameMinusButton);
        ImageButton player2GamePlusBtn = findViewById(R.id.edit_player2GamePlusButton);
        ImageButton player2GameMinusBtn = findViewById(R.id.edit_player2GameMinusButton);
        Button switchServerBtn = findViewById(R.id.edit_switchServerButton);
        Button changeAdSettingBtn = findViewById(R.id.edit_changeAdButton);
        Button changeSetSettingBtn = findViewById(R.id.edit_changeSetButton);

        //assign all dynamic textviews

        final Player player1 = currentMatchSettings.getPlayerOne();
        final Player player2 = currentMatchSettings.getPlayerTwo();

        player1PointPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1.addPoint();
                updateDisplay();
            }
        });

        player2PointPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2.addPoint();
                updateDisplay();
            }
        });

        player1PointMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1.subtractPoint();
                updateDisplay();
            }
        });

        player2PointMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2.subtractPoint();
                updateDisplay();
            }
        });

        player1GamePlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1.addGame(currentMatchSettings.getCurrentSet());
                updateDisplay();;
            }
        });

        player2GamePlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2.addGame(currentMatchSettings.getCurrentSet());
                updateDisplay();
            }
        });

        player1GameMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player1.subtractGame(currentMatchSettings.getCurrentSet());
                updateDisplay();
            }
        });

        player2GameMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player2.subtractGame(currentMatchSettings.getCurrentSet());
                updateDisplay();
            }
        });

        switchServerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMatchSettings.switchServingPlayer();
                updateDisplay();
            }
        });

        changeSetSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMatchSettings.changeSetSetting();
                updateDisplay();
            }
        });

        changeAdSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMatchSettings.changeAdSetting();
                updateDisplay();
            }
        });

    }
}
