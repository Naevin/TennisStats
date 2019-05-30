package com.slashlearn.tennisstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.EditText;

public class NewMatchLoading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match_loading);

        //Next Button creates a new "Match Object"
        Button nextBtn = findViewById(R.id.newMatchNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText playerOneNameE = (EditText) findViewById(R.id.playerOneName);
                EditText playerTwoNameE = (EditText) findViewById(R.id.playerTwoName);
                String playerOneName = playerOneNameE.getText().toString();
                String playerTwoName = playerTwoNameE.getText().toString();
                Player playerOne = new Player(playerOneName);
                Player playerTwo = new Player(playerTwoName);

                //if ad or no ad
                RadioGroup adSettingGroup = (RadioGroup) findViewById(R.id.adRGroup);
                int adSettingId = adSettingGroup.getCheckedRadioButtonId();
                boolean adSetting = true;
                if (R.id.noAd == adSettingId) {
                    adSetting = false;
                }

                //if serving is player1 or player2
                RadioGroup servingGroup = (RadioGroup) findViewById(R.id.servingRGroup);
                int servingPlayer = servingGroup.getCheckedRadioButtonId();
                if (R.id.pOneServe == servingPlayer) {
                    servingPlayer = 1;
                } else {
                    servingPlayer = 2;
                }

                //Set Format
                RadioGroup setGroup = (RadioGroup) findViewById(R.id.setRGroup);
                int setSetting = setGroup.getCheckedRadioButtonId();
                if (R.id.twoOutThree == setSetting) {
                    setSetting = 0;
                } else if (R.id.threeOutFive == setSetting) {
                    setSetting = 1;
                } else {
                    setSetting = 2;
                }

                Match currentMatch = new Match(playerOne, playerTwo, adSetting, servingPlayer, setSetting);
                //take to StartPoint screen
                Intent firstPointIntent = new Intent(getApplicationContext(), StartPoint.class);
                firstPointIntent.putExtra("currentMatch", currentMatch);
                startActivity(firstPointIntent);
            }
        });
    }
}
