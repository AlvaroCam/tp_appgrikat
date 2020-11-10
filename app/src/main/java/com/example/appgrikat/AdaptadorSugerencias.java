package com.example.appgrikat;


import android.content.Context;
import android.content.Intent;
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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorSugerencias extends RecyclerView.Adapter<AdaptadorSugerencias.ViewHolder>{
    List<Sugerencias> listasuge =new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public ImageView imagen;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.sugerencias_title_id);
            imagen = view.findViewById(R.id.sugerencias_img_view);
            cardView = view.findViewById(R.id.cardview_sugerencias_id);
        }
    }

    public  void llenarSugerencia (final Context context) {
        /*
        RequestQueue que = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequests =new JsonArrayRequest(Request.Method.GET, " ",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()!=0) {
                            for(int h=0;h<response.length();h++) {
                                try {
                                    listasuge.add(new Sugerencias(((JSONObject) response.get(h)).getString("nombre_sugerencia"), "" + ((JSONObject) response.get(h)).getInt("id_restaurante"),
                                            (((JSONObject) response.get(h)).getString("imagen_sugerencia")).getBytes()));
                                    /*Toast.makeText(context, ((JSONObject) response.get(h)).getString("nombre_sugerencia"), Toast.LENGTH_SHORT).show();*/
/*
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                            Sugerencias.llenarosugerencias(listasuge);
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, ""+ response.length(), Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(context, listasuge.size()+"", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        );
        que.add(jsonArrayRequests);
 */
    }

    @Override
    public void onBindViewHolder(final AdaptadorSugerencias.ViewHolder holder, final int position) {
        //final Platos item = Platos.listaPlatos.get(position);
        final  Sugerencias item2 = Sugerencias.listaSugerencias.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item2.getIdDrawable())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(holder.imagen);
        holder.nombre.setText(item2.getNombre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenarDetalleSugerencia(v,holder,item2,position);
            }
        });
    }

    public void llenarDetalleSugerencia(View v ,ViewHolder holder,Sugerencias item2, int position){

        Intent intent = new Intent(holder.itemView.getContext(), DetalleActivity.class);
        intent.putExtra("pos", position);

        holder.itemView.getContext().startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return   Sugerencias.listaSugerencias.size();
    }

    @Override
    public AdaptadorSugerencias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sugerencias_item, parent, false);
        return new AdaptadorSugerencias.ViewHolder(view);
    }
}
