package com.example.appgrikat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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

public class AdaptadorOfertasPorLocal extends RecyclerView.Adapter<AdaptadorOfertasPorLocal.ViewHolder> {

    public List<Ofertas> listaOfertas =new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreOferta;
        public TextView duracion_oferta;
        public ImageView imagenOferta;
        public CardView cardView;
        public ViewHolder(View view) {
            super(view);
            nombreOferta = view.findViewById(R.id.ofertas_local_title_id);
            imagenOferta = view.findViewById(R.id.ofertas_local_img_view);
            duracion_oferta = view.findViewById(R.id.duracion_oferta_local);
            cardView = view.findViewById(R.id.cardview_comentario_id);
        }
    }


    public void listarOfertas(final Context context, int id) {
        listaOfertas.clear();
        RequestQueue que = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequests =new JsonArrayRequest(Request.Method.GET,
                "http://appgrikat.gear.host/api/ofertas/" + id,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()!=0) {
                            for ( int i=0; i<response.length();i++) {
                                try {
                                    Ofertas of = new Ofertas();
                                    of.setNombre(((JSONObject)response.get(i)).getString("nombre"));
                                    of.setFechaIn(((JSONObject)response.get(i)).getString("fechaI"));
                                    of.setFechaFin(((JSONObject) response.get(i)).getString("fechaF"));
                                    of.setImagen(((JSONObject)response.get(i)) .getString("imagen"));
                                    listaOfertas.add(of);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            Ofertas.llenarOfertas(listaOfertas);
                            notifyDataSetChanged();
                        }else{
                            //      Toast.makeText(context, ""+ response.length(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //  Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }
        );
        que.add(jsonArrayRequests);


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ofertaxlocal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Ofertas item = listaOfertas.get(position);
        holder.nombreOferta.setText(item.getNombre());
        String fechaIDia = item.getFechaIn().substring(8,10);
        String fechaIMes = item.getFechaIn().substring(5,7);
        String fechaIAño = item.getFechaIn().substring(0,4);
        String fechaFDia = item.getFechaFin().substring(8,10);
        String fechaFMes = item.getFechaFin().substring(5,7);
        String fechaFAño = item.getFechaFin().substring(0,4);
        holder.duracion_oferta.setText("Válido desde el " + fechaIDia+"/"+fechaIMes+"/"+fechaIAño + " hasta el " + fechaFDia+"/"+fechaFMes+"/"+fechaFAño);
                Glide.with(holder.itemView.getContext()).load(item.getImagen())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(holder.imagenOferta);
    }

    @Override
    public int getItemCount() {
        return listaOfertas.size();
    }




}
