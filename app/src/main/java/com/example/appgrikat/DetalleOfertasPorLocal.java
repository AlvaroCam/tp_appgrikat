package com.example.appgrikat;

import android.os.Bundle;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetalleOfertasPorLocal extends AppCompatActivity {
    private LinearLayoutManager llm;
    private RecyclerView rOf;
    private AdaptadorOfertasPorLocal aoxl;
    private AdaptadorOfertas ao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ofertas_por_local);
        final int pos = getIntent().getExtras().getInt("pos");
        Toolbar toolbar = findViewById(R.id.toolbarOfertasxLocal);
        setSupportActionBar(toolbar);
        obtenerLocal(Locales.listaLocales.get(pos).getLocalId());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aoxl =new AdaptadorOfertasPorLocal();
        aoxl.listarOfertas(this, Locales.listaLocales.get(pos).getLocalId());
        rOf = findViewById(R.id.recyclerOfertas);
        rOf.setAdapter(aoxl);
        llm = new LinearLayoutManager(this);
        rOf.setLayoutManager(llm);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void obtenerLocal(int idR) {
        RequestQueue que = Volley.newRequestQueue(this);
        //Toast.makeText(getApplicationContext(), "" + idR, Toast.LENGTH_LONG).show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://appgrikat.gear.host/api/local/" + idR, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Locales loc = new Locales();
                            loc.setLocalId(response.getInt("LocalId"));
                            loc.setNombre(response.getString("nombre"));
                            setTitle("Ofertas del Local #" + loc.getLocalId());

                            TextView tv = findViewById(R.id.NombreLocal);
                            tv.setText(loc.getNombre());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }
        );
        que.add(jsonObjectRequest);

    }
}