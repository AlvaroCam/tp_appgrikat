package com.example.appgrikat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class OfertaFragment extends Fragment {
    private RecyclerView recyclerOfertas;
    private GridLayoutManager layoutManager;
    private AdaptadorOfertas adaptador;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oferta,container,false);

        recyclerOfertas = view.findViewById(R.id.idreciclador_ofertas);
        layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerOfertas.setLayoutManager(layoutManager);


        adaptador = new AdaptadorOfertas();
       adaptador.llenarOfertas(getContext());
        recyclerOfertas.setAdapter(adaptador);
        return view;

    }


}
