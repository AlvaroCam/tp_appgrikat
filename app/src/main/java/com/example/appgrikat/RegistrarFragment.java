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

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

public class RegistrarFragment extends Fragment implements Response.ErrorListener {
    EditText nombre,apellido,correo,password1,password2,username,nacimiento,telefono;
    Button btnRegistrar;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    //establece la conexion directamente con el servidor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registrar, container, false);
        nombre = (EditText) v.findViewById(R.id.eTNombre);
        correo = (EditText) v.findViewById(R.id.eTCorreo);
        password1 = (EditText) v.findViewById(R.id.eTPassword1);

        username = (EditText) v.findViewById(R.id.eTUsername);
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
        JSONObject usuario= new JSONObject();
        try {
            usuario.put("username",username.getText());
            usuario.put("contrasena",password1.getText());
            usuario.put("correo",correo.getText());
            usuario.put("nombre",nombre.getText());
            usuario.put("celular",telefono.getText());
            // Log.i("TAGs",Bebidas.buscarbebida.get(pos).getId_bebida()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url= "http://appgrikat.gear.host/api/usuarios";//+password1.getText().toString()+"/"+nombre.getText().toString()+"/"+apellido.getText().toString()+"/"+direccion.getText().toString()+"/"+telefono.getText().toString()+"/"+nacimiento.getText().toString()+"/"+correo.getText().toString();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, usuario,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext(),"Se registro existosamente el usuario",Toast.LENGTH_SHORT).show();
                        nombre.setText(" ");
                        correo.setText(" ");
                        username.setText(" ");
                        telefono.setText(" ");
                        password1.setText(" ");
                    }
                }, this);

        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Ha ocurrido un error"+error.toString(),Toast.LENGTH_SHORT).show();
    }
}