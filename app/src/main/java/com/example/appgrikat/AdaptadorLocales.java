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

public class AdaptadorLocales  extends RecyclerView.Adapter<AdaptadorLocales.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre,descrip;
        public ImageView imagen;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            nombre = view.findViewById(R.id.Local_title_id);
            descrip = view.findViewById(R.id.descripcion_local_id);
            imagen = view.findViewById(R.id.local_img_view);
            cardView = view.findViewById(R.id.cardview_locales_id);
        }
    }

    @Override
    public void onBindViewHolder(final AdaptadorLocales.ViewHolder holder, final int position) {
        Locales item = Locales.listaLocales.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getIdDrawable())
                .apply(new RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher_round))
                .into(holder.imagen);
        holder.nombre.setText(item.getNombre());
        holder.descrip.setText(item.getDescripcion());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //ewer
    }

    @Override
    public int getItemCount() {
        return Locales.listaLocales.size();
    }

    @Override
    public AdaptadorLocales.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.locales_item, parent, false);
        return new AdaptadorLocales.ViewHolder(view);
    }
}
