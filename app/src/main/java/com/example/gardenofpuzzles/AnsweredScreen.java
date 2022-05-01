//displays screen that we go to when we answer a question
package com.example.gardenofpuzzles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AnsweredScreen extends AppCompatActivity {
    Button backButton;
    TextView message;
    int ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //again take away that bar at the top
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.screen_answered);
        //get intent - ans will be 0 if player got the question incorrect, 1 if correct
        Intent intent = getIntent();
        ans = intent.getIntExtra("CORRECT", 0);
        //set color
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.bkrdColor));

        backButton=findViewById(R.id.doneButton);
        message=findViewById(R.id.msg);

        //set button and textview text based on whether the player got it correct or incorrect
        if (ans==1){
            backButton.setText("Yay!");
            message.setText("Congradulations! You got that one correct!");
        } else if (ans==0) {
            backButton.setText("Okay");
            message.setText("Uh Oh! Looks like you got that one incorrect.");
        } else {
            //tells the user if they have done all the puzzles of puzType - reuse of screen, there
                //was no need to make a new activity
            backButton.setText("Okay");
            message.setText("Looks like you've already completed all of these puzzles!");
        }

        //this button just takes us back to the main menu
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
