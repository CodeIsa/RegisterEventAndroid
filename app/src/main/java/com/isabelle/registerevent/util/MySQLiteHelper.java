package com.isabelle.registerevent.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper  extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Eventos.db";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create tb_funcionario table
        String sqlCreateTableTbEvento = "CREATE TABLE IF NOT EXISTS tb_evento ("
                + "id integer PRIMARY KEY AUTOINCREMENT,"
                + "latitude TEXT NOT NULL,"
                + "longitude TEXT NOT NULL,"
                + "endereco TEXT(70));";

        // create tb_funcionario table
        db.execSQL(sqlCreateTableTbEvento);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
