package com.example.appgrikat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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


public class AdaptadorCategorias  extends RecyclerView.Adapter<AdaptadorCategorias.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public ImageView imagen;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.categorias_title_id);
            imagen = view.findViewById(R.id.categorias_img_view);
            cardView = view.findViewById(R.id.cardview_categorias_id);
        }
    }

    @Override
    public void onBindViewHolder(final AdaptadorCategorias.ViewHolder holder, final int position) {
        Categoria item = Categoria.listaCategoria.get(position);
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
        return Categoria.listaCategoria.size();
    }

    @Override
    public AdaptadorCategorias.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorias_item, parent, false);
        return new AdaptadorCategorias.ViewHolder(view);
    }
    }
