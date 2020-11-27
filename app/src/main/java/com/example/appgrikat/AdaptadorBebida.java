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
import android.widget.Toast;

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

    public List<Bebidas> getListarBebidas() {
        return listarBebidas;
    }

    public void setListarBebidas(List<Bebidas> listarBebidas) {
        this.listarBebidas = listarBebidas;
    }


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
        RequestQueue que = Volley.newRequestQueue(context);
        /*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://amadis-backend.herokuapp.com/api/people", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray customers = response.getJSONArray("data");

                    for (int i = 0; i < customers.length(); i++) {

                        Bebidas customer = new Bebidas();
                        JSONObject customerJSONObject = customers.getJSONObject(i);
                        customer.setNombre(customerJSONObject.getString("name"));

                        listarBebidas.add(customer);

                    }
                    Toast.makeText(context, listarBebidas.toString(), Toast.LENGTH_SHORT).show();
                    Log.i("TAGs",listarBebidas.get(1).getNombre());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        que.add(jsonObjectRequest);*/

       JsonArrayRequest jsonArrayRequests =new JsonArrayRequest(Request.Method.GET, "http://angelord-001-site1.etempurl.com/api/bebidas/"+bebidas2,null,
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

                                  Toast.makeText(context, ((JSONObject) response.get(k)).getString("nombre"), Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            Bebidas.setBuscarbebida(listarBebidas);
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bebidas_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.lblbebida.setText(Bebidas.buscarbebida.get(position).getNombre());
        holder.lblprecio.setText((int) Bebidas.buscarbebida.get(position).getPrecio()+"");
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
                val.setId(Bebidas.buscarbebida.get(position).getId_bebida()+"");
                Intent intent = new Intent(holder.itemView.getContext(), ComentarValorarActivity.class);
                intent.putExtra("pos", position);
                Toast.makeText(holder.itemView.getContext(), Bebidas.buscarbebida.get(position).getId_bebida()+"", Toast.LENGTH_LONG).show();
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listarBebidas.size();
    }


}

