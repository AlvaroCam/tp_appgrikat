package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;

public class Valoracion {
    private String comentar;
    private int puntuacion;
    private String nombre;
    public static String id;


    public Valoracion() {

    }

    public  String getId() {
        return id;
    }

    public  void setId(String id) {
        Valoracion.id = id;
    }

    public Valoracion(String comentar, int puntuacion, String nombre) {
        this.comentar = comentar;
        this.puntuacion = puntuacion;
        this.nombre = nombre;
    }

    public String getComentar() {
        return comentar;
    }

    public void setComentar(String comentar) {
        this.comentar = comentar;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static List<Valoracion> listaValoracion= new ArrayList<>();

    public static  void  llenarValoracion(List<Valoracion> valoracions){
        listaValoracion=valoracions;

    }
}
