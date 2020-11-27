package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;


//Licetti estuvo aquí
public class Locales{
    private String nombre;
    private String descripcion;
    private int idDrawable;
    public Locales(String nombre, String descripcion, int idDrawable){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.idDrawable=idDrawable;

    }
    public static final List<Locales> listaLocales= new ArrayList<>();
    static {
        listaLocales.add(new Locales("Don Pepe", "Sabor peruano sabor a la casa", R.drawable.imagenrestaurante1));
        listaLocales.add(new Locales("La Choza Nautica", "comida Lo mejor de lo mejor", R.drawable.imagenrestaurante2));
        listaLocales.add(new Locales("La Granja", "Comida Ranchera", R.drawable.imagenrestaurante3));
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
