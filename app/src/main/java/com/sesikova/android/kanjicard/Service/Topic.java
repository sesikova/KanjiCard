package com.sesikova.android.kanjicard.Service;

public class Topic {

    private int id;
    private String kanji;
    private String english;
    private String image;


    public Topic(int id, String kanji,String english, String image) {
        this.id = id;
        this.kanji = kanji;
        this.english = english;
        this.image = image;
    }

    public int getTopicId(){
        return id;
    }

    public String getTopicName() {
        return english;
    }

    public String getTopicNameJapan() {
        return kanji;
    }

    public String getTopicImagePath() {
        return image;
    }
}
