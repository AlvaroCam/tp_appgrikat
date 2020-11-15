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
    public boolean createSession(String nombre_persona, String apellido_persona, String pass, String correo_persona, String telefono_persona) {
        editor.putBoolean("sesion", true);
        editor.putString("nombre_persona", nombre_persona);
        editor.putString("apellido_persona", apellido_persona);
        editor.putString("pass", pass);
        editor.putString("correo_persona", correo_persona);
        editor.putString("telefono_persona", telefono_persona);
        return editor.commit();
    }
    public boolean closeSession(boolean resp){
        if (resp == true){
            editor.putBoolean("sesion", false);
            return editor.commit();
        }
        return false;
    }

    public boolean checkSession() {
        return preferences.getBoolean("sesion", false);
    }

  /*  public Usuario getSesion() {
        if (sesion == null) {
            sesion = new Usuario(preferences.getString("correo_persona", ""), preferences.getString("nombre_persona",
                    ""), preferences.getString("apellido_persona", ""),
                    preferences.getString("telefono_persona", ""), preferences.getString("pass", ""));
        }
        return sesion;
    }*/
}

