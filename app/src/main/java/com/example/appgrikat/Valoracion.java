package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;

public class Valoracion {
    private int ValoracionId;
    private String comentar;
    private int puntuacion;
    private String nombreUsuario;


    public Valoracion() {

    }

    public  int getValoracionId() {
        return ValoracionId;
    }

    public  void setValoracionId(int ValoracionId) {
        this.ValoracionId = ValoracionId;
    }

    public Valoracion(int ValoracionId,int puntuacion,String comentar, String nombreUsuario) {
        this.ValoracionId=ValoracionId;
        this.puntuacion = puntuacion;
        this.comentar = comentar;
        this.nombreUsuario = nombreUsuario;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }



    public static List<Valoracion> listaValoracion= new ArrayList<>();

    public static  void  llenarValoracion(List<Valoracion> valoracions){
        listaValoracion=valoracions;

    }
}
