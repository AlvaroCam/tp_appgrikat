package com.example.appgrikat;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonRequest;

import androidx.fragment.app.Fragment;


public class PerfilFragment extends Fragment {
    RequestQueue rq;
    JsonRequest jrq2;

    EditText txtUsuario, txtNombre_pa,txtNombre_man;
    Button btnIniciarSesion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //aca se llama a la sesion cargada


        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }


}
