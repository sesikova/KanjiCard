package com.sesikova.android.nihongoquiz.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseObject {

    private static DataBase dbHelper;
    private SQLiteDatabase db;

    public DataBaseObject(Context context) {
        dbHelper = new DataBase(context);
        this.dbHelper.getWritableDatabase();
        this.db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection(){
        return this.db;
    }

    public void closeDbConnection(){
        if(this.db != null){
            this.db.close();
        }
    }
}
