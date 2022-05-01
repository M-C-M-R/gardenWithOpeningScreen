//CLASS OF MULTIPLECHOICEQUESTION. Does NOT display question.

package com.example.gardenofpuzzles;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class MultipleChoiceQuestion extends PuzzleActivity implements Parcelable {
    //from PuzzleActivity: puzzleType, puzzleCode, question, updatePuzzle()
    String correctAnswer;
    String incorrectAnswers[]=new String[3];

    //constructor
    public MultipleChoiceQuestion(int pc){puzzleCode=pc;}
    public MultipleChoiceQuestion(int pt, int pc, String quest, String ca, String ica1,
                                       String ica2, String ica3){
        puzzleType=pt;
        puzzleCode=pc;
        question=quest;
        correctAnswer=ca;
        incorrectAnswers[0]=ica1;
        incorrectAnswers[1]=ica2;
        incorrectAnswers[2]=ica3;
    }
    //getters
    public String getCorrect(){
        return correctAnswer;
    }

    public String getIncorrect1(){
        return incorrectAnswers[0];
    }

    public String getIncorrect2(){
        return incorrectAnswers[1];
    }

    public String getIncorrect3(){
        return incorrectAnswers[2];
    }

    public String getQuestion() {
        return question;
    }

    //writes the question to a parcel - MANDATORY for adding to intent
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(puzzleType);
        parcel.writeInt(puzzleCode);
        parcel.writeString(question);
        parcel.writeString(correctAnswer);
        parcel.writeStringArray(incorrectAnswers);
    }
    //reads the question from a parcel - MANDATORY for getting from intent
    //must be in the same order as the writeToParcel, otherwise the data will not be read correctly
    protected MultipleChoiceQuestion(Parcel in) {
        puzzleType = in.readInt();
        puzzleCode = in.readInt();
        question = in.readString();
        correctAnswer = in.readString();
        incorrectAnswers = in.createStringArray();
    }

    //also mandatory for intent passing
    public static final Creator<MultipleChoiceQuestion> CREATOR = new Creator<MultipleChoiceQuestion>() {
        @Override
        public MultipleChoiceQuestion createFromParcel(Parcel in) {
            return new MultipleChoiceQuestion(in);
        }

        @Override
        public MultipleChoiceQuestion[] newArray(int size) {
            return new MultipleChoiceQuestion[size];
        }
    };

    //also mandatory for intent passing
    @Override
    public int describeContents() {
        return 0;
    }

    //returns true if the answer is correct
    public boolean checkAnswer(String resp){
        if (correctAnswer==resp){
            updatePuzzle(true);
            return true;
        } else {
            updatePuzzle(false);
            return false;
        }
    }
}
