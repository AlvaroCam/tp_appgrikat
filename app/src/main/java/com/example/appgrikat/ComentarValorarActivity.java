package com.example.appgrikat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
        ImageButton btnComentar = findViewById(R.id.btnComentar);
        btnComentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtComentar.getText().toString().isEmpty()) {
                    Snackbar.make(txtComentar, "Este campo no puede quedar en blanco.", Snackbar.LENGTH_LONG).show();
                }
                insertarValoracion(pos, txtComentar.getText().toString());
                adaptador.setListaValoracion(new ArrayList<Valoracion>());
               adaptador.listarValoraciones(getApplicationContext(),Bebidas.buscarbebida.get(pos).getId_bebida());
               recyclerCom.setAdapter(adaptador);
                txtComentar.setText("");
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
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    public  void insertarValoracion (int pos, String comentario){
        RequestQueue que = Volley.newRequestQueue(this);
        final TextView nombre = findViewById(R.id.idcomentario);
        final JSONObject valoracion = new JSONObject();


        try {
            valoracion.put("comentario",comentario);
            valoracion.put("puntuacion",1);
            valoracion.put("UsuarioId",manager.getSesion().getUsuarioId());
            valoracion.put("BebidaId",Bebidas.buscarbebida.get(pos).getId_bebida());
           // Log.i("TAGs",Bebidas.buscarbebida.get(pos).getId_bebida()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, "http://virualca-001-site1.dtempurl.com/api/valoraciones/com"+manager.getSesion().getUsuarioId()+"/"+ Bebidas.buscarbebida.get(pos).getId_bebida()+"/"+4+"/"+comentario.replaceAll(" ", "%20")
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.POST, "http://virualca-001-site1.dtempurl.com/api/valoraciones/pun",valoracion,
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
}

