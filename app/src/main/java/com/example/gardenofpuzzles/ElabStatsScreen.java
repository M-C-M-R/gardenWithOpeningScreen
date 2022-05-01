//Elaborated stats screen: shows breakdown of puzzles either attempted, completed, or unattempted
package com.example.gardenofpuzzles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ElabStatsScreen extends AppCompatActivity {
    Button backButton;
    TextView title, questionTypes, chessNum, cupNum, riverNum, syllogismNum, truthNum;
    int typecode;
    int tempNum;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //setting up activity
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.screen_elab_stats);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.bkrdColor));
        Intent intent = getIntent();
        //typecode determines if we are displaying completed (0), attempted (1), or unattempted (2)
        typecode = intent.getIntExtra("TYPECODE",0);
        //setting up buttons and textviews
        backButton = findViewById(R.id.elabStatsBackButton);
        title = findViewById(R.id.elabStatsTitle);
        questionTypes=findViewById(R.id.elabTextDisplay);
        chessNum=findViewById(R.id.cNumDisplay);
        cupNum=findViewById(R.id.fNumDisplay);
        riverNum=findViewById(R.id.rNumDisplay);
        syllogismNum=findViewById(R.id.sNumDisplay);
        truthNum=findViewById(R.id.tNumDisplay);
        //based on typecode, display the relevant info
        switch (typecode){
            case 0:
                title.setText("Completed");
                tempNum=MainActivity.getCompleted(1);
                chessNum.setText(tempNum + "/5");
                tempNum=MainActivity.getCompleted(2);
                cupNum.setText(tempNum + "/5");
                tempNum=MainActivity.getCompleted(3);
                riverNum.setText(tempNum + "/1");
                tempNum=MainActivity.getCompleted(4);
                syllogismNum.setText(tempNum + "/5");
                tempNum=MainActivity.getCompleted(5);
                truthNum.setText(tempNum + "/5");
                break;
            case 1:
                title.setText("Attempted");
                tempNum=MainActivity.getAttempted(1);
                chessNum.setText(tempNum + "/5");
                tempNum=MainActivity.getAttempted(2);
                cupNum.setText(tempNum + "/5");
                tempNum=MainActivity.getAttempted(3);
                riverNum.setText(tempNum + "/1");
                tempNum=MainActivity.getAttempted(4);
                syllogismNum.setText(tempNum + "/5");
                tempNum=MainActivity.getAttempted(5);
                truthNum.setText(tempNum + "/5");
                break;
            case 2:
                title.setText("Unattempted");
                tempNum=5-MainActivity.getAttempted(1);
                chessNum.setText(tempNum + "/5");
                tempNum=5-MainActivity.getAttempted(2);
                cupNum.setText(tempNum + "/5");
                tempNum=1-MainActivity.getAttempted(3);
                riverNum.setText(tempNum + "/1");
                tempNum=5-MainActivity.getAttempted(4);
                syllogismNum.setText(tempNum + "/5");
                tempNum=5-MainActivity.getAttempted(5);
                truthNum.setText(tempNum + "/5");
                break;
        }
        //takes us back to the stats screen
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),StatsScreen.class);
                startActivity(intent);
            }
        });
    }
}
