package com.example.appgrikat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CategoriaFragment extends Fragment {
    private RecyclerView recyclerCategorias;
    private LinearLayoutManager layoutManager;
    private AdaptadorCategorias adaptador;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categoria,container,false);

        recyclerCategorias = view.findViewById(R.id.idreciclador_categoria);
        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerCategorias.setLayoutManager(layoutManager);

        adaptador = new AdaptadorCategorias();
        recyclerCategorias.setAdapter(adaptador);
        return view;

    }

}
