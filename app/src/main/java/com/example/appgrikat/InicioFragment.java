package com.example.appgrikat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class InicioFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue rq;
    JsonRequest jrq2;

    EditText cajaUser, cajaPas;
    Button btnIniciarSesion, btnRegistrar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_inicio,container,false);
        cajaUser=(EditText) vista.findViewById(R.id.txtUsuario);
        cajaPas=(EditText) vista.findViewById(R.id.txtPas);
        btnIniciarSesion = (Button) vista.findViewById(R.id.btnIniciarSesion);
        btnRegistrar = (Button) vista.findViewById(R.id.btnRegistrar);
        rq = Volley.newRequestQueue(getContext());
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //1
                    iniciarsesion();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.fragment_container, new PerfilFragment());
                    fr.commit();


            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //aqui pasa algo raro
                iniciarsesion();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new RegistrarFragment());
                fr.commit();


            }
        });

        return vista;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se encontró el usuario" +error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        // Toast.makeText(getContext(),"Se ha encontrado el usuario" +cajaUser.getText().toString(), Toast.LENGTH_SHORT).show();
        Usuario usuario = new Usuario();



        //JSONArray jsonArray = response.optJSONArray("datos");

        JSONObject jsonObject = null;
        jsonObject = response ;
        usuario.setCorreo_persona(jsonObject.optString("correo_persona"));
        usuario.setPass(jsonObject.optString("contraseña"));
        usuario.setNombre_persona(jsonObject.optString("nombre_persona"));
        Toast.makeText(getContext(),"Se ha encontrado el usuario: " +usuario.getNombre_persona(), Toast.LENGTH_SHORT).show();

    }
    private void iniciarsesion() {
        String url = "http://localhost/Appgrikat/buscarUsuarioCorreo/" + cajaUser.getText().toString().trim();
        jrq2 = new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null, this, this);
        rq.add(jrq2);

        // guardar la sesion y validarla
        //

    }



    public static class detalles_Ofertas extends Fragment {
        TextView textDescripcion;
        ImageView imagenDetalle;

        public detalles_Ofertas(){

        }
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View vista = inflater.inflate(R.layout.fragment_detalle_ofertas,container,false);
            textDescripcion= (TextView) vista.findViewById(R.id.lblDescripcionOferta);
            imagenDetalle = (ImageView) vista.findViewById(R.id.imgDetalleOfertaId);
            Bundle objetoOferta =getArguments();
            Ofertas miOferta =null;
            if (objetoOferta!=null){
                miOferta= (Ofertas) objetoOferta.getSerializable("objeto");
                asignarInformacion(miOferta);
            }

            return vista;
        }

        public void asignarInformacion(Ofertas miOferta){
           // imagenDetalle.setImageResource(miOferta.getIdDrawable());
            textDescripcion.setText(miOferta.getInfo());
        }
    }
}
