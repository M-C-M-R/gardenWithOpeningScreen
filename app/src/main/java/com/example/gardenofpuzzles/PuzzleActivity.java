//superclass for the puzzles

package com.example.gardenofpuzzles;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PuzzleActivity extends AppCompatActivity {
    int puzzleType; //1: Chess 2: Fill Cup 3: River 4: Syllogism 5: Truth
    int puzzleCode; //0-20
    String question;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    //updates the puzzle - response is true if the user got the question correct
    public void updatePuzzle(boolean response){
        if (response){
            MainActivity.updateCompleted(this.puzzleCode);
            MainActivity.updateAttempted(this.puzzleCode);
        } else {
            MainActivity.updateAttempted(this.puzzleCode);
        }
    }

    public int getPuzzleCode() {
        return puzzleCode;
    }

    public int getPuzzleType() {
        return puzzleType;
    }
}
