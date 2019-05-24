package com.sesikova.android.kanjicard.Service;

import android.database.sqlite.SQLiteDatabase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;


public class DataBaseQuery {
    private SQLiteDatabase db;

    public DataBaseQuery(Context context) {
        DataBaseAssetHelper dbAssetHelper;

        dbAssetHelper = new DataBaseAssetHelper(context);
        dbAssetHelper.getWritableDatabase();
        this.db = dbAssetHelper.getReadableDatabase();
    }

    private SQLiteDatabase getDbConnection(){
        return this.db;
    }

    private void closeDbConnection(){
        if(this.db != null){
            this.db.close();
        }
    }


    public List<Topic> getTopicList() {
        List<Topic> topicList = new ArrayList<Topic>();

        String query = "select * from topic";
        Cursor cursor = getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String kanji = cursor.getString(cursor.getColumnIndexOrThrow("kanji"));
                String english = cursor.getString(cursor.getColumnIndexOrThrow("english"));
                String image = cursor.getString(cursor.getColumnIndexOrThrow("image"));
                topicList.add(new Topic(id, kanji, english, image));
            }while(cursor.moveToNext());
        }
        cursor.close();

        return topicList;
    }

    public List<Card> getCardList(int topic){
        List<Card> cardList = new ArrayList<Card>();
        String[] variantArray = { "1", "2", "3", "4"};

        String query = "select * from card where topic =" +  topic;
        Cursor cursor = getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String kanji = cursor.getString(cursor.getColumnIndexOrThrow("kanji"));
                String english = cursor.getString(cursor.getColumnIndexOrThrow("english"));
                cardList.add(new Card(id,kanji,english));

            }
            while(cursor.moveToNext());
        }
        cursor.close();

        if(cardList.size() != 0){
            int cardCount = cardList.size();
            int cardIndex = 0;
            while(cardIndex < cardCount){
                Card card = cardList.get(cardIndex);
                card.setVariantArray(getCardVariant(card.getInfo("id")));
                //card.variantArray = getCardVariant(card.getInfo("id"));
                cardIndex++;
            }
        }

        return cardList;
    }



    private String[] getCardVariant(String cardId){
        String[] variantArray = { "", "", "", ""};
        int Index = 0;

        String query = "select * from variant where card =" +  cardId;
        Cursor cursor = getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                variantArray[Index] =cursor.getString(cursor.getColumnIndexOrThrow("english")).trim();
                Index++;
            }
            while(cursor.moveToNext());
        }
       cursor.close();

       return variantArray;
    }


}
