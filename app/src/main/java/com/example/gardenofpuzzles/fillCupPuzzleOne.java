package com.example.gardenofpuzzles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class fillCupPuzzleOne extends PuzzleActivity {

    TextView textView;

        // non cup buttons
    Button fillButton;
    Button emptyButton;
    Button backButton;
    Button submitButton;

        // cup buttons
    Button fiveUnitButton;
    Button threeUnitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_cup_puzzle_one);
        textView = findViewById(R.id.textView);
        fillButton = findViewById(R.id.fill);
        emptyButton = findViewById(R.id.empty);
        submitButton = findViewById(R.id.submit);
        backButton = findViewById(R.id.back);

        fiveUnitButton = findViewById(R.id.fiveUnitCup);
        threeUnitButton = findViewById(R.id.threeUnitCup);


            // set mode to filling mode
        fillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jug.setToFill();
            }
        });

            //set mode to empting mode
        emptyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Jug.setToEmpty();
            }
        });


            // check if its puzzle one that you are doing
        Intent intent = getIntent();
        int question = intent.getIntExtra("FILLCUPINT", 0);

            // there is a bunch of if's (that should really be a switch) for each question
        if(question == 5) {
                // make array of jusgs to pass into fullCupPuzzle class
            Jug jugFive = new Jug(5, fiveUnitButton, 2);
            Jug jugThree = new Jug(3, threeUnitButton, 2);

            Jug[] jugs = {jugFive, jugThree};

            fillCupPuz puzzleFirst = new fillCupPuz(jugs, textView, 2, 5);
                //set the text view to see the question
            textView.setText("You have a cup of 5 oz and 3 oz. Try to get 2 oz");
            puzzleFirst.makePuzzle();

                // let the user submit there answer
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (puzzleFirst.if_win) {
                        // TODO: make correct index
                        MainActivity.completed[5] = true;
                        Intent intentWin = new Intent(getApplicationContext(), winFillCupScreen.class);
                        startActivity(intentWin);
                    }
                }
            });
        }

            // go to main activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });


        if(question == 6){

                // make array of jugs to pass into fullCupPuzzle class
            Jug jugFive = new Jug(7, fiveUnitButton, 5);
            Jug jugThree = new Jug(4, threeUnitButton, 5);
            Jug[] jugs = {jugFive, jugThree};
            fillCupPuz puzzleFirst = new fillCupPuz(jugs, textView, 2, 6);
            textView.setText("You have a cup of 7 oz and 4 oz. Try to get 5 oz");
            puzzleFirst.makePuzzle();


            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (puzzleFirst.if_win) {
                        MainActivity.completed[6] = true;
                        Intent intentWin = new Intent(getApplicationContext(), winFillCupScreen.class);
                        startActivity(intentWin);
                    }
                }
            });
        }

        if(question == 7) {
                // make array of jusgs to pass into fullCupPuzzle class
            Jug jugFive = new Jug(7, fiveUnitButton, 6);
            Jug jugThree = new Jug(4, threeUnitButton, 6);
            Jug[] jugs = {jugFive, jugThree};
            fillCupPuz puzzleFirst = new fillCupPuz(jugs, textView, 2, 6);
            textView.setText("You have a cup of 7 oz and 4 oz. Try to get 6 oz");
            puzzleFirst.makePuzzle();


            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (puzzleFirst.if_win) {
                        // TODO: make correct index
                        MainActivity.completed[7] = true;
                        Intent intentWin = new Intent(getApplicationContext(), winFillCupScreen.class);
                        startActivity(intentWin);
                    }
                }
            });
        }
        if(question == 8) {
            Jug jugFive = new Jug(5, fiveUnitButton, 4);
            Jug jugThree = new Jug(3, threeUnitButton, 4);
            Jug[] jugs = {jugFive, jugThree};
            fillCupPuz puzzleFirst = new fillCupPuz(jugs, textView, 2, 8);
            textView.setText("You have a cup of 5 oz and 3 oz. Try to get 4 oz");
            puzzleFirst.makePuzzle();


            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (puzzleFirst.if_win) {
                        // TODO: make correct index
                        MainActivity.completed[8] = true;
                        Intent intentWin = new Intent(getApplicationContext(), winFillCupScreen.class);
                        startActivity(intentWin);
                    }
                }
            });
        }
        if(question == 9) {
            Jug jugFive = new Jug(9, fiveUnitButton, 7);
            Jug jugThree = new Jug(5, threeUnitButton, 7);
            Jug[] jugs = {jugFive, jugThree};
            fillCupPuz puzzleFirst = new fillCupPuz(jugs, textView, 2, 9);
            textView.setText("You have a cup of 9 oz and 5 oz. Try to get 7 oz");
            puzzleFirst.makePuzzle();


            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (puzzleFirst.if_win) {
                        // TODO: make correct index
                        MainActivity.completed[9] = true;
                        Intent intentWin = new Intent(getApplicationContext(), winFillCupScreen.class);
                        startActivity(intentWin);
                    }
                }
            });
        }




    }
}