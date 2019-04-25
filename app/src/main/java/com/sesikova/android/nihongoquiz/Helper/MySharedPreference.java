package com.sesikova.android.nihongoquiz.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private SharedPreferences prefs;

    private Context context;

    public MySharedPreference(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(Information.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public void saveQuizHighestQuizScore(int score){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putInt(Information.QUIZ_SCORE, score);
        edits.apply();
    }

    public int getCurrentHighestQuizScore(){
        return prefs.getInt(Information.QUIZ_SCORE, 0);
    }

    public boolean isHighestScore(int newScore){
        int highestScore = getCurrentHighestQuizScore();
        if(highestScore > newScore){
            return true;
        }
        return false;
    }

    public void clearAllSubscriptions(){
        prefs.edit().clear().apply();
    }
}
