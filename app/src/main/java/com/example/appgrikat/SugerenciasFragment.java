package com.example.appgrikat;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SugerenciasFragment extends Fragment {
    private RecyclerView recyclerSugerencias;
    private LinearLayoutManager layoutManager;
    private AdaptadorSugerencias adaptador;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sugerencias,container,false);

        recyclerSugerencias = view.findViewById(R.id.idreciclador_sugerencias);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerSugerencias.setLayoutManager(layoutManager);

        adaptador = new AdaptadorSugerencias();
        recyclerSugerencias.setAdapter(adaptador);
        return view;

    }

}
