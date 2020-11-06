package com.example.appgrikat;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



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
/*
    public  void llenarOfertas (final Context context){
        RequestQueue queueEquipo = Volley.newRequestQueue(context);
        JsonArrayRequest  jsonArrayRequests =new JsonArrayRequest(Request.Method.GET, direccion.getDIRECCION()+"/ofertas",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()!=0) {
                            try {
                                Toast.makeText(context ,((JSONObject) response.get(0)).getString("nombre_oferta"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }else{
                            Toast.makeText(context, ""+ response.length(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Algo salio mal",Toast.LENGTH_SHORT).show();

            }
        }
        );

      notifyDataSetChanged();
    }

*/
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Ofertas item = Ofertas.listaOfertas.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getIdDrawable())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(holder.imagen);
        holder.nombre.setText(item.getNombre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetalleActivity.class);
                intent.putExtra("pos", position);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Ofertas.listaOfertas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ofertas_item, parent, false);
         return new ViewHolder(view);
    }





}
