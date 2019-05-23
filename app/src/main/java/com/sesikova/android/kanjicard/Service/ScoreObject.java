package com.sesikova.android.kanjicard.Service;

import com.sesikova.android.kanjicard.Service.ResultObject;

import java.util.ArrayList;
import java.util.List;

public class ScoreObject {

    private int score=0;

    private List<Card> cardList;

    public ScoreObject(){
        cardList = new ArrayList<Card>();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = this.score + score;
    }

    public void addCard(Card card){
        cardList.add(card);
    }

    public List<Card> getCardList(){
        return this.cardList;
    }
}
