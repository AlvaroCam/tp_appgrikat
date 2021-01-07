package com.example.appgrikat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
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
    UserSessionManager manager;
    NavigationView navigationView;
    Menu menu;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        manager = new UserSessionManager(getActivity());
        View vista = inflater.inflate(R.layout.fragment_inicio,container,false);
        navigationView = getActivity().findViewById(R.id.nav_view);
        menu = navigationView.getMenu();
        cajaUser = (EditText) vista.findViewById(R.id.txtUsuario);
        cajaPas = (EditText) vista.findViewById(R.id.txtContra);
        btnIniciarSesion = (Button) vista.findViewById(R.id.btnLogin);
        btnRegistrar = (Button) vista.findViewById(R.id.btnRegistrar);
        rq = Volley.newRequestQueue(getContext());
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //1
                iniciarsesion();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new LobbyFragment());
                fr.commit();


            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new RegistrarFragment());
                fr.commit();


            }
        });

        return vista;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No se encontró el usuario" + error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Se ha encontrado el usuario" + cajaUser.getText().toString(), Toast.LENGTH_SHORT).show();
        Usuario usuario = new Usuario();
        //JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject;
        jsonObject = response;
        usuario.setUsername(jsonObject.optString("username"));
        usuario.setContrasena(jsonObject.optString("contrasena"));
        usuario.setNombre(jsonObject.optString("nombre"));
        Toast.makeText(getContext(), "Se ha encontrado el usuario: " + usuario.getNombre(), Toast.LENGTH_SHORT).show();

    }
    private void iniciarsesion() {
       // final String url = "http://virualca-001-site1.dtempurl.com/api/usuarios/buscar/" + cajaUser.getText().toString().trim();

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", cajaUser.getText().toString());
            jsonObject.put("contrasena", cajaPas.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue que = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonObjectRequest jsonObjectRequests =new JsonObjectRequest(Request.Method.GET, "http://virualca-001-site1.dtempurl.com/api/usuarios/buscar/"+cajaUser.getText().toString(),null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if( cajaPas.getText().toString().equals(response.getString("contrasena"))) {
                                manager.createSession(response.getInt("UsuarioId"),response.getString("username"),
                                        response.getString("contrasena"), response.getString("correo"),response.getString("nombre"),
                                        response.getString("celular"));

                                menu.findItem(R.id.nav_cerrar_sesion).setVisible(true);
                                menu.findItem(R.id.nav_iniciar_sesion).setVisible(false);

                                Snackbar.make(navigationView, "Bienvenido "+manager.getSesion().getNombre(),Snackbar.LENGTH_LONG).show();
                            }
                            else {
                                Snackbar.make(navigationView, "Usuario incorrecto",Snackbar.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        );
        que.add(jsonObjectRequests);


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


            textDescripcion.setText(miOferta.getInfo());
        }
    }
}
