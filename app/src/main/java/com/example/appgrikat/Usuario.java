package com.example.appgrikat;



public class Usuario {
    private String nombre_persona;
    private String apellido_persona;
    private String pass;
    private String correo_persona;
    private String telefono_persona;

    public Usuario(String correo_persona, String nombre_persona, String apellido_persona, String telefono_persona, String pass) {
        this.nombre_persona=nombre_persona;
        this.apellido_persona=apellido_persona;
        this.pass=pass;
        this.telefono_persona=telefono_persona;
        this.correo_persona=correo_persona;
    }
public Usuario(){

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCorreo_persona() {
        return correo_persona;
    }

    public void setCorreo_persona(String correo_persona) {
        this.correo_persona = correo_persona;
    }

    public String getTelefono_persona() {
        return telefono_persona;
    }

    public void setTelefono_persona(String telefono_persona) {
        this.telefono_persona = telefono_persona;
    }




}
