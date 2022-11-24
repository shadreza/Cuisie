package com.example.cuisie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqLiteHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DataBaseName = "Cuisie.db";
    public static final int DataBaseVersion = 1;

    public static final String TableName = "myCuisine";
    public static final String ColumnId = "_id";
    public static final String ColumnId = "_id";
    public static final String ColumnId = "_id";

    public SqLiteHelper(@Nullable Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
