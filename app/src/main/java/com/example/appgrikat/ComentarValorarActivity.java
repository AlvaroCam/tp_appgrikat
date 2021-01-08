package com.example.appgrikat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ComentarValorarActivity extends AppCompatActivity {
    Valoracion val=new Valoracion();
    private List<Valoracion> listaValoracion = new ArrayList<>();
    private UserSessionManager manager;
    private RecyclerView recyclerCom;
    private LinearLayoutManager layoutManager;
    private AdaptadorComentarios adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentar_valorar);


       // Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        final int pos = getIntent().getExtras().getInt("pos");

        manager = new UserSessionManager(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(Bebidas.buscarbebida.get(pos).getNombre());

       // TextView nombreu = findViewById(R.id.lblNombre);
        //nombreu.setText(manager.getSesion().getNombre());
        TextView descripcion = findViewById(R.id.txtDescripcion);
        descripcion.setText(Bebidas.buscarbebida.get(pos).getDescripcion());
        ImageView imagen = findViewById(R.id.idDrawable);
        Glide.with(this).load(Bebidas.buscarbebida.get(pos).getImagenbe())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(imagen);
        recyclerCom = findViewById(R.id.idRecyclerComent);
        layoutManager = new LinearLayoutManager(this);
        recyclerCom.setLayoutManager(layoutManager);

        adaptador = new AdaptadorComentarios();
        adaptador.listarValoraciones(this,Bebidas.buscarbebida.get(pos).getId_bebida());
        recyclerCom.setAdapter(adaptador);

        final EditText txtComentar = findViewById(R.id.txtComentar);
        final RatingBar rbPuntuacion = findViewById(R.id.rbPuntuacion);
        ImageButton btnComentar = findViewById(R.id.btnComentar);
        btnComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtComentar.getText().toString().isEmpty()) {
                    Snackbar.make(txtComentar, "Este campo no puede quedar en blanco.", Snackbar.LENGTH_LONG).show();
                }
                insertarValoracion(pos, txtComentar.getText().toString(), rbPuntuacion.getRating());
                adaptador.setListaValoracion(new ArrayList<Valoracion>());
               adaptador.listarValoraciones(getApplicationContext(),Bebidas.buscarbebida.get(pos).getId_bebida());
               recyclerCom.setAdapter(adaptador);
                txtComentar.setText("");
                rbPuntuacion.setRating(0);
            }
        });

        if (manager.checkSession()) {
            btnComentar.setEnabled(true);
            txtComentar.setEnabled(true);
        }
        else {
            btnComentar.setEnabled(false);
            txtComentar.setEnabled(false);
        }
        Log.i("elpepe", "xd1");
        getPuntajePromedio(this, Bebidas.buscarbebida.get(pos).getId_bebida());
        verificarSiEsteUsuarioYaComento(manager.getSesion().getUsuarioId(), Bebidas.buscarbebida.get(pos).getId_bebida());
    }

    public void verificarSiEsteUsuarioYaComento(int UsuarioId, int BebidaId){
        RequestQueue que = Volley.newRequestQueue(this);

        //String endPoint = "http://virualca-001-site1.dtempurl.com/api/valoraciones/pun/" + UsuarioId + "/" + BebidaId;
        String endPoint = "http://appgrikat.gear.host/api/valoraciones/pun/1/1";

       // Toast.makeText(getApplicationContext(), endPoint, Toast.LENGTH_LONG).show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, endPoint, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        boolean yaComento;
                        Toast.makeText(getApplicationContext(), "el pepe2", Toast.LENGTH_LONG).show();
                        try {
                            yaComento = (boolean)response.get(0);
                            if(yaComento){
                                //bloquear comentario
                                LinearLayout comentBox = findViewById(R.id.comentBox);
                                comentBox.setVisibility(View.INVISIBLE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        que.add(jsonArrayRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public  void insertarValoracion (int pos, String comentario, float puntuacion){
        RequestQueue que = Volley.newRequestQueue(this);
        final TextView nombre = findViewById(R.id.idcomentario);
        final JSONObject valoracion = new JSONObject();


        try {
            valoracion.put("comentario",comentario);
            valoracion.put("puntuacion", puntuacion);
            valoracion.put("UsuarioId",manager.getSesion().getUsuarioId());
            valoracion.put("BebidaId",Bebidas.buscarbebida.get(pos).getId_bebida());
           // Log.i("TAGs",Bebidas.buscarbebida.get(pos).getId_bebida()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, "http://virualca-001-site1.dtempurl.com/api/valoraciones/com"+manager.getSesion().getUsuarioId()+"/"+ Bebidas.buscarbebida.get(pos).getId_bebida()+"/"+4+"/"+comentario.replaceAll(" ", "%20")
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, "http://appgrikat.gear.host/api/valoraciones/com",valoracion,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.toString().contains("JSONException")) {
                    nombre.setText("Sin comentarios");
                } else {
               //     Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }

            }
        }
        );
        que.add(jsonObjectRequest);
    }

    public  void getPuntajePromedio(final Context context, int id) {

        RequestQueue que = Volley.newRequestQueue(context);

        String endPoint = "http://appgrikat.gear.host/api/bebida/" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endPoint, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        double pp = 0;
                        //Toast.makeText(getApplicationContext(), "el pepe", Toast.LENGTH_LONG).show();
                        try {
                            pp = response.getDouble("puntajeProm");
                            //Toast.makeText(getApplicationContext(),String.valueOf(pp), Toast.LENGTH_LONG).show();
                            RatingBar puntajePromedio = findViewById(R.id.rbPuntajePromedio);
                            puntajePromedio.setRating((float)pp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        que.add(jsonObjectRequest);
    }
}

