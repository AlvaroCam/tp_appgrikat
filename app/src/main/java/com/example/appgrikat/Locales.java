package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;



public class Locales{
    private String nombre;
    private String descripcion;
    private String direccion;
    private String imagenLo;

    public Locales(){

    }

    public Locales(String nombre, String descripcion, String direccion, String imagenLo){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.imagenLo=imagenLo;

    }
    public static final List<Locales> listaLocales= new ArrayList<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagenLo() {
        return imagenLo;
    }

    public void setImagenLo(String imagenLo) {
        this.imagenLo = imagenLo;
    }

    public static List<Locales> getListaLocales() {
        return listaLocales;
    }
}


