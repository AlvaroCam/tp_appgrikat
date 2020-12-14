package com.example.appgrikat;
import android.content.Context;
import android.content.SharedPreferences;


public class UserSessionManager {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Usuario sesion;

    public UserSessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("preferenciasMiApp", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    public boolean createSession(String UsuarioId,String username,String contrasena, String correo,String nombre,String celular) {
        editor.putBoolean("sesion", true);
        editor.putString("UsuarioId", UsuarioId);
        editor.putString("username", username);
        editor.putString("contrasena", contrasena);
        editor.putString("correo", correo);
        editor.putString("nombre", nombre);
        editor.putString("celular", celular);
        return editor.commit();
    }
    public boolean closeSession(){
        editor.putBoolean("session", false);
        return editor.commit();
    }


    public boolean checkSession() {
        return preferences.getBoolean("sesion", false);
    }

  public Usuario getSesion() {
        if (sesion == null) {
            sesion = new Usuario(preferences.getString("UsuarioId",""),preferences.getString("username",""),preferences.getString("contrasena", ""),preferences.getString("correo", ""), preferences.getString("nombre",
                    ""), preferences.getString("celular", ""));
        }
        return sesion;
    }
}

