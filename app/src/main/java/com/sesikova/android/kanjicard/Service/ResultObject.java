package com.sesikova.android.kanjicard.Service;



public class ResultObject {

    private Integer cardIndex;
    private String kanji;
    private String english;
    private String englishUser;
    private boolean markUser;

    public ResultObject(Integer cardIndex, String kanji, String english, String englishUser, boolean markUser) {
        this.cardIndex = cardIndex;
        this.kanji = kanji;
        this.english = english;
        this.englishUser = englishUser;
        this.markUser = markUser;
    }

    public Integer getQuizIndex() {
        return cardIndex;
    }

    public String getQuestionTitle() {
        return kanji;
    }

    public String getSelectedAnswer() {
        return englishUser;
    }

    public String getCollectAnswer() {
        return english;
    }

    public boolean isCorrect() {
        return markUser;
    }
}
