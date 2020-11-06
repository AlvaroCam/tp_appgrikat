package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;



public class Ofertas {
    private String nombre;
    private String info;
    private int idDrawable;
   // private  int imgOfertaDetalle;

    public Ofertas(){

    }

    public Ofertas(String nombre, String info, int idDrawable){
        this.nombre=nombre;
        this.info=info;
        this.idDrawable=idDrawable;
        //this.imgOfertaDetalle=imgOfertaDetalle;

    }

    public String getNombre(){
        return  nombre;
    }
    public String getInfo(){
        return info;
    }
    public static final List<Ofertas> listaOfertas= new ArrayList<>();
    static {
        listaOfertas.add(new Ofertas("oferta1","comida zabrozonga",R.drawable.imagenoferta1));
        listaOfertas.add(new Ofertas("oferta2","comida zabrozonga",R.drawable.imagenoferta2));
        listaOfertas.add(new Ofertas("oferta3","comida zabrozonga",R.drawable.imagenoferta3));
        listaOfertas.add(new Ofertas("oferta4","comida zabrozonga",R.drawable.imagenoferta4));
        listaOfertas.add(new Ofertas("oferta5","comida zabrozonga",R.drawable.imagenoferta5));
        listaOfertas.add(new Ofertas("oferta6","comida zabrozonga",R.drawable.imagenoferta6));
        listaOfertas.add(new Ofertas("oferta7","comida zabrozonga",R.drawable.imagenoferta7));
        listaOfertas.add(new Ofertas("oferta8","comida zabrozonga",R.drawable.imagenoferta8));
        listaOfertas.add(new Ofertas("oferta9","comida zabrozonga",R.drawable.imagenoferta9));
    }
    public int getIdDrawable(){return idDrawable;}
    public void setNombre(String nombre){ this.nombre=  nombre;}
    public void setInfo(String info){this.info=  info;}
    public void setIdDrawable(int idDrawable){this.idDrawable=  idDrawable;}
  //  public int getImgOfertaDetalle(){return imgOfertaDetalle;}
    //public void setImgOfertaDetalle(int imgOfertaDetalle){ this.imgOfertaDetalle=  imgOfertaDetalle;}
}
