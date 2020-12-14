package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;

public class Valoracion {
    private int ValoracionId;
    private String comentar;
    private int puntuacion;


    public Valoracion() {

    }

    public  int getValoracionId() {
        return ValoracionId;
    }

    public  void setValoracionId(int ValoracionId) {
        this.ValoracionId = ValoracionId;
    }

    public Valoracion(int ValoracionId,String comentar, int puntuacion) {
        this.ValoracionId=ValoracionId;
        this.comentar = comentar;
        this.puntuacion = puntuacion;
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



    public static List<Valoracion> listaValoracion= new ArrayList<>();

    public static  void  llenarValoracion(List<Valoracion> valoracions){
        listaValoracion=valoracions;

    }
}
