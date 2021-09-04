package com.example.groceryapp;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class ExternalDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "RecipeFinal2.db";
    private static final int DATABASE_VERSION = 1;

    public ExternalDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
