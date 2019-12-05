package com.isabelle.registerevent.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class SQLGateway {
    private static SQLGateway gw;
    private SQLiteDatabase db;

    private SQLGateway(Context ctx){
        SQLHelper helper = new SQLHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public static SQLGateway getInstance(Context ctx){
        if(gw == null)
            gw = new SQLGateway(ctx);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}
