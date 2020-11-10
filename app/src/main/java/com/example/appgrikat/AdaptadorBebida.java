package com.example.appgrikat;

import android.content.Context;
import android.content.Intent;
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


public class AdaptadorBebida extends RecyclerView.Adapter<AdaptadorBebida.ViewHolder>{
    List<Bebidas> listaBebida =new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView lblbebida,lbllocal,lblprecio,lbldescripcion;
        public SearchView buscar;
        public EditText buscarBebida;
        public CardView cardView;


        public ViewHolder(View view) {
            super(view);
           /* lblbebida= (TextView) view.findViewById(R.id.lblPlato);
            lbllocal= (TextView) view.findViewById(R.id.lblNombreRestaurante);
            lblprecio= (TextView) view.findViewById(R.id.lblPrecioPlato);
            lbldescripcion= (TextView) view.findViewById(R.id.lblDescripcionPlato);
            buscar= (SearchView) view.findViewById(R.id.sVBuscar);
            buscarBebida= (EditText) view.findViewById(R.id.eTBuscar);
            cardView = view.findViewById(R.id.cardview_plato_id);

            */
        }
    }
    /*public  void llenarBebida(final Context context, String bebidas2){
        RequestQueue que = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequests =new JsonArrayRequest(Request.Method.GET, "http://"+Usuario.IP+"/AppgrikatWeb/bebidas/"+bebidas2,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()!=0) {
                            for(int k=0;k<response.length();k++) {
                                try {
                                    Bebidas bebidas = new Bebidas();
                                    bebidas.setId_plato(((JSONObject) response.get(k)).getInt("id_plato"));
                                    bebidas.setDescripcion(((JSONObject) response.get(k)).getString("descripcion_plato"));
                                    bebidas.setNombre(((JSONObject) response.get(k)).getString("nombre_plato"));
                                    bebidas.setPrecio(((JSONObject) response.get(k)).getDouble("precio_plato"));
                                    bebidas.setRestaurante(((JSONObject) response.get(k)).getString("nombre_restaurante"));
                                    listaBebida.add(bebidas);

                                    Toast.makeText(context, ((JSONObject) response.get(k)).getString("nombre_plato"), Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            Bebidas.setBuscarpalatos(listaBebida);
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
    }*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bebidas_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
      /*  holder.lblplato.setText(listaPlatos.get(position).getNombre());
        holder.lblrestaurante.setText(listaPlatos.get(position).getRestaurante());
        holder.lblprecio.setText((int) listaPlatos.get(position).getPrecio()+"");
        holder.lbldescripcion.setText(listaPlatos.get(position).getDescripcion());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Valoracion val =new Valoracion();
                val.setId(listaPlatos.get(position).getId_plato()+"");
                Intent intent = new Intent(holder.itemView.getContext(), ComentarValorarActivity.class);
                intent.putExtra("pos", position);
                Toast.makeText(holder.itemView.getContext(), listaPlatos.get(position).getId_plato()+"", Toast.LENGTH_LONG).show();
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return Bebidas.buscarbebidas.size();
    }


}

