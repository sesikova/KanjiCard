package com.sesikova.android.kanjicard.Service;

public class Card {

    private int id;
    private String kanji;
    private String english;
    private String[] variantArray;
    private String englishUser;
    private boolean markUser;

    public Card(int id, String kanji,String english) {
        this.id = id;
        this.kanji = kanji;
        this.english = english;
    }

    public String[] getVariantArray() {
        return variantArray;
    }

    public void setVariantArray(String[] variantArray) {
        this.variantArray = variantArray;
    }

    public String getInfo(String infoType) {
        String info="";
        if (infoType.equals("id")) {
            info = Integer.toString( id );
        }
        if (infoType.equals("kanji")) {
            info = kanji;
        }
        if (infoType.equals("english")) {
            info = english;
        }
        if (infoType.equals("englishUser")) {
            info = englishUser;
        }
        if (infoType.equals("markUser")) {
            info = Boolean.toString(markUser);
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


}
