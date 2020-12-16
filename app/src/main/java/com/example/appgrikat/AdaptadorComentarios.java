package com.example.appgrikat;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorComentarios extends RecyclerView.Adapter<AdaptadorComentarios.ViewHolder>  {
    Valoracion val=new Valoracion();
    List<Valoracion> listaValoracion =new ArrayList<>();
    public String bebida;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public ImageView imagen;
        public TextView comentario;
        public CardView cardView;
        public ViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.lblNombre);
            imagen = view.findViewById(R.id.idImagen);
            comentario = view.findViewById(R.id.lblComentario);
            cardView = view.findViewById(R.id.cardview_comentario_id);
        }
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public void listarValoraciones(final Context context) {
        RequestQueue que = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequests =new JsonArrayRequest(Request.Method.GET, "http://virualca-001-site1.dtempurl.com/api/valoraciones/com",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()!=0) {
                            for ( int i=0; i<response.length();i++) {
                                try {
                                    int valoracionid = ((JSONObject)response.get(i)).getInt("ValoracionId");
                                    int puntuacion = ((JSONObject)response.get(i)).getInt("puntuacion");
                                    String comentario = ((JSONObject)response.get(i)).getString("comentario");
                                    listaValoracion.add(new Valoracion(valoracionid,puntuacion, comentario));
                                    Toast.makeText(context, ((JSONObject) response.get(i)).getString("comentario"), Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            Valoracion.llenarValoracion(listaValoracion);
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, ""+ response.length(), Toast.LENGTH_SHORT).show();
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comentarios_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Valoracion item = listaValoracion.get(position);

        //holder.nombre.setText(item.getNombre());
        holder.comentario.setText(item.getComentar());
    }

    @Override
    public int getItemCount() {
        return listaValoracion.size();
    }



}
