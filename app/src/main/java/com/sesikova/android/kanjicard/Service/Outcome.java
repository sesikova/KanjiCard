package com.sesikova.android.kanjicard.Service;

import java.util.ArrayList;
import java.util.List;

public class Outcome {

    private int markGoodCount=0;
    private int markAllCount;

    private List<Card> cardList;

    public Outcome(){
        cardList = new ArrayList<Card>();
    }

    public int getMarkGoodCount() {
        return markGoodCount;
    }

    public void setMarkGoodCount(int markGoodCount) {
        this.markGoodCount = this.markGoodCount + markGoodCount;
    }

    public int getMarkAllCount() {
        return markAllCount;
    }

    public void setMarkAllCount(int markAllCount) {
        this.markAllCount = markAllCount;
    }

    public void addCard(Card card){
        cardList.add(card);
    }

    public List<Card> getCardList(){
        return this.cardList;
    }
}
