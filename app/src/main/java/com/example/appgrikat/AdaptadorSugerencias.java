package com.example.appgrikat;


import android.content.Context;
import android.content.Intent;
import android.icu.text.MessagePattern;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorSugerencias extends RecyclerView.Adapter<AdaptadorSugerencias.ViewHolder>{
    List<Sugerencias> listasuge =new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView titulo;
        public TextView descripcion;
        public ImageView imagen;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            titulo = view.findViewById(R.id.sugerencias_title_id);
            descripcion = view.findViewById(R.id.descripcion_sugerencias_id);
            imagen = view.findViewById(R.id.sugerencias_img_view);
            cardView = view.findViewById(R.id.cardview_sugerencias_id);
        }
    }

    public  void listarSugerencia (final Context context) {

        RequestQueue que = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequests =new JsonArrayRequest(Request.Method.GET,
                "http://virualca-001-site1.dtempurl.com/api/sugerencias",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()!=0) {
                            for(int h=0;h<response.length();h++) {
                                try {
                                   Sugerencias sug = new Sugerencias();
                                   sug.setTituloSug(((JSONObject)response.get(h)).getString("titulo"));
                                   sug.setContentSug(((JSONObject)response.get(h)).getString("contenido"));
                                   sug.setBebidaId(((JSONObject)response.get(h)).getInt("BebidaId"));


                                   String cadenaJson = ((JSONObject) response.get(h)).getString("Bebida");
                                   JSONObject json = new JSONObject(cadenaJson);
                                   String image = json.getString("imagen");

                                   //Log.i("tag",image);

                                   sug.setCadenaImagen(image);
                                   listasuge.add(sug);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "" + response.length(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        );

        que.add(jsonArrayRequests);

    }

    @Override
    public void onBindViewHolder(final AdaptadorSugerencias.ViewHolder holder, final int position) {
        final  Sugerencias item2 = listasuge.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item2.getCadenaImagen())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(holder.imagen);
        holder.titulo.setText(item2.getTituloSug());
        holder.descripcion.setText(item2.getContentSug());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return  listasuge.size();
    }

    @Override
    public AdaptadorSugerencias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sugerencias_item, parent, false);
        return new AdaptadorSugerencias.ViewHolder(view);
    }
}
