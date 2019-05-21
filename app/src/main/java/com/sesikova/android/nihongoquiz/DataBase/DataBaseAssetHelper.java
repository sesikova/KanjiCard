package com.sesikova.android.nihongoquiz.DataBase;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseAssetHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "nihongoquiz";
    private static final int DATABASE_VERSION = 1;

    public DataBaseAssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}

