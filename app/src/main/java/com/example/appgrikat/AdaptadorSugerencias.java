package com.example.appgrikat;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorSugerencias extends RecyclerView.Adapter<AdaptadorSugerencias.ViewHolder>{
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre,precio;
        public ImageView imagen;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.sugerencias_title_id);
            precio = view.findViewById(R.id.precio_sugerencias_id);
            imagen = view.findViewById(R.id.sugerencias_img_view);
            cardView = view.findViewById(R.id.cardview_sugerencias_id);
        }
    }

    @Override
    public void onBindViewHolder(final AdaptadorSugerencias.ViewHolder holder, final int position) {
        Bebidas item = Bebidas.listaBebidas.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getIdDrawable())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(holder.imagen);
        holder.nombre.setText(item.getNombre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return Bebidas.listaBebidas.size();
    }

    @Override
    public AdaptadorSugerencias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sugerencias_item, parent, false);
        return new AdaptadorSugerencias.ViewHolder(view);
    }
}
