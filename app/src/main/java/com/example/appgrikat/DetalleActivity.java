package com.example.appgrikat;

import android.os.Bundle;

import android.util.Base64;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int pos = getIntent().getExtras().getInt("pos");
        obtenerLocal(Ofertas.listaOfertas.get(pos).getInfo());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public  void obtenerLocal (String idR){
        RequestQueue que = Volley.newRequestQueue(this);
        Toast.makeText(getApplicationContext(), ""+idR, Toast.LENGTH_LONG).show();
        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET, "http://virualca-001-site1.dtempurl.com/api/ofertas/"+idR,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Locales loc = new Locales();

                            loc.setDescripcion(response.getString("descripcion"));
                            loc.setDireccion(response.getString("direccion"));
                            loc.setNombre(response.getString("nombre"));
                          //  loc.setImage(Base64.decode(response.getString("imagen_oferta"),Base64.DEFAULT));
                            loc.setImagenLo(response.getString("imagen"));
                            setTitle(loc.getNombre());
                            TextView nombre = findViewById(R.id.txttitleOf);
                            nombre.setText(loc.getNombre());
                            TextView desc = findViewById(R.id.txtDescOf);
                            desc.setText(loc.getDescripcion());
                            TextView dir = findViewById(R.id.txtDireccion);
                            dir.setText(loc.getDireccion());
                            ImageView iamgen = findViewById(R.id.idImagen);
                            Glide.with(iamgen.getContext())
                                    .load(loc.getImagenLo())
                                    .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                                    .into(iamgen);
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
