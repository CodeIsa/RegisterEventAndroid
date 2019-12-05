package com.isabelle.registerevent.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.isabelle.registerevent.dao.SQLGateway;
import com.isabelle.registerevent.model.Localidades;


import java.util.ArrayList;

public class Localidades_dao {
    private final String TABLE_LOCALIDADES = "localizacoes";
    private SQLGateway gw;

    public Localidades_dao(Context ctx){
        gw = SQLGateway.getInstance(ctx);
    }

    public boolean salvar(String latitude, String longitude, String usuario_cadastrante){
        ContentValues cv = new ContentValues();
        cv.put("latitude", latitude);
        cv.put("longitude", longitude);
        return gw.getDatabase().insert(TABLE_LOCALIDADES, null, cv) > 0;
    }

    public ArrayList<Localidades> getLocalizacoes(){
        ArrayList<Localidades> locList = new ArrayList<Localidades>();
        String query = "SELECT * FROM LOCALIZACOES";
        Cursor cursor = gw.getDatabase().rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Localidades loc = new Localidades();
                loc.setId(cursor.getInt(0));
                loc.setLatitude(cursor.getString(1));
                loc.setLongitude(cursor.getString(2));

                locList.add(loc);
            }while (cursor.moveToNext());
        }

        return locList;
    }

}
