package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;


public class Sugerencias {
    private String nombre;
    private String descripcion;
    private int idDrawable;
    public Sugerencias(String nombre, String descripcion, int idDrawable){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.idDrawable=idDrawable;

    }
    public static final List<Sugerencias> listaSugerencias= new ArrayList<Sugerencias>();
    static {
        listaSugerencias.add(new Sugerencias("7colores","muerteconfirmada",R.drawable.ic_oferta));
    }
    public String getNombre(){
        return  nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public int getIdDrawable(){
        return idDrawable;
    }
}
