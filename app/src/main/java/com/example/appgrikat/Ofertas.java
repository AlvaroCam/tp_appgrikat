package com.example.appgrikat;

import java.util.ArrayList;
import java.util.List;


public class Ofertas {
    private int ofertaId;
    private String nombre;
    private String info;
    private String imagen;
    private String fechaIn;
    private String fechaFin;
    private double descuento;
    private int localId;
    private String nombreLocal;

    public Ofertas() {

    }

    public Ofertas(int ofertaId,
                   String nombre,
                   String info,
                   String imagen,
                   String fechaIn,
                   String fechaFin,
                   double descuento,
                   int localId,
                   String nombreLocal) {
        this.ofertaId = ofertaId;
        this.nombre = nombre;
        this.info = info;
        this.imagen = imagen;
        this.fechaIn = fechaIn;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
        this.localId = localId;
        this.nombreLocal = nombreLocal;
    }

    public int getOfertaId() {
        return ofertaId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getInfo() {
        return info;
    }

    public String getImagen() {
        return imagen;
    }

    public String getFechaIn() {
        return fechaIn;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public double getDescuento() {
        return descuento;
    }

    public int getLocalId() {
        return localId;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public static List<Ofertas> listaOfertas = new ArrayList<>();

    public void setOfertaId(int ofertaId) {
        this.ofertaId = ofertaId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setFechaIn(String fechaIn) {
        this.fechaIn = fechaIn;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public static void llenarOfertas(List<Ofertas> ofertas) {
        listaOfertas = ofertas;
    }

}
