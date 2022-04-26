package com.example.entrega3.model;

import java.io.Serializable;

public class ItemList implements Serializable {
    private String titulo;
    private String release;
    private String genrer;
    private String poster_path,sinopsis,language;
    //private int imgResource;

    public ItemList(String titulo,String release,String genrer,String sinopsis,String language){
        this.titulo = titulo;
        this.release = release;
        //this.poster_path = poster_path;
        //this.imgResource = imgResource;
        this.genrer = genrer;
        this.sinopsis = sinopsis;
        this.language=language;
    }
    public String getPosterPath(){return poster_path;}
    public String getTitulo(){return titulo;}
    public  String getRelease(){return release;}
    public  String getGenrer(){return genrer;}
    public  String getSinopsis(){return sinopsis;}
    public  String getLanguage(){return language;}
    //public int getImgResource(){return imgResource;}
}
