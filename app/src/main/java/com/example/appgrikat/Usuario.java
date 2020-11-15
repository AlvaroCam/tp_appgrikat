package com.example.appgrikat;



public class Usuario {
    private String id_usuario;
    private String nombre_persona;
    private String apellido_persona;
    private String correo_persona;
    private String pass;
    private String telefono_persona;

    public Usuario() {

    }
    public Usuario(String id_usuario, String nombre_persona, String apellido_persona, String correo_persona, String pass, String telefono_persona) {
        this.id_usuario = id_usuario;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.correo_persona = correo_persona;
        this.pass = pass;
        this.telefono_persona = telefono_persona;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApellido_persona() {
        return apellido_persona;
    }

    public void setApellido_persona(String apellido_persona) {
        this.apellido_persona = apellido_persona;
    }

    public String getCorreo_persona() {
        return correo_persona;
    }

    public void setCorreo_persona(String correo_persona) {
        this.correo_persona = correo_persona;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTelefono_persona() {
        return telefono_persona;
    }

    public void setTelefono_persona(String telefono_persona) {
        this.telefono_persona = telefono_persona;
    }

    public static String IP = "192.168.0.89:8084";

}
