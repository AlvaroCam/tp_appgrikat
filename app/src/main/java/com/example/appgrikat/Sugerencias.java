package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;


public class Sugerencias {
    private int BebidaId;
    private String tituloSug;
    private String contentSug;
    private String cadenaImagenSug;

    public Sugerencias(){}

    public Sugerencias(int BebidaId, String tituloSug, String contentSug, String cadenaImagenSug){
        this.BebidaId=BebidaId;
        this.tituloSug=tituloSug;
        this.contentSug=contentSug;
        this.cadenaImagenSug=cadenaImagenSug;

    }
    public static List<Sugerencias> listaSugerencias= new ArrayList<Sugerencias>();
    public int getBebidaId(){
        return BebidaId;
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

    public void setBebidaId(int BebidaId){this.BebidaId = BebidaId;}
    public void setTituloSug(String TituloSug){this.tituloSug =TituloSug; }
    public void setContentSug(String ContentSug){ this.contentSug = ContentSug; }
    public void setCadenaImagen(String CadenaImagen){ this.cadenaImagenSug = CadenaImagen; }

}
