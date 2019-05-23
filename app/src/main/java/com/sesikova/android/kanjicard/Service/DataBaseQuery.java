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
        int Index = 0;

        String query = "select * from card where topic =" +  topic;
        Cursor cursor = getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                Index++;
                String kanji = cursor.getString(cursor.getColumnIndexOrThrow("kanji"));
                String variants = cursor.getString(cursor.getColumnIndexOrThrow("variants"));
                String english = cursor.getString(cursor.getColumnIndexOrThrow("english"));
                cardList.add(new Card(Integer.toString(Index),kanji, variants, english));
            }while(cursor.moveToNext());
        }
        cursor.close();

        return cardList;
    }


}
