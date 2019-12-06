package com.isabelle.registerevent.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "locais.db";
    private static final int DATABASE_VERSION = 2; //2 para a 2 versao, criando nova coluna
    private final String CREATE_TABLE =
            "CREATE TABLE " +
                    "localizacoes " +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "latitude TEXT NOT NULL, " +
                    "longitude TEXT NOT NULL, " +
                    "endereco TEXT" +
                    ");";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
