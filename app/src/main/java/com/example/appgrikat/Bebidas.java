package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;


public class Bebidas {
    private double precio;
    private String nombre;
    private  String descrip;
    private int idDrawable;

    public Bebidas(double precio ,String nombre,String descrip,int idDrawable){
        this.precio= precio;
        this.nombre=nombre;
        this.descrip=descrip;
        this.idDrawable=idDrawable;
    }
    public Bebidas(){

    }
    static  List<Bebidas> buscarbebidas= new ArrayList<Bebidas>();
    public double getPrecio(){
        return  precio;
    }

    public String getNombre(){
        return  nombre;
    }

    public String getDescrip(){
        return  descrip;
    }
    public int getIdDrawable(){
        return  idDrawable;
    }
    public  static  void  setBuscarbebidas( List<Bebidas> bebida ){
        buscarbebidas=bebida;
    }
}
