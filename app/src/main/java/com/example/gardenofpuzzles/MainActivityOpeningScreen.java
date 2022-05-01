package com.example.gardenofpuzzles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityOpeningScreen extends AppCompatActivity {

    Button briefDescription;
    Button playButt;
    Button instructionButt;
    Button quitButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_opening_screen);

        briefDescription = (Button) findViewById(R.id.descriptionBtn);
        playButt = (Button) findViewById(R.id.playBtn);
        instructionButt = (Button) findViewById(R.id.instructionBtn);
        quitButt = (Button) findViewById(R.id.quitBtn);

        briefDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityOpeningScreen.this, BriefDescription.class);
                startActivity(intent);
            }
        });

        playButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityOpeningScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        instructionButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityOpeningScreen.this, GameInstruction.class);
                startActivity(intent);
            }
        });

        quitButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}