package com.isabelle.registerevent.model;

import java.io.Serializable;

public class Localidades implements Serializable{
    private int id;
    private String latitude;
    private String longitude;
    private String endereco;

    public Localidades(){ }

    public Localidades(int id, String latitude, String longitude, String endereco) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o){
        return this.id == ((Localidades)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }

}
