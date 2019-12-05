package com.isabelle.registerevent.controller;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.isabelle.registerevent.model.CadastrarModel;

public class CadastrarController {

    public boolean cadastrarEvento(LatLng latLng){
        Log.i("Controller", latLng.toString());
        CadastrarModel model = new CadastrarModel();
        return true;
    }
}
