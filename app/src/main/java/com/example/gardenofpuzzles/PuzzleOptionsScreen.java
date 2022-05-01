//Accessed from the main activity - allows user to play a puzzle, view instructions, or go back
package com.example.gardenofpuzzles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PuzzleOptionsScreen extends AppCompatActivity {
    Button playBut,instBut,backBut;
    TextView title,desc;
    int curPuzType;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        //setting up activity
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.options_screen_puzzle);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.bkrdColor));
        Intent intent = getIntent();
        curPuzType = intent.getIntExtra("PUZTYPE",0);

        //buttons and textviews
        playBut = findViewById(R.id.playButton);
        instBut = findViewById(R.id.instructionsButton);
        backBut = findViewById(R.id.optMenuBackButton);
        title = findViewById(R.id.puzType);
        desc = findViewById(R.id.puzDesc);

        //based on the puzzle code passed from MainActivity, we set the title and description
        switch (curPuzType){
            case 1:     //Chess
                title.setText("Chess Question");
                desc.setText("Look at the chessboard image and answer the question");
                break;
            case 2:     //Fill Cup
                title.setText("Fill Cup Problem");
                desc.setText("Get a certain amount of water into a cup");
                break;
            case 3:     //River
                title.setText("River Crossing Problem");
                desc.setText("Get all characters safely to the other side of the river");
                break;
            case 4:     //Syllogism
                title.setText("Syllogism Question");
                desc.setText("Determine what statement(s) are true based on previous given statements");
                break;
            case 5:     //Truth
                title.setText("Truth Tellers and Liars Puzzle");
                desc.setText("Determine the truth based on statements given by characters");
                break;
            default:
                break;
        }

        //go back to main menu
        backBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //do a puzzle
        playBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(curPuzType != 2) {
                    Intent intent = new Intent(getApplicationContext(), MultipleChoicePuzzle.class);
                    if (MainActivity.getAttempted(curPuzType) == 5) {
                        MainActivity.resetAttempted(curPuzType);
                    }
                    MultipleChoiceQuestion question = MainActivity.getQuestion(curPuzType);
                    intent.putExtra("QUESTION", question);
                    startActivity(intent);
                }
                if(curPuzType == 2){
                    Intent intent = new Intent(getApplicationContext(), fillCupPuzzleOne.class);
                    int question = MainActivity.getFillCupQuestion();
                    intent.putExtra("FILLCUPINT", question);
                    startActivity(intent);
                }
            }
        });

        //view more instructions for this puzzle type
        instBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),PuzzleInstructionsScreen.class);
                intent.putExtra("PUZTYPE", curPuzType);
                startActivity(intent);
            }
        });

    }
}
