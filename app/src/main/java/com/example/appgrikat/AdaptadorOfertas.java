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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.DefaultRetryPolicy;
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


public class AdaptadorOfertas extends RecyclerView.Adapter<AdaptadorOfertas.ViewHolder>{
    public  List<Ofertas> listaOfertas= new ArrayList<>();
    DireccionWeb direccion= new DireccionWeb();
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public ImageView imagen;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.ofertas_title_id);
            imagen = view.findViewById(R.id.ofertas_img_view);
            cardView = view.findViewById(R.id.cardview_ofertas_id);
        }
    }

    public  void llenarOfertas (final Context context){
        listaOfertas.clear();
        RequestQueue que = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequests =new JsonArrayRequest(Request.Method.GET,
                "http://virualca-001-site1.dtempurl.com/api/ofertas",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()!=0) {
                            for ( int u=0; u<response.length();u++) {
                                try {
                                    Ofertas ofertas = new Ofertas();
                                    ofertas.setNombre(((JSONObject) response.get(u)).getString("nombre"));
                                    //ofertas.setPrecio(((JSONObject) response.get(u)).getDouble("precio"));
                                   ofertas.setImagen(((JSONObject) response.get(u)).getString("imagen"));
                                    listaOfertas.add(ofertas);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            Ofertas.llenarOfertas(listaOfertas);
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
        //  jsonArrayRequests.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        que.add(jsonArrayRequests);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //Ofertas item = Ofertas.listaOfertas.get(position);
        final Ofertas item = listaOfertas.get(position);
        //Bitmap bmp = BitmapFactory.decodeByteArray(item.getIdDrawable(), 0, item.getIdDrawable().length);

        Glide.with(holder.itemView.getContext()).load(item.getImagen())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(holder.imagen);
        holder.nombre.setText(item.getNombre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetalleActivity.class);
                intent.putExtra("pos", position);
                Toast.makeText(holder.itemView.getContext(), ""+item.getInfo(), Toast.LENGTH_LONG).show();
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaOfertas.size();
        //  return Ofertas.listaOfertas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ofertas_item, parent, false);
        return new ViewHolder(view);
    }

}
