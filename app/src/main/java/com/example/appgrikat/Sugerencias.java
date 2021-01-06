package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;


public class Sugerencias {
    private int BebidaId;
    private int sugerenciaId;
    private double precioBeb;
    private String tituloSug;
    private String contentSug;
    private String cadenaImagenSug;

    public Sugerencias(){}

    public Sugerencias(int BebidaId, int SugerenciaId, double PrecioBeb, String tituloSug, String contentSug, String cadenaImagenSug){
        this.BebidaId=BebidaId;
        this.sugerenciaId= SugerenciaId;
        this.precioBeb= PrecioBeb;
        this.tituloSug=tituloSug;
        this.contentSug=contentSug;
        this.cadenaImagenSug=cadenaImagenSug;

    }
    public int getBebidaId(){
        return BebidaId;
    }
    public int getSugerenciaId(){
        return sugerenciaId;
    }
    public double getPrecioBeb(){
        return precioBeb;
    }
    public String getTituloSug(){
        return tituloSug;
    }
    public String getContentSug(){
        return contentSug;
    }
    public String getCadenaImagen(){
        return cadenaImagenSug;
    }


    public static List<Sugerencias> listaSugerencias= new ArrayList<>();

    public void setBebidaId(int BebidaId){this.BebidaId = BebidaId;}
    public void setSugerenciaId(int SugerenciaId){this.sugerenciaId = SugerenciaId;}
    public void setPrecioBeb(double PrecioBeb){this.precioBeb = PrecioBeb;}
    public void setTituloSug(String TituloSug){this.tituloSug =TituloSug; }
    public void setContentSug(String ContentSug){ this.contentSug = ContentSug; }
    public void setCadenaImagen(String CadenaImagen){ this.cadenaImagenSug = CadenaImagen; }

    public static  void  llenarSugerencias(List<Sugerencias> sugerencias){
        listaSugerencias=sugerencias;

    }


}
