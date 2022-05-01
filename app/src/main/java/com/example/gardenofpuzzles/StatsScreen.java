//displays stats
package com.example.gardenofpuzzles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StatsScreen extends AppCompatActivity {
    Button backButton, compButton, attButton, unattButton, resetButton;
    TextView title, clickMess;
    int tempNum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //setting up activity
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.screen_stats);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.bkrdColor));
        //buttons and textviews - display numbers of attempted, completed, and unattempted
        backButton = findViewById(R.id.statsBackButton);
        compButton = findViewById(R.id.completedQuestions);
        tempNum = MainActivity.getCompleted();
        compButton.setText("Completed:\n"+tempNum + "/21");
        attButton = findViewById(R.id.attemptedQuestions);
        tempNum = MainActivity.getAttempted();
        attButton.setText("Attempted:\n"+tempNum + "/21");
        unattButton = findViewById(R.id.unattemptedQuestions);
        tempNum = 21-tempNum;
        unattButton.setText("Unattempted:\n"+tempNum + "/21");
        resetButton = findViewById(R.id.resetButton);
        title = findViewById(R.id.statsTitle);
        clickMess = findViewById(R.id.clickMessage);
        //goes back to main activity
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        //these buttons lead to more detailed statistics (a breakdown by puzzle type)
        compButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),ElabStatsScreen.class);
                intent.putExtra("TYPECODE",0);
                startActivity(intent);
            }
        });

        attButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),ElabStatsScreen.class);
                intent.putExtra("TYPECODE",1);
                startActivity(intent);
            }
        });

        unattButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),ElabStatsScreen.class);
                intent.putExtra("TYPECODE",2);
                startActivity(intent);
            }
        });
        //resets stats
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MainActivity.clearData();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
