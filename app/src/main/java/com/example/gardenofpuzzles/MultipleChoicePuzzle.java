//DISPLAYS a multiple choice question

package com.example.gardenofpuzzles;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MultipleChoicePuzzle extends AppCompatActivity {

    Button backButton, answerButton1, answerButton2, answerButton3, answerButton4;
    TextView puzzleDescription;
    ImageView picture;
    MultipleChoiceQuestion quest;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //this just makes the app fullscreen, gets rid of the top menu bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.puzzle_multiple_choice);
        //get the question object from the intent
        Intent intent = getIntent();
        quest = intent.getParcelableExtra("QUESTION");
        if (quest.getPuzzleCode()==-1){
            Intent intent1 = new Intent(getApplicationContext(),AnsweredScreen.class);
            intent1.putExtra("CORRECT",2);
            startActivity(intent1);
        }
        //sets the background color
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.bkrdColor));
        //set the button and textview text to whatever the question has
        backButton = findViewById(R.id.backButton);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);
        answerButton4 = findViewById(R.id.answerButton4);
        puzzleDescription = findViewById(R.id.puzDesc);
        puzzleDescription.setText(quest.getQuestion());
        picture = findViewById(R.id.chessPic);
        //if a chess puzzle, we have to display the image
        if (quest.getPuzzleType()==1){
            switch (quest.getPuzzleCode()){
                case 0:
                    picture.setImageDrawable(getDrawable(R.drawable.chess1));
                    break;
                case 1:
                    picture.setImageDrawable(getDrawable(R.drawable.chess2));
                    break;
                case 2:
                    picture.setImageDrawable(getDrawable(R.drawable.chess3));
                    break;
                case 3:
                    picture.setImageDrawable(getDrawable(R.drawable.chess4));
                    break;
                case 4:
                    picture.setImageDrawable(getDrawable(R.drawable.chess5));
                    break;
                default:
                    break;
            }
            picture.setVisibility(View.VISIBLE);
        } else {  //if not chess, no image
            picture.setVisibility(View.GONE);
        }

        //set button text. This may be the least efficient way to do this but it works
        int randomNum=rand.nextInt(4);
        switch (randomNum){
            case 0:
                answerButton1.setText(quest.getCorrect());
                randomNum=rand.nextInt(3);
                switch(randomNum){
                    case 0:
                        answerButton2.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton3.setText(quest.getIncorrect2());
                            answerButton4.setText(quest.getIncorrect3());
                        } else {
                            answerButton4.setText(quest.getIncorrect2());
                            answerButton3.setText(quest.getIncorrect3());
                        }
                        break;
                    case 1:
                        answerButton3.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton2.setText(quest.getIncorrect2());
                            answerButton4.setText(quest.getIncorrect3());
                        } else {
                            answerButton4.setText(quest.getIncorrect2());
                            answerButton2.setText(quest.getIncorrect3());
                        }
                        break;
                    case 2:
                        answerButton4.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton2.setText(quest.getIncorrect2());
                            answerButton3.setText(quest.getIncorrect3());
                        } else {
                            answerButton3.setText(quest.getIncorrect2());
                            answerButton2.setText(quest.getIncorrect3());
                        }
                        break;
                }
                break;
            case 1:
                answerButton2.setText(quest.getCorrect());
                randomNum=rand.nextInt(3);
                switch(randomNum){
                    case 0:
                        answerButton1.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton3.setText(quest.getIncorrect2());
                            answerButton4.setText(quest.getIncorrect3());
                        } else {
                            answerButton4.setText(quest.getIncorrect2());
                            answerButton3.setText(quest.getIncorrect3());
                        }
                        break;
                    case 1:
                        answerButton3.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton1.setText(quest.getIncorrect2());
                            answerButton4.setText(quest.getIncorrect3());
                        } else {
                            answerButton4.setText(quest.getIncorrect2());
                            answerButton1.setText(quest.getIncorrect3());
                        }
                        break;
                    case 2:
                        answerButton4.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton1.setText(quest.getIncorrect2());
                            answerButton3.setText(quest.getIncorrect3());
                        } else {
                            answerButton3.setText(quest.getIncorrect2());
                            answerButton1.setText(quest.getIncorrect3());
                        }
                        break;
                }
                break;
            case 2:
                answerButton3.setText(quest.getCorrect());
                randomNum=rand.nextInt(3);
                switch(randomNum){
                    case 0:
                        answerButton1.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton2.setText(quest.getIncorrect2());
                            answerButton4.setText(quest.getIncorrect3());
                        } else {
                            answerButton4.setText(quest.getIncorrect2());
                            answerButton2.setText(quest.getIncorrect3());
                        }
                        break;
                    case 1:
                        answerButton2.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton1.setText(quest.getIncorrect2());
                            answerButton4.setText(quest.getIncorrect3());
                        } else {
                            answerButton4.setText(quest.getIncorrect2());
                            answerButton1.setText(quest.getIncorrect3());
                        }
                        break;
                    case 2:
                        answerButton4.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton1.setText(quest.getIncorrect2());
                            answerButton2.setText(quest.getIncorrect3());
                        } else {
                            answerButton2.setText(quest.getIncorrect2());
                            answerButton1.setText(quest.getIncorrect3());
                        }
                        break;
                }
                break;
            case 3:
                answerButton4.setText(quest.getCorrect());
                randomNum=rand.nextInt(3);
                switch(randomNum){
                    case 0:
                        answerButton1.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton2.setText(quest.getIncorrect2());
                            answerButton3.setText(quest.getIncorrect3());
                        } else {
                            answerButton3.setText(quest.getIncorrect2());
                            answerButton2.setText(quest.getIncorrect3());
                        }
                        break;
                    case 1:
                        answerButton2.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton1.setText(quest.getIncorrect2());
                            answerButton3.setText(quest.getIncorrect3());
                        } else {
                            answerButton3.setText(quest.getIncorrect2());
                            answerButton1.setText(quest.getIncorrect3());
                        }
                        break;
                    case 2:
                        answerButton3.setText(quest.getIncorrect1());
                        randomNum=rand.nextInt(2);
                        if (randomNum==0){
                            answerButton1.setText(quest.getIncorrect2());
                            answerButton2.setText(quest.getIncorrect3());
                        } else {
                            answerButton2.setText(quest.getIncorrect2());
                            answerButton1.setText(quest.getIncorrect3());
                        }
                        break;
                }
                break;
        }

        //listeners for all buttons. back button goes back to main
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        //all other buttons send user to the answered screen
        answerButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),AnsweredScreen.class);
                //checkAnswer returns true if the answer is correct. This part is important because
                // later we will randomize what answers go on what buttons
                if (quest.checkAnswer((String) answerButton1.getText())){
                    //puts 1 on the intent if correct
                    intent.putExtra("CORRECT", 1);
                } else {
                    intent.putExtra("CORRECT", 0);
                }
                startActivity(intent);
            }
        });

        answerButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),AnsweredScreen.class);
                boolean answer =quest.checkAnswer((String) answerButton2.getText());
                if (answer){
                    intent.putExtra("CORRECT", 1);
                } else {
                    intent.putExtra("CORRECT", 0);
                }
                startActivity(intent);
            }
        });

        answerButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),AnsweredScreen.class);
                if (quest.checkAnswer((String) answerButton3.getText())){
                    intent.putExtra("CORRECT", 1);
                } else {
                    intent.putExtra("CORRECT", 0);
                }
                startActivity(intent);
            }
        });

        answerButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),AnsweredScreen.class);
                if (quest.checkAnswer((String) answerButton4.getText())){
                    intent.putExtra("CORRECT", 1);
                } else {
                    intent.putExtra("CORRECT", 0);
                }
                startActivity(intent);
            }
        });
    }
}
