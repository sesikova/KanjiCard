package com.sesikova.android.kanjicard.DataBase;

import android.database.sqlite.SQLiteDatabase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.sesikova.android.kanjicard.Entity.QuizObject;
import com.sesikova.android.kanjicard.Entity.TopicObject;



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


    public List<TopicObject> getTopicList() {
        List<TopicObject> topicList = new ArrayList<TopicObject>();
        String query = "select * from topic";
        Cursor cursor = getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String name_japan = cursor.getString(cursor.getColumnIndexOrThrow("name_japan"));
                String image = cursor.getString(cursor.getColumnIndexOrThrow("image"));
                topicList.add(new TopicObject(id, name, name_japan, image));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return topicList;
    }

    public List<QuizObject> getQuizList(int topic_id){




        List<QuizObject> QuestionList = new ArrayList<QuizObject>();
        String query = "select * from quiz where topic_id =" +  topic_id;
        Cursor cursor = getDbConnection().rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                String question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
                String options = cursor.getString(cursor.getColumnIndexOrThrow("options"));
                String answer = cursor.getString(cursor.getColumnIndexOrThrow("answer"));
                QuestionList.add(new QuizObject(topic_id, question, options, answer));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return QuestionList;
    }


}
