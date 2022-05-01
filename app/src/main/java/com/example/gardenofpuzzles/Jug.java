package com.example.gardenofpuzzles;

import android.widget.Button;

public class Jug {
    // what mode you are in
    public static boolean fillMode;
    public static boolean emptyMode;
    public static boolean pourMode;
    // what jug was last clicked
    public static Jug lastJug;
    // has their been a jug clicked
    public static boolean oneWasClicked = false;

    public int maxAmount;
    public int currentAmount;
    public Button button;
    public static int desiredNumber;

    public Jug(int _maxAmount, Button _button, int number){
        maxAmount = _maxAmount;
        currentAmount = 0;
        button = _button;
        desiredNumber = number;
    }

    // set desired number and text
    public static void setDesiredNumber(int number){
        desiredNumber = number;
    }
    //seting the mode
    public static void setToFill(){
        fillMode = true;
        emptyMode = false;
        pourMode = false;
    }
    public static void setToEmpty(){
        fillMode = false;
        emptyMode = true;
        pourMode = false;

    }
    public static void setToPour(){
        fillMode = false;
        emptyMode = false;
        pourMode = true;
    }


    public void ifClicked(){
        if(fillMode){   // fill buckit
            currentAmount = maxAmount;
            button.setText(String.valueOf(currentAmount));

            setToPour();
        }else if(emptyMode){    // fill buckit
            this.currentAmount = 0;
            button.setText(String.valueOf(0));
            setToPour();
        }else if(pourMode){     // pour one buckit into another
            if(oneWasClicked){
                this.pourInto(lastJug);
                button.setText(String.valueOf(this.currentAmount));
                lastJug.button.setText(String.valueOf(lastJug.currentAmount));
                oneWasClicked = false;
            }else{
                oneWasClicked = true;
                lastJug = this;
            }
            changeImage(lastJug);
            changeImage(this);
        }
        changeImage(this);



    }


    public void changeImage(Jug jug){
        switch(jug.currentAmount){
            case 0:
                jug.button.setBackgroundResource(R.drawable.cupzero);
                break;
            case 1:
                jug.button.setBackgroundResource(R.drawable.cupone);
                break;
            case 2:
                jug.button.setBackgroundResource(R.drawable.cuptwo);
                break;
            case 3:
                jug.button.setBackgroundResource(R.drawable.cupthree);
                break;
            default:
                jug.button.setBackgroundResource(R.drawable.cupthree);
        }
        if(jug.currentAmount == jug.maxAmount){
            jug.button.setBackgroundResource(R.drawable.cupfore);
        }

    }

    public void empty(){
        currentAmount = 0;
    }
    // helper for pourInto
    public int howMuchLeft(){
        return (this.maxAmount - this.currentAmount);
    }
    // pour one cut into another and handle the math
    public void pourInto(Jug Other){
        if (Other.currentAmount + this.currentAmount <= this.maxAmount) {
            this.currentAmount = Other.currentAmount + this.currentAmount;
            Other.currentAmount = 0;
        } else {
            Other.currentAmount = Other.currentAmount - this.howMuchLeft();
            this.currentAmount = this.maxAmount;
        }
    }
}
