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
        listaCategoria.add(new Categoria("Fermentadas", R.drawable.fermentadosimg));
        listaCategoria.add(new Categoria("Destilados", R.drawable.destiladosimg));
        listaCategoria.add(new Categoria("Licores", R.drawable.licoresimg));
        listaCategoria.add(new Categoria("Cremas", R.drawable.cremasimg));
        listaCategoria.add(new Categoria("Sin Alcohol", R.drawable.noal));
        listaCategoria.add(new Categoria("Otros", R.drawable.otrosimg));
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

}
