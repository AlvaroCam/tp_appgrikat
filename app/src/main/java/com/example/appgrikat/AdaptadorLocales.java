package com.example.appgrikat;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorLocales  extends RecyclerView.Adapter<AdaptadorLocales.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre,descrip,direc;
        public ImageView imagen;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.Local_title_id);
            descrip = view.findViewById(R.id.descripcion_local_id);
            direc = view.findViewById(R.id.direccion_local_id);
            imagen = view.findViewById(R.id.local_img_view);
            cardView = view.findViewById(R.id.cardview_locales_id);
        }
    }

    List<Locales> listarLocales =new ArrayList<>();


    public  void llenarlocales(final Context context) {
        RequestQueue que = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequests =new JsonArrayRequest(Request.Method.GET, "http://virualca-001-site1.dtempurl.com/api/locales",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()!=0) {

                            for(int k=0;k<response.length();k++) {
                                try {
                                    Toast.makeText(context, ((JSONObject) response.get(k)).getString("nombre"), Toast.LENGTH_SHORT).show();
                                    Locales loc = new Locales();
                                    loc.setNombre(((JSONObject) response.get(k)).getString("nombre"));
                                  loc.setDescripcion(((JSONObject) response.get(k)).getString("descripcion"));
                                    loc.setDireccion(((JSONObject) response.get(k)).getString("direccion"));
                                    loc.setImagenLo(((JSONObject) response.get(k)).getString("imagen"));
                                    listarLocales.add(loc);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, ""+ response.length(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG",error.toString());

                Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        );
        que.add(jsonArrayRequests);
    }

    @NonNull
    @Override
    public AdaptadorLocales.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locales_item, parent, false);
        return new AdaptadorLocales.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdaptadorLocales.ViewHolder holder, final int position) {
        final Locales item = listarLocales.get(position);


        Glide.with(holder.itemView.getContext()).load(item.getImagenLo())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(holder.imagen);
        holder.nombre.setText(item.getNombre());
        holder.descrip.setText(item.getDescripcion());
        holder.direc.setText(item.getDireccion());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetalleActivity.class);
                intent.putExtra("pos", position);
                Toast.makeText(holder.itemView.getContext(), ""+item.getNombre(), Toast.LENGTH_LONG).show();
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listarLocales.size();
    }


}
