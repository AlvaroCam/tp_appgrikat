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
   /* static {
        listaLocales.add(new Locales("Katmandu 1", "el original","Av La Marina 233", R.drawable.imagenrestaurante1));
        listaLocales.add(new Locales("Katmandu 2", "sucursal","Av los constructores 455", R.drawable.imagenrestaurante2));
        listaLocales.add(new Locales("Katmandu 3", "sede 3", "av las gaviotas 845",R.drawable.imagenrestaurante3));
    }
    */

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


