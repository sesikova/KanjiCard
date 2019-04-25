package com.sesikova.android.nihongoquiz.Entity;

import java.util.ArrayList;
import java.util.List;

public class ScoreObject {

    private int score=0;

    private List<ResultObject> resultList;

    public ScoreObject(){
        resultList = new ArrayList<ResultObject>();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = this.score + score;
    }

    public void addNewResult(ResultObject resultObject){
        resultList.add(resultObject);
    }

    public List<ResultObject> getResultList(){
        return this.resultList;
    }
}
