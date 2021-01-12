package com.example.appgrikat;

import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Toolbar toolbar = findViewById(R.id.toolbarDetalleOferta);
        setSupportActionBar(toolbar);

        int pos = getIntent().getExtras().getInt("pos");
        obtenerLocal(Ofertas.listaOfertas.get(pos).getOfertaId());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public  void obtenerLocal (int idR) {
        RequestQueue que = Volley.newRequestQueue(this);
        //Toast.makeText(getApplicationContext(), "" + idR, Toast.LENGTH_LONG).show();

        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, "http://appgrikat.gear.host/api/oferta/"+idR,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Ofertas of = new Ofertas();
                            of.setOfertaId(response.getInt("OfertaId"));
                            of.setNombre(response.getString("nombre"));
                            setTitle("OFERTA #" + of.getOfertaId() +": "+of.getNombre());

                            of.setImagen(response.getString("imagen"));
                            of.setInfo(response.getString("descripcion"));
                            of.setFechaIn(response.getString("fechaI"));
                            of.setFechaFin(response.getString("fechaF"));

                            TextView desc = findViewById(R.id.descripcionOferta);
                            desc.setText(of.getInfo());
                            TextView fechaDisp = findViewById(R.id.DisponibilidadFecha);
                            String fechaIDia = of.getFechaIn().substring(8,10);
                            String fechaIMes = of.getFechaIn().substring(5,7);
                            String fechaIAño = of.getFechaIn().substring(0,4);
                            String fechaFDia = of.getFechaFin().substring(8,10);
                            String fechaFMes = of.getFechaFin().substring(5,7);
                            String fechaFAño = of.getFechaFin().substring(0,4);
                            fechaDisp.setText("Valido desde el " + fechaIDia+"/"+fechaIMes+"/"+fechaIAño + " hasta el " + fechaFDia+"/"+fechaFMes+"/"+fechaFAño);
                            ImageView imagen = findViewById(R.id.imagenOferta);
                            Glide.with(imagen.getContext())
                                    .load(of.getImagen())
                                    .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                                    .into(imagen);

                            String cadenaJson = response.getString("Local");
                            JSONObject json = new JSONObject(cadenaJson);

                            of.setNombreLocal(json.getString("nombre"));
                            TextView nombLoc = findViewById(R.id.DisponibilidadLocal);
                            nombLoc.setText("¡Disponible en " + of.getNombreLocal()+"!");
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
