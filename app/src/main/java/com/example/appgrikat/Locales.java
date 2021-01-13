package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;


public class Locales {
    private int localId;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String imagenLo;
    private int ofertaId;

    public Locales() {

    }

    public Locales(int localId, String nombre, String descripcion, String direccion, String imagenLo, int ofertaId) {
        this.localId = localId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.imagenLo = imagenLo;
        this.ofertaId = ofertaId;

    }

    public static  List<Locales> listaLocales = new ArrayList<>();

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) { this.localId = localId; }

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

    public int getOfertaId(){return ofertaId;}

    public void setOfertaId(int ofertaId){this.ofertaId = ofertaId;}

    public static void llenarLocales(List<Locales> locales) {
        listaLocales = locales;
    }

}


