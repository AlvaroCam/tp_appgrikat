package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;



public class Ofertas {
    private String nombre;
    private String info;
    private byte[] idDrawable;
//private int idimagen;
// private  int imgOfertaDetalle;

    public Ofertas(){

    }

    public Ofertas(String nombre, String info, byte[]  idDrawable){
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
    //  public int getIdimagen(){return  idimagen;}
    public static  List<Ofertas> listaOfertas= new ArrayList<>();
    static {
        /*
        listaOfertas.add(new Ofertas("oferta1"," 1",R.drawable.imagenoferta1));
        listaOfertas.add(new Ofertas("oferta2","2 ",R.drawable.imagenoferta2));
        listaOfertas.add(new Ofertas("oferta3","3 ",R.drawable.imagenoferta3));
        listaOfertas.add(new Ofertas("oferta4","4 ",R.drawable.imagenoferta4));
        listaOfertas.add(new Ofertas("oferta5","5 ",R.drawable.imagenoferta5));
        listaOfertas.add(new Ofertas("oferta6","6 ",R.drawable.imagenoferta6));
        listaOfertas.add(new Ofertas("oferta7","7 ",R.drawable.imagenoferta7));
        listaOfertas.add(new Ofertas("oferta8","8 ",R.drawable.imagenoferta8));
        listaOfertas.add(new Ofertas("oferta9","9 ",R.drawable.imagenoferta9));
        */
    }
    public byte[]  getIdDrawable(){return idDrawable;}
    public void setNombre(String nombre){ this.nombre=  nombre;}
    public void setInfo(String info){this.info=  info;}
    // public void setIdimagen(int idimagen){this.idimagen=  idimagen;}
    public void setIdDrawable(byte[]  idDrawable){this.idDrawable=  idDrawable;}

    public static  void  llenarOfertas(List<Ofertas> ofertas){
        listaOfertas=ofertas;

    }

}
