package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;


public class Bebidas {
    private int id_bebida;
    private double precio;
    private String nombre;
    private String descripcion;
    private byte[] idDrawable;

    public Bebidas(int id_bebida, double precio, String nombre, String descripcion, byte[] idDrawable) {
        this.id_bebida = id_bebida;
        this.precio = precio;
        this.nombre = nombre;
        this.descripcion= descripcion;
        this.idDrawable = idDrawable;
    }


    public Bebidas() {
    }

    static  List<Bebidas> buscarbebida= new ArrayList<Bebidas>();



    public  static  void  setBuscarbebida( List<Bebidas> bebidas ){
        buscarbebida=bebidas;
    }
    public byte[] getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(byte[] idDrawable) {
        this.idDrawable = idDrawable;
    }
    public int getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(int id_bebida) {
        this.id_bebida = id_bebida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

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

}
