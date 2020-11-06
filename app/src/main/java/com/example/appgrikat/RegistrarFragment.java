package com.example.appgrikat;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Downloader;

import org.json.JSONObject;

import androidx.fragment.app.Fragment;

public class RegistrarFragment extends Fragment implements Response.ErrorListener {
    EditText nombre,apellido,correo,password1,password2,direccion,nacimiento,telefono;
    Button btnRegistrar;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    //establece la conexion directamente con el servidor


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registrar, container, false);
        nombre = (EditText) v.findViewById(R.id.eTNombre);
        apellido = (EditText) v.findViewById(R.id.eTApellido);
        correo = (EditText) v.findViewById(R.id.eTCorreo);
        password1 = (EditText) v.findViewById(R.id.eTPassword1);
        password2 = (EditText) v.findViewById(R.id.eTPassword2);

        nacimiento = (EditText) v.findViewById(R.id.eTNacimiento);
        direccion = (EditText) v.findViewById(R.id.eTDireccion);
        telefono = (EditText) v.findViewById(R.id.eTTelefono);

        btnRegistrar = (Button) v.findViewById(R.id.btnRegistrar);
        request = Volley.newRequestQueue(getContext());
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarservicio();
            }
        });
        return v;

    }
    private void llamarservicio() {
        String url= "http://172.20.56.255:8084/Appgrikat/crearUsuario/"+password1.getText().toString()+"/"+nombre.getText().toString()+"/"+apellido.getText().toString()+"/"+direccion.getText().toString()+"/"+telefono.getText().toString()+"/"+nacimiento.getText().toString()+"/"+correo.getText().toString();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext(),"Se registro existosamente el usuario",Toast.LENGTH_SHORT).show();
                        nombre.setText(" ");
                        apellido.setText(" ");
                        correo.setText(" ");
                        nacimiento.setText(" ");
                        direccion.setText(" ");
                        telefono.setText(" ");
                        password1.setText(" ");
                    }
                }, this);
        //jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,this,this); //Lee la informacion que quiero enviar
        request.add(jsonObjectRequest);
    }//Request.Method.GET ,url, this , this


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Ha ocurrido un error"+error.toString(),Toast.LENGTH_SHORT).show();
    }
/*
    @Override
    public void onResponse(JsonObjectRequest response) {
        Toast.makeText(getContext(),"Se registro existosamente el usuario",Toast.LENGTH_SHORT).show();
        nombre.setText(" ");
        apellido.setText(" ");
        correo.setText(" ");
        nacimiento.setText(" ");
        direccion.setText(" ");
        telefono.setText(" ");
        password1.setText(" ");
    }*/
}
