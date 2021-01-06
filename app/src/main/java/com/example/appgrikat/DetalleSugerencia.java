package com.example.appgrikat;

import android.content.Context;
import android.os.Bundle;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetalleSugerencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_sugerencia);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int pos = getIntent().getExtras().getInt("pos");
        obtenerBebida(Sugerencias.listaSugerencias.get(pos).getSugerenciaId());

        Log.i("tag",(Sugerencias.listaSugerencias.get(pos).getSugerenciaId() + ""));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void obtenerBebida(int idB){
        RequestQueue que = Volley.newRequestQueue(this);
        //Toast.makeText(getApplicationContext(), ""+idB, Toast.LENGTH_LONG).show();

        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, "http://appgrikat.gear.host/api/sugerencia/"+idB,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Sugerencias sug = new Sugerencias();

                            sug.setTituloSug(response.getString("titulo"));
                            setTitle(sug.getTituloSug());
                            TextView nombreSug = findViewById(R.id.nombreSug);
                            nombreSug.setText(sug.getTituloSug());

                            sug.setContentSug(response.getString("contenido"));
                            TextView descripcion = findViewById(R.id.descripcionSugerencia);
                            descripcion.setText(sug.getContentSug());

                            String cadenaJson = response.getString("Bebida");
                            JSONObject json = new JSONObject(cadenaJson);
                            String image = json.getString("imagen");

                            //precio
                            double precio = json.getDouble("precio");
                            sug.setPrecioBeb(precio);
                            TextView precioBebida = findViewById(R.id.bebidaPrecio);
                            precioBebida.setText("S/. " + sug.getPrecioBeb());

                            //imagen
                            sug.setCadenaImagen(image);
                            ImageView imagen = findViewById(R.id.imagenBebida);
                            Glide.with(imagen.getContext())
                                    .load(sug.getCadenaImagen())
                                    .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                                    .into(imagen);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        }
        );

        que.add(jsonObjectRequest);


    }
}