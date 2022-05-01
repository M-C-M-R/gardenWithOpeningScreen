//displays screen that we go to when we answer a question
package com.example.gardenofpuzzles;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class PuzzleInstructionsScreen extends AppCompatActivity {
    Button okButton;
    TextView instructions, title;
    int curPuzType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set up activity
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.screen_puzzle_instructions);
        Intent intent = getIntent();
        curPuzType = intent.getIntExtra("PUZTYPE",0);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.bkrdColor));
        //buttons and views
        okButton=findViewById(R.id.okButton);
        instructions=findViewById(R.id.instructions);
        title=findViewById(R.id.puzName);

        //based on the puzzle code, we display the title and detailed instructions
        switch (curPuzType){
            case 1:     //Chess
                title.setText("Chess Question");
                instructions.setText("An image of a chessboard will be given to you, and a question " +
                        "relating to the image will be asked. Select the correct answer. There will " +
                        "only be one correct answer per puzzle.");
                break;
            case 2:     //Fill Cup
                title.setText("Fill Cup Problem");
                instructions.setText("Cups of various sizes will be given to you. The sizes of the " +
                        "cups will be stated and be different for each puzzle. The goal is to get " +
                        "a certain amount of liquid in one cup, a number which will be different " +
                        "with each puzzle. You can either fill a cup to the brim, empty the cup " +
                        "completely, or empty the cup into another cup. When emptying into another " +
                        "cup, only the amount that fits in the second cup will be poured and the " +
                        "rest will remain in the first cup. You can reset the puzzle at any time.");
                break;
            case 3:     //River
                title.setText("River Crossing Problem");
                instructions.setText("There will be various characters on one side of the river. " +
                        "The goal is to get all characters across the river. Be careful though, some " +
                        "characters cannot be alone together, and the boat can't hold everyone at " +
                        "once. You can reset the puzzle at any time.");
                break;
            case 4:     //Syllogism
                title.setText("Syllogism Question");
                instructions.setText("You will be given a series of true statements. Then, you will " +
                        "be given a series of statements, one of which is true. You must select the " +
                        "true statement from the second series of statements based on reasoning from " +
                        "the first series of statements.");
                break;
            case 5:     //Truth
                title.setText("Truth Tellers and Liars Puzzle");
                instructions.setText("There will be different characters in each puzzle. Some tell " +
                        "the truth and some lie. Sometimes you will know who is telling the truth " +
                        "and will have to figure out a true statement based on that. Sometime you " +
                        "will not know who is telling the truth and will be asked to determine who " +
                        "is truthful, or figure out a true statement based on the information given.");
                break;
            default:
                break;
        }

        //this button just takes us back to the main menu for now
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), PuzzleOptionsScreen.class);
                intent.putExtra("PUZTYPE", curPuzType);
                startActivity(intent);
            }
        });
    }
}
