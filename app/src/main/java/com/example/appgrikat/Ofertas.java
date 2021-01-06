package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;



public class Ofertas {
    private String nombre;
    private String info;
    private double precio;
    private String imagen;
//private int idimagen;
// private  int imgOfertaDetalle;

    public Ofertas(){

    }

    public Ofertas(String nombre, String info, double precio,String imagen){
        this.nombre=nombre;
        this.info=info;
        this.precio=precio;
        this.imagen=imagen;
        //this.imgOfertaDetalle=imgOfertaDetalle;

    }

    public String getNombre(){
        return  nombre;
    }
    public String getInfo(){ return info; }
    public String getImagen(){
        return imagen;
    }
    public double getPrecio(){
        return precio;
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
    //public byte[]  getIdDrawable(){return imagen;}
    public void setNombre(String nombre){ this.nombre=  nombre;}
    public void setInfo(String info){this.info=  info;}
    public void setPrecio(double precio){this.precio=precio;}
    // public void setIdimagen(int idimagen){this.idimagen=  idimagen;}
    public void setImagen(String  imagen){this.imagen=  imagen;}

    public static  void  llenarOfertas(List<Ofertas> ofertas){
        listaOfertas=ofertas;

    }

}
