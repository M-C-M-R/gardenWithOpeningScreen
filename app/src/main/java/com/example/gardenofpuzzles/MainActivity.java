/*
Main Activity: Displays garden with farmer to walk around and choose puzzles
 */
package com.example.gardenofpuzzles;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //buttons
    ImageButton upButt;
    ImageButton downButt;
    ImageButton leftButt;
    ImageButton rightBut;
    Button puzzButton;
    // views
    ImageView farmer;
    TextView position;
    //all the flowers. There definitely was a better way to do this than making 100 views but we
        //don't have time to figure that out
    ImageView f11,f12,f13,f14,f15,f21,f22,f23,f24,f25,f3,f41,f42,f43,f44,f45,f51,f52,f53,f54,f55;
    //nums
    int positionNum = 0;
    long animationDeration = 1000; // millis
    //make two arrays size 21, boolean, to store attempted and completed
    static boolean[] completed = new boolean[21];
    static boolean[] attempted = new boolean[21];
    //make questions
    //Chess Questions (PuzzleCode 1)
    static MultipleChoiceQuestion c1=new MultipleChoiceQuestion(1,0,"In the position below, which is the strongest move for White?","Qf3","Re7","Qxc7","Rxc8");
    static MultipleChoiceQuestion c2=new MultipleChoiceQuestion(1,1,"White to move. What is the minimum number of moves for a white to checkmate the black king? Assume both sides play optimally. If a forced mate is not possible, give your answer as 0 (Composed by L-C. M. de La Bourdonnais, 1883)", "4 moves", "0 moves", "3 moves", "5 moves");
    static MultipleChoiceQuestion c3=new MultipleChoiceQuestion(1,2,"It is White to move. If White plays Bkf6, can Black recapture the same value of three points at its next move?","Either Qxf6 or Pxf6 works","Yes, with Qxf6","Yes, with Pxf6","No");
    static MultipleChoiceQuestion c4=new MultipleChoiceQuestion(1,3,"White has just played bishop to b5. Is it possible for Black to move his pawn from d7 to d6 at the next move?","No, as the King will be put into check.","Yes, it is the strongest move.","Yes, but it is not the strongest move.","No, as the King will be put into checkmate.");
    static MultipleChoiceQuestion c5=new MultipleChoiceQuestion(1,4,"It is White to move. What should be the next move?","Cannot move, it is checkmate  ","Kxh2","Kh1","None of the above");
    //Fill Cup Problems (PuzzleCode 2)
    static Button fiveUnitButton;
    static Button threeUnitButton;
    static TextView textView;
    static Jug jugFive = new Jug(5, fiveUnitButton, 2);
    static Jug jugThree = new Jug(3, threeUnitButton, 2);
    static Jug[] jugs = {jugFive, jugThree};

    static fillCupPuz fcp1 = new fillCupPuz(jugs, textView, 2, 5);


    //Syllogisms (PuzzleCode 4)
    static MultipleChoiceQuestion s1=new MultipleChoiceQuestion(4,11,"Statements:\n" +
            "Some birds are reptiles.\n" +
            "Some insects are reptiles.\n" +
            "Conclusions:\n" +
            "1, All insects being reptiles is a possibility.\n" +
            "2, Some reptiles are birds.\n" +
            "\n" +
            "\tChoose the conclusion(s) from the following statements:\n", "Both Conclusion 1 and 2", "Only Conclusion 1", "Only Conclusion 2", "Neither Conclusion 1 nor 2");
    static MultipleChoiceQuestion s2=new MultipleChoiceQuestion(4,12,"Statements:\n" +
            "Some bottles are cups.\n" +
            "All cups are glasses.\n" +
            "All glasses are saucers.\n" +
            "Conclusions:\n" +
            "1, Some bottles are glasses.\n" +
            "2, Some glasses are bottles.\n" +
            "3, All cups are saucers.\n" +
            "\n" +
            "\tChoose the conclusion(s) from the following statements:\n", "All", "Only 1 and 2", "Only 2 and 3", "None of these");
    static MultipleChoiceQuestion s3=new MultipleChoiceQuestion(4,13,"Statements:\n" +
            "Some orange is yellow\t\tSome yellow are green\n" +
            "All green is blue\t\t\tNo blue is black\n" +
            "Conclusions:\n" +
            "1, No black is green.\n" +
            "2, Some yellow is blue.\n" +
            "3, Some black is orange.\n" +
            "4, No black is orange.\n" +
            "\n" +
            "\tChoose the conclusion(s) from the following statements:\n", "Only 1 and 2", "Either 3 or 4", "Only 1 AND either 3 or 4", "None of these");
    static MultipleChoiceQuestion s4=new MultipleChoiceQuestion(4,14,"Statements\n" +
            "All clocks are watches.\n" +
            "Some clocks are alarms.\n" +
            "\tConclusion\n" +
            "\t\t1, Some alarms are watches.\n" +
            "2, All watches are alarm.\n" +
            "\n" +
            "\tChoose the conclusion(s) from the following statements:\n", "Only Conclusion 1", "Only Conclusion 2", "Neither Conclusion 1 nor 2", "Both Conclusion 1 and 2");
    static MultipleChoiceQuestion s5=new MultipleChoiceQuestion(4,15,"Statements\n" +
            "No Lion is an Elephant\n" +
            "All Elephants are Kangaroos\n" +
            "All Kangaroos are Giraffes\n" +
            "\tConclusion\n" +
            "\t\t1. All Kangaroos can’t be Lions\n" +
            "2. All Lions are Giraffes\n" +
            "\n" +
            "\tChoose the conclusion(s) from the following statements:\n", "Only Conclusion 1", "Only Conclusion 2", "Both Conclusion 1 and 2", "Either conclusion 1 or 2");
    //Truth Tellers and Liars (PuzzleCode 5)
    static MultipleChoiceQuestion ttl1=new MultipleChoiceQuestion(5, 16, "Three persons A, B and C gave these statements:\n" +
            "A said, either Freedom Party or Green Party won the elections.\n" +
            "B said, Freedom Party won.\n" +
            "C said, neither Freedom Party nor Green Party won the elections.\n" +
            "Of these persons, only one person is wrong.\n" +
            "Who won the elections?\n",
            "Freedom Party", "Green Party", "Data Inadequate", "None of these");
    static MultipleChoiceQuestion ttl2=new MultipleChoiceQuestion(5,17,"The police rounded up Tolu, Molu, and Golu yesterday because one of them was suspected of robbing the local bank. The 3 suspects gave the following statements after intensive questioning:\n" +
            "Tolu: I’m innocent.\n" +
            "Molu: I’m innocent.\n" +
            "Golu: Molu is the guilty one.\nWho robbed the bank among the three persons, if only one of the statements will be true?", "Tolu", "Molu", "Golu", "None of these");
    static MultipleChoiceQuestion ttl3=new MultipleChoiceQuestion(5,18,"While searching for a Painter, Ali met three locals - Raj, Rajan, and Roy - who always gave two replies to any question. Among them one is a truth-teller, one is a liar and one is an alternator. When Ali asked them, \"Who among you is the painter?\", their replies were: \n" +
            "Raj: \tI am the Painter. Rajan is a liar\n" +
            "Rajan:\tI am the Painter. Roy is a liar\n" +
            "Roy: \tRajan is the Painter. Raj is a liar.\n" +
            "\n" +
            "\tWho is the painter? \n", "Rajan", "Raj", "Roy", "Either Rajan or Roy");
    static MultipleChoiceQuestion ttl4=new MultipleChoiceQuestion(5, 19, "In Honolulu Island, there are two types of people-truth tellers and liars. Truth-tellers always speak the truth and liars always lie. I met three residents Ho, Lo, and Po, and asked them \"Who among you is the liar?\" The following are their replies: \n" +
            "Ho:\tI am a truth-teller.\n" +
            "Lo:\tHo is not a truth-teller.\n" +
            "Po:\tLo is not a liar.\n" +
            "If it is known that exactly one person among them is a liar and the other two are truth-tellers, then who among them is the liar?\n", "Ho", "Lo", "Po", "Data Inadequate");
    static MultipleChoiceQuestion ttl5=new MultipleChoiceQuestion(5,20,"Fact 1: Mary said,\"Ann and I both have cats.\"\n" +
            "    Fact 2: Ann said, \"I don't have a cat.\"\n" +
            "    Fact 3: Mary always tells the truth, but Ann sometimes lies.\n" +
            "\n" +
            "If the first three statements are facts, which of the following statements must also be a fact?\n" +
            "\n" +
            "I: Ann has a cat.\n" +
            "II: Mary has a cat.\n" +
            "III: Ann is lying.\n", "All the statements are facts.", "I only", "II only", "I and II only");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setting up the activity
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        // set buttons
        upButt = findViewById(R.id.upButton);
        downButt = findViewById(R.id.downButton);
        leftButt = findViewById(R.id.leftButton);
        rightBut = findViewById(R.id.rightButton);
        puzzButton = findViewById(R.id.puzButton);
        puzzButton.setText("Check Stats");
        // set views
        position = findViewById(R.id.location);
        farmer = findViewById(R.id.farmerImage);
        position.setVisibility(View.GONE);
        f11=findViewById(R.id.flower1_1);
        //and the flowers T-T
        if (completed[0]) f11.setVisibility(View.VISIBLE); else f11.setVisibility(View.GONE);
        f12=findViewById(R.id.flower1_2);
        if (completed[1]) f12.setVisibility(View.VISIBLE); else f12.setVisibility(View.GONE);
        f13=findViewById(R.id.flower1_3);
        if (completed[2]) f13.setVisibility(View.VISIBLE); else f13.setVisibility(View.GONE);
        f14=findViewById(R.id.flower1_4);
        if (completed[3]) f14.setVisibility(View.VISIBLE); else f14.setVisibility(View.GONE);
        f15=findViewById(R.id.flower1_5);
        if (completed[4]) f15.setVisibility(View.VISIBLE); else f15.setVisibility(View.GONE);
        f21=findViewById(R.id.flower2_1);
        if (completed[5]) f21.setVisibility(View.VISIBLE); else f21.setVisibility(View.GONE);
        f22=findViewById(R.id.flower2_2);
        if (completed[6]) f22.setVisibility(View.VISIBLE); else f22.setVisibility(View.GONE);
        f23=findViewById(R.id.flower2_3);
        if (completed[7]) f23.setVisibility(View.VISIBLE); else f23.setVisibility(View.GONE);
        f24=findViewById(R.id.flower2_4);
        if (completed[8]) f24.setVisibility(View.VISIBLE); else f24.setVisibility(View.GONE);
        f25=findViewById(R.id.flower2_5);
        if (completed[9]) f25.setVisibility(View.VISIBLE); else f25.setVisibility(View.GONE);
        f3=findViewById(R.id.flower3);
        if (completed[10]) f3.setVisibility(View.VISIBLE); else f3.setVisibility(View.GONE);
        f41=findViewById(R.id.flower4_1);
        if (completed[11]) f41.setVisibility(View.VISIBLE); else f41.setVisibility(View.GONE);
        f42=findViewById(R.id.flower4_2);
        if (completed[12]) f42.setVisibility(View.VISIBLE); else f42.setVisibility(View.GONE);
        f43=findViewById(R.id.flower4_3);
        if (completed[13]) f43.setVisibility(View.VISIBLE); else f43.setVisibility(View.GONE);
        f44=findViewById(R.id.flower4_4);
        if (completed[14]) f44.setVisibility(View.VISIBLE); else f44.setVisibility(View.GONE);
        f45=findViewById(R.id.flower4_5);
        if (completed[15]) f45.setVisibility(View.VISIBLE); else f45.setVisibility(View.GONE);
        f51=findViewById(R.id.flower5_1);
        if (completed[16]) f51.setVisibility(View.VISIBLE); else f51.setVisibility(View.GONE);
        f52=findViewById(R.id.flower5_2);
        if (completed[17]) f52.setVisibility(View.VISIBLE); else f52.setVisibility(View.GONE);
        f53=findViewById(R.id.flower5_3);
        if (completed[18]) f53.setVisibility(View.VISIBLE); else f53.setVisibility(View.GONE);
        f54=findViewById(R.id.flower5_4);
        if (completed[19]) f54.setVisibility(View.VISIBLE); else f54.setVisibility(View.GONE);
        f55=findViewById(R.id.flower5_5);
        if (completed[20]) f55.setVisibility(View.VISIBLE); else f55.setVisibility(View.GONE);

        // the positions that have gardens are 1,2,5,6,8
        //garden=puzzlecode(puzzletype); 1=1(Chess); 2=2(Fill Cup); 5=3(River); 6=4(Syllogism); 8=5(Truth)

        // each button gets a switch statement to handle all positions
        upButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (positionNum) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        positionNum = 0;
                        // animator that takes a object that you want to move and a distance you want to be at as paramiters.
                        ObjectAnimator animatorY2 = ObjectAnimator.ofFloat(farmer, "y", 350f);
                        // an animatiorSet
                        AnimatorSet animatiorSet2 = new AnimatorSet();
                        // link the animatorSet and animator
                        animatiorSet2.playTogether( animatorY2);
                        // start it
                        animatiorSet2.start();
                        puzzButton.setText("Check Stats");
                        break;
                    case 3:
                        positionNum = 1;
                        ObjectAnimator animatorY3 = ObjectAnimator.ofFloat(farmer, "y", 350f);
                        AnimatorSet animatiorSet3 = new AnimatorSet();
                        animatiorSet3.playTogether( animatorY3);
                        animatiorSet3.start();
                        puzzButton.setText("Chess Question");
                        puzzButton.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        positionNum = 3;
                        ObjectAnimator animatorY4 = ObjectAnimator.ofFloat(farmer, "y", 700f);
                        AnimatorSet animatiorSet4 = new AnimatorSet();
                        animatiorSet4.playTogether( animatorY4);
                        animatiorSet4.start();
                        break;
                    case 5:
                        positionNum = 2;
                        ObjectAnimator animatorY5 = ObjectAnimator.ofFloat(farmer, "y", 700f);
                        AnimatorSet animatiorSet5 = new AnimatorSet();
                        animatiorSet5.playTogether( animatorY5);
                        animatiorSet5.start();
                        puzzButton.setText("Fill Cup Problem");
                        break;
                    case 6:
                        positionNum = 4;
                        ObjectAnimator animatorY6 = ObjectAnimator.ofFloat(farmer, "y", 1150f);
                        AnimatorSet animatiorSet6 = new AnimatorSet();
                        animatiorSet6.playTogether( animatorY6);
                        animatiorSet6.start();
                        puzzButton.setVisibility(View.GONE);
                        break;
                    case 7:
                        positionNum = 6;
                        ObjectAnimator animatorY7 = ObjectAnimator.ofFloat(farmer, "y", 1400f);
                        AnimatorSet animatiorSet7 = new AnimatorSet();
                        animatiorSet7.playTogether( animatorY7);
                        animatiorSet7.start();
                        puzzButton.setText("Syllogism Question");
                        puzzButton.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        positionNum = 5;
                        ObjectAnimator animatorY8 = ObjectAnimator.ofFloat(farmer, "y", 1150f);
                        AnimatorSet animatiorSet8 = new AnimatorSet();
                        animatiorSet8.playTogether(animatorY8);
                        animatiorSet8.start();
                        puzzButton.setText("River Crossing Problem");
                        break;
                }
                position.setText(String.valueOf(positionNum));
            }
        });

        downButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (positionNum) {
                    case 0:
                        positionNum = 2;
                        // code to add animation (need animator and animatiorSet1)
                        ObjectAnimator animatorY1 = ObjectAnimator.ofFloat(farmer, "y", 700f);
                        AnimatorSet animatiorSet1 = new AnimatorSet();
                        animatiorSet1.playTogether(animatorY1);
                        animatiorSet1.start();
                        puzzButton.setText("Fill Cup Problem");
                        break;
                    case 1:
                        positionNum = 3;
                        ObjectAnimator animatorY5 = ObjectAnimator.ofFloat(farmer, "y", 700f);
                        AnimatorSet animatiorSet5 = new AnimatorSet();
                        animatiorSet5.playTogether( animatorY5);
                        animatiorSet5.start();
                        puzzButton.setVisibility(View.GONE);
                        break;
                    case 2:
                        positionNum = 5;
                        ObjectAnimator animatorY2 = ObjectAnimator.ofFloat(farmer, "y", 1150f);
                        AnimatorSet animatiorSet2 = new AnimatorSet();
                        animatiorSet2.playTogether( animatorY2);
                        animatiorSet2.start();
                        puzzButton.setText("River Crossing Problem");
                        break;
                    case 3:
                        positionNum = 4;
                        ObjectAnimator animatorY4 = ObjectAnimator.ofFloat(farmer, "y", 1150f);
                        AnimatorSet animatiorSet4 = new AnimatorSet();
                        animatiorSet4.playTogether( animatorY4);
                        animatiorSet4.start();
                        break;
                    case 4:
                        positionNum = 6;
                        ObjectAnimator animatorY6 = ObjectAnimator.ofFloat(farmer, "y", 1400);
                        AnimatorSet animatiorSet6 = new AnimatorSet();
                        animatiorSet6.playTogether( animatorY6);
                        animatiorSet6.start();
                        puzzButton.setText("Syllogism Question");
                        puzzButton.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        positionNum = 8;
                        ObjectAnimator animatorY3 = ObjectAnimator.ofFloat(farmer, "y", 1720f);
                        AnimatorSet animatiorSet3 = new AnimatorSet();
                        animatiorSet3.playTogether( animatorY3);
                        animatiorSet3.start();
                        puzzButton.setText("Truth Tellers and Liars Puzzle");
                        break;
                    case 6:
                        positionNum = 7;
                        ObjectAnimator animatorY7 = ObjectAnimator.ofFloat(farmer, "y", 1720f);
                        AnimatorSet animatiorSet7 = new AnimatorSet();
                        animatiorSet7.playTogether( animatorY7);
                        animatiorSet7.start();
                        puzzButton.setVisibility(View.GONE);
                        break;
                    case 7:
                        positionNum = 7;
                        break;
                }
                position.setText(String.valueOf(positionNum));
            }
        });

        leftButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (positionNum) {
                    case 0:
                        break;
                    case 1:
                        positionNum = 0;
                        ObjectAnimator animatorX1 = ObjectAnimator.ofFloat(farmer, "x", 150f);
                        animatorX1.setDuration(animationDeration);
                        AnimatorSet animatiorSet1 = new AnimatorSet();
                        animatiorSet1.playTogether(animatorX1);
                        animatiorSet1.start();
                        puzzButton.setText("Check Stats");
                        puzzButton.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        break;
                    case 3:
                        positionNum = 2;
                        ObjectAnimator animatorX2 = ObjectAnimator.ofFloat(farmer, "x", 150f);
                        animatorX2.setDuration(animationDeration);
                        AnimatorSet animatiorSet2 = new AnimatorSet();
                        animatiorSet2.playTogether(animatorX2);
                        animatiorSet2.start();
                        puzzButton.setText("Fill Cup Problem");
                        puzzButton.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        positionNum = 5;
                        ObjectAnimator animatorX3 = ObjectAnimator.ofFloat(farmer, "x", 150f);
                        animatorX3.setDuration(animationDeration);
                        AnimatorSet animatiorSet3 = new AnimatorSet();
                        animatiorSet3.playTogether(animatorX3);
                        animatiorSet3.start();
                        puzzButton.setText("River Crossing Problem");
                        puzzButton.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        positionNum = 8;
                        ObjectAnimator animatorX4 = ObjectAnimator.ofFloat(farmer, "x", 150f);
                        animatorX4.setDuration(animationDeration);
                        AnimatorSet animatiorSet4 = new AnimatorSet();
                        animatiorSet4.playTogether(animatorX4);
                        animatiorSet4.start();
                        puzzButton.setText("Truth Tellers and Liars Puzzle");
                        puzzButton.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        break;
                }
                position.setText(String.valueOf(positionNum));
            }
        });

        rightBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (positionNum) {
                    case 0:
                        positionNum = 1;
                        ObjectAnimator animatorX1 = ObjectAnimator.ofFloat(farmer, "x", 750f);
                        animatorX1.setDuration(animationDeration);
                        AnimatorSet animatiorSet1 = new AnimatorSet();
                        animatiorSet1.playTogether(animatorX1);
                        animatiorSet1.start();
                        puzzButton.setText("Chess Question");
                        puzzButton.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        break;
                    case 2:
                        positionNum = 3;
                        ObjectAnimator animatorX2 = ObjectAnimator.ofFloat(farmer, "x", 750f);
                        animatorX2.setDuration(animationDeration);
                        AnimatorSet animatiorSet2 = new AnimatorSet();
                        animatiorSet2.playTogether(animatorX2);
                        animatiorSet2.start();
                        puzzButton.setVisibility(View.GONE);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        positionNum = 4;
                        ObjectAnimator animatorX3 = ObjectAnimator.ofFloat(farmer, "x", 750f);
                        animatorX3.setDuration(animationDeration);
                        AnimatorSet animatiorSet3 = new AnimatorSet();
                        animatiorSet3.playTogether(animatorX3);
                        animatiorSet3.start();
                        puzzButton.setVisibility(View.GONE);
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        positionNum = 7;
                        ObjectAnimator animatorX4 = ObjectAnimator.ofFloat(farmer, "x", 750f);
                        animatorX4.setDuration(animationDeration);
                        AnimatorSet animatiorSet4 = new AnimatorSet();
                        animatiorSet4.playTogether(animatorX4);
                        animatiorSet4.start();
                        puzzButton.setVisibility(View.GONE);
                        break;
                }
                position.setText(String.valueOf(positionNum));
            }

        });

        //button to take us to puzzle screen
        puzzButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //takes us to the stats screen
                if (positionNum==0){
                    Intent intent = new Intent(getApplicationContext(),StatsScreen.class);
                    startActivity(intent);
                }
                //based on what location we are at, that determines the puzzle code
                int puzType=0;
                switch (positionNum){
                    case 1:
                        puzType=1;
                        break;
                    case 2:
                        puzType=2;
                        break;
                    case 5:
                        puzType=3;
                        break;
                    case 6:
                        puzType=4;
                        break;
                    case 8:
                        puzType=5;
                        break;
                    default:
                        break;
                }
                //if we are on a puzzle plot, open the puzzle screen
                if (puzType!=0){
                    Intent intent = new Intent(getApplicationContext(),PuzzleOptionsScreen.class);
                    //pass puzzle code so that we can display it properly
                    intent.putExtra("PUZTYPE",puzType);
                    startActivity(intent);
                }
            }
        });
    }

    //returns how many puzzles have been completed
    public static int getCompleted(){
        int num = 0;
        for (int i=0; i<21; i++){
            if (completed[i]){
                num++;
            }
        }
        return num;
    }
    //returns how many puzzles, of type puzType, have been completed
    public static int getCompleted(int puzType){
        int num = 0;
        switch (puzType){
            case 1:
                for (int i=0; i<5; i++){
                    if (completed[i]){
                        num++;
                    }
                }
                return num;
            case 2:
                for (int i=5; i<10; i++){
                    if (completed[i]){
                        num++;
                    }
                }
                return num;
            case 3:
                if (completed[10]){
                    return 1;
                } else {
                    return 0;
                }
            case 4:
                for (int i=11; i<15; i++){
                    if (completed[i]){
                        num++;
                    }
                }
                return num;
            case 5:
                for (int i=16; i<21; i++){
                    if (completed[i]){
                        num++;
                    }
                }
                return num;
        }
        return 0;
    }
    //returns how many puzzles have been attempted
    public static int getAttempted(){
        int num = 0;
        for (int i=0; i<21; i++){
            if (attempted[i]){
                num++;
            }
        }
        return num;
    }
    //returns how many puzzles of puzType have been attempted
    public static int getAttempted(int puzType){
        int num = 0;
        switch (puzType){
            case 1:
                for (int i=0; i<5; i++){
                    if (attempted[i]){
                        num++;
                    }
                }
                return num;
            case 2:
                for (int i=5; i<10; i++){
                    if (attempted[i]){
                        num++;
                    }
                }
                return num;
            case 3:
                if (attempted[10]){
                    return 1;
                } else {
                    return 0;
                }
            case 4:
                for (int i=11; i<15; i++){
                    if (attempted[i]){
                        num++;
                    }
                }
                return num;
            case 5:
                for (int i=16; i<21; i++){
                    if (attempted[i]){
                        num++;
                    }
                }
                return num;
        }
        return 5;
    }

    //sets a puzzle to attempted when attempted
    public static void updateAttempted(int puzCode){
        attempted[puzCode]=true;
    }
    //sets a puzzle to completed when completed
    public static void updateCompleted(int puzCode){
        completed[puzCode]=true;
    }
    //resets the attempted back to unattempted
    public static void resetAttempted(int puzType){
        switch (puzType){
            case 1:
                for (int i=0; i<5; i++){
                    //if the puzzle is not completed, set attempted to false
                    if (completed[i]==false){
                        attempted[i]=false;
                    }
                }
                break;
            case 2:
                for (int i=5; i<10; i++){
                    if (completed[i]==false){
                        attempted[i]=false;
                    }
                }
                break;
            case 3:
                attempted[10]=false;
                break;
            case 4:
                for (int i=11; i<15; i++){
                    if (completed[i]==false){
                        attempted[i]=false;
                    }
                }
                break;
            case 5:
                for (int i=16; i<21; i++){
                    if (completed[i]==false){
                        attempted[i]=false;
                    }
                }
                break;
        }
    }
    //returns a multiple choice question based on puzType
    public static MultipleChoiceQuestion getQuestion(int puzType){
        //if all puzzles of puzType have been completed, indicate that
        if (getCompleted(puzType)==5){
            MultipleChoiceQuestion nomore = new MultipleChoiceQuestion(-1);
            return nomore;
        }
        //if all puzzles of puzType have been attempted (but NOT completed), reset attempted
        if (getAttempted(puzType)==5){
            resetAttempted(puzType);
        }
        //generate a random num to pick the puzzle
        Random rand = new Random();
        int puzCode = rand.nextInt(5);
        switch (puzType) {
            case 1:
                //will generate another random num until we get a puzzle that has not been attempted
                while (attempted[puzCode]) {
                    puzCode = rand.nextInt(5);
                }
                switch (puzCode) {
                    case 0:
                        return c1;
                    case 1:
                        return c2;
                    case 2:
                        return c3;
                    case 3:
                        return c4;
                    case 4:
                        return c5;
                }
                break;
            case 4:
                //add 11 because starting at puzCode 11
                puzCode += 11;
                while (attempted[puzCode]) {
                    puzCode = rand.nextInt(5) + 11;
                }
                switch (puzCode) {
                    case 11:
                        return s1;
                    case 12:
                        return s2;
                    case 13:
                        return s3;
                    case 14:
                        return s4;
                    case 15:
                        return s5;
                }
                break;
            case 5:
                //add 16 because starting at puzCode 16
                puzCode += 16;
                while (attempted[puzCode]) {
                    puzCode = rand.nextInt(5) + 16;
                }
                switch (puzCode) {
                    case 16:
                        return ttl1;
                    case 17:
                        return ttl2;
                    case 18:
                        return ttl3;
                    case 19:
                        return ttl4;
                    case 20:
                        return ttl5;
                }
                break;
            }
            return c1;
        }

        // get a random not yet completed fill cup question
        public static int getFillCupQuestion(){
            int random = (int)(Math.random() * 5 + 5);
            while (completed[random] == true){
                random = (int)(Math.random() * 5 + 5);
            }
            return random;
        }
    //resets the completed and attempted data
    public static void clearData(){
        for (int i=0; i<21; i++){
            completed[i]=false;
            attempted[i]=false;
        }
    }

}