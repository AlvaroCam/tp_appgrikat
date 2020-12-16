package com.example.appgrikat;


import java.io.Serializable;

public class Usuario implements Serializable {
    private int UsuarioId;
    private String username;
    private String nombre;
    private String correo;
    private String contrasena;
    private String celular;

    public Usuario() {

    }
    public Usuario(int UsuarioId,String username,String contrasena , String correo, String nombre, String celular) {
        this.UsuarioId = UsuarioId;
        this.username = username;
        this.contrasena = contrasena;
        this.correo = correo;
        this.nombre = nombre;
        this.celular = celular;
    }

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int UsuarioId) {
        this.UsuarioId = UsuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public static String IP = "192.168.0.89:8084";

}
