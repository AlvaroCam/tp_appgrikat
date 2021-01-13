package com.example.appgrikat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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


public class AdaptadorBebida extends RecyclerView.Adapter<AdaptadorBebida.ViewHolder> {
    List<Bebidas> listarBebidas =new ArrayList<>();


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView lblbebida,lblprecio,lbldescripcion;
        public SearchView buscar;
        public EditText buscarBebida;
        public CardView cardView;
        public ImageView idImagen;


        public ViewHolder(View view) {
            super(view);
            lblbebida= (TextView) view.findViewById(R.id.lblNombreBebida);
            lblprecio= (TextView) view.findViewById(R.id.lblPrecioBebida);
            lbldescripcion= (TextView) view.findViewById(R.id.lblDescripcionBebida);
            buscar= (SearchView) view.findViewById(R.id.sVBuscar);
            buscarBebida= (EditText) view.findViewById(R.id.eTBuscar);
            cardView = view.findViewById(R.id.cardview_bebida_id);
            idImagen= view.findViewById(R.id.idImagen);
        }
    }
    public  void llenarbebidas(final Context context, String bebidas2) {

        listarBebidas.clear();
        RequestQueue que = Volley.newRequestQueue(context);

       JsonArrayRequest jsonArrayRequests =new JsonArrayRequest(Request.Method.GET, "http://appgrikat.gear.host/api/bebidas/"+bebidas2,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()!=0) {

                            for(int k=0;k<response.length();k++) {
                                try {

                                    Bebidas bebida = new Bebidas();
                                    bebida.setId_bebida(((JSONObject) response.get(k)).getInt("BebidaId"));
                                   bebida.setDescripcion(((JSONObject) response.get(k)).getString("descripcion"));
                                    bebida.setNombre(((JSONObject) response.get(k)).getString("nombre"));
                                    bebida.setPrecio(((JSONObject) response.get(k)).getDouble("precio"));
                                    //bebida.setIdDrawable(Base64.decode(((JSONObject) response.get(k)).getString("imagen"),Base64.DEFAULT));
                                    bebida.setImagenbe(((JSONObject) response.get(k)).getString("imagen"));

                                    listarBebidas.add(bebida);

                                //Toast.makeText(context, ((JSONObject) response.get(k)).getString("nombre"), Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            Bebidas.setBuscarbebida(listarBebidas);
                            notifyDataSetChanged();
                        }else{
//                            Toast.makeText(context, ""+ response.length(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Log.i("TAG",error.toString());

             //   Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }
        );
         que.add(jsonArrayRequests);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.lblbebida.setText(Bebidas.buscarbebida.get(position).getNombre());
        holder.lblprecio.setText("S/. "+Bebidas.buscarbebida.get(position).getPrecio()+"0");
        holder.lbldescripcion.setText(Bebidas.buscarbebida.get(position).getDescripcion());
        final Bebidas item = Bebidas.buscarbebida.get(position);
//        Bitmap bmp = BitmapFactory.decodeByteArray(item.getIdDrawable(), 0, item.getIdDrawable().length);
        Glide.with(holder.itemView.getContext()).load(item.getImagenbe())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(holder.idImagen);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Valoracion val =new Valoracion();
                val.setValoracionId(Bebidas.buscarbebida.get(position).getId_bebida());
                Intent intent = new Intent(holder.itemView.getContext(), ComentarValorarActivity.class);
                intent.putExtra("pos", position);
               // Toast.makeText(holder.itemView.getContext(), Bebidas.buscarbebida.get(position).getNombre()+"", Toast.LENGTH_LONG).show();
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Bebidas.buscarbebida.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bebidas_item, parent, false);
        return new ViewHolder(view);
    }

}

