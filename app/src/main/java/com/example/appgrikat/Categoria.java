package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;



public class Categoria {
    private String nombre;
    private int idDrawable;

    public Categoria( String nombre, int idDrawable) {

        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }



    public static final List<Categoria> listaCategoria = new ArrayList<>();


    static {
        listaCategoria.add(new Categoria("Criollo", R.drawable.imagencategoria1));
        listaCategoria.add(new Categoria("Marino", R.drawable.imagencategoria2));
        listaCategoria.add(new Categoria("Oriental", R.drawable.imagencategoria3));
        listaCategoria.add(new Categoria("Francesa", R.drawable.imagencategoria4));
        listaCategoria.add(new Categoria("Pasta", R.drawable.imagencategoria5));
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

}
