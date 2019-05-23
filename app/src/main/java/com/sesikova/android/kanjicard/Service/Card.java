package com.sesikova.android.kanjicard.Service;

public class Card {


    private String kanji;
    private String english;
    private String variants;

    private String index;
    private String englishUser;
    private boolean markUser;

    public Card(String index, String kanji, String variants, String english) {
        this.index = index;
        this.kanji = kanji;
        this.variants = variants;
        this.english = english;
    }

    public String getInfo(String infoType) {
        String info="";
        if (infoType.equals("kanji")) {
            info = this.kanji;
        }
        if (infoType.equals("english")) {
            info = this.english;
        }
        if (infoType.equals("variants")) {
            info = this.variants;
        }
        return info;
    }


    public void setInfo(String infoType, String info) {
        if (infoType.equals("englishUser")) {
            this.englishUser = info;
        }

        if (infoType.equals("markUser")) {
            this.markUser = Boolean.valueOf(info);
        }
    }


    public String[] convertOptionsToStringArray(String options){
        String[] allOptions = options.split(",");
        return allOptions;
    }


}
